package com.example.city;

import com.example.city.model.City;
import com.example.city.model.Role;
import com.example.city.model.User;
import com.example.city.repository.CityRepository;
import com.example.city.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class CityApplication {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(CityApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        List<City> persistedCities = cityRepository.findAll();
        if (CollectionUtils.isEmpty(persistedCities)) {
            log.info("initializing cities");
            List<City> cityList = new ArrayList<>();
            InputStream in = getClass().getResourceAsStream("/csv/cities.csv");
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader fileReader = new BufferedReader(reader);
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                City city = new City(
                        csvRecord.get("name"),
                        csvRecord.get("photo")
                );
                cityList.add(city);
            }

            cityRepository.saveAll(cityList);
        }

        User user = User.builder()
                .firstName("serdar")
                .lastName("turkmen")
                .email("user@gmail.com")
                .password("user")
                .build();

        userService.createUser(user, Role.ROLE_USER);

        User admin = User.builder()
                .firstName("admin")
                .lastName("admin")
                .email("admin@gmail.com")
                .password("admin")
                .build();

        userService.createUser(admin, Role.ROLE_ALLOW_EDIT);

    }


}
