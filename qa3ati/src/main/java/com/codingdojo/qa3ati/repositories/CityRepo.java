package com.codingdojo.qa3ati.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.qa3ati.models.City;

@Repository
public interface CityRepo extends CrudRepository<City, Long> {
	List<City> findAll();
}
