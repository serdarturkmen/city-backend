package com.example.city.repository;

import com.example.city.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "select ct from City ct where ct.name = :name")
    Page<City> getCitiesByName(String name, Pageable pageable);

    Optional<City> findById(Long id);

}
