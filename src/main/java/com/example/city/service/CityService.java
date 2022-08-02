package com.example.city.service;

import com.example.city.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CityService {

    Page<City> findAll(Pageable pageable);

    Page<City> findByName(Pageable pageable, String name);

    Optional<City> findById(Long id);

}
