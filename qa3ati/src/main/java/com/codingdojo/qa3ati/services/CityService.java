package com.codingdojo.qa3ati.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.qa3ati.models.City;
import com.codingdojo.qa3ati.repositories.CityRepo;

@Service
public class CityService {
	private final CityRepo cityRepo;
	
	public CityService(CityRepo cityRepo) {
		this.cityRepo = cityRepo;
	}
	
	public City findCityById(Long id) {
		Optional<City> optionalCity = cityRepo.findById(id);
		if(optionalCity.isPresent()) {
			return optionalCity.get();
		}
		else {
			return null;
		}
	}
	
	public City createCity(City city) {
		return cityRepo.save(city);
	}
	
	public List<City> allCities() {
		return cityRepo.findAll();
	}
}
