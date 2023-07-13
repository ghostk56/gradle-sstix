package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.repository.model.City;
import com.example.repository.model.CityCountView;

public interface CityRepository extends JpaRepository<City, Integer> {

	@Query(value = "SELECT count(city) as CityCount FROM sakila.city", nativeQuery = true)
	public CityCountView selectCityCount();
}
