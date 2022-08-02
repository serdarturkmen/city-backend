package com.example.city.resource;

import com.example.city.exception.NotAllowedException;
import com.example.city.model.City;
import com.example.city.service.CityService;
import com.example.city.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/city")
public class CityResource {

    private final CityService cityService;

    public CityResource(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * {@code GET  /} : get all the cities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cities in body.
     */
    @GetMapping("")
    public ResponseEntity<List<City>> findAllCities(Pageable pageable) {
        log.debug("Rest Request to get all cities");
        Page<City> page = cityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/city");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * {@code GET  /:name} : get the "name" city.
     *
     * @param name the id of the city to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cities in body.
     */
    @GetMapping("/{name}")
    public ResponseEntity<List<City>> getCityByName(Pageable pageable, @PathVariable String name) {
        log.debug("Rest Request to get all cities by name: {}", name);
        Page<City> page = cityService.findByName(pageable, name);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/city/{name}");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * {@code GET  /details/:id} : get the "id" city.
     *
     * @param id the id of the city to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the city, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/details/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> authList = authorities.stream().map(simpleGrantedAuthority -> simpleGrantedAuthority.getAuthority()).collect(Collectors.toList());
        if(!authList.contains("ROLE_ALLOW_EDIT")){
            throw new NotAllowedException("Not authorized");
        }
        log.debug("Rest Request to get city by id: {}", id);
        Optional<City> city = cityService.findById(id);
        return city.isPresent() ? ResponseEntity.of(city) : ResponseEntity.notFound().build();
    }

}
