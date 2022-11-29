package com.codingdojo.qa3ati.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingdojo.qa3ati.models.City;
import com.codingdojo.qa3ati.models.Hall;
import com.codingdojo.qa3ati.services.CityService;
import com.codingdojo.qa3ati.services.HallService;

@RestController
public class AppRestController {
	private final HallService hallService;
	private final CityService cityService;
	
	public AppRestController(HallService hallService, CityService cityService) {
		this.hallService = hallService;
		this.cityService = cityService;
	}
	
	@PostMapping("/cities/new")
	public City newCity(@RequestParam(value="name") String name) {
		City city = new City(name);
		return cityService.createCity(city);
	}
//	
//	@GetMapping("/halls")
//	public List<Hall> allHalls() {
//		return hallService.allHalls();
//	}
//	
//	@PostMapping("/halls/new")
//	public Hall newHall(
//			@RequestParam(value="name") String name,
//			@RequestParam(value="address") String address,
//			@RequestParam(value="price") int basicPrice,
//			@RequestParam(value="capacity") int capacity,
//			@RequestParam(value="desc") String description,
//			@RequestParam(value="num") String phoneNumber,
//			@RequestParam(value="city") Long cityId
//			) {
//		Hall hall = new Hall(name, address, basicPrice, capacity, description, phoneNumber);
//		City city = cityService.findCityById(cityId);
//		hall.setCity(city);
//		return hallService.createHall(hall);
//	}
//	
	@GetMapping("/halls/search")
	public List<Hall> searchHall(
			@RequestParam(value="cityName", required = false) String cityName,
			@RequestParam(value="minPrice", required = false) Integer minPrice,
			@RequestParam(value="maxPrice", required = false) Integer maxPrice,
			@RequestParam(value="minCapacity", required = false) Integer minCapacity,
			@RequestParam(value="maxCapacity", required = false) Integer maxCapacity) {
		return hallService.findHallByQuery(
				cityName,
				minPrice,
				maxPrice,
				minCapacity,
				maxCapacity);
	}
	
}
