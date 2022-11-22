package com.codingdojo.qa3ati.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.qa3ati.models.Hall;
import com.codingdojo.qa3ati.repositories.HallRepo;

@Service
public class HallService {
	private final HallRepo hallRepo;
	
	public HallService(HallRepo hallRepo) {
		this.hallRepo = hallRepo;
	}
	
	public List<Hall> allHalls() {
		return hallRepo.findAll();
	}
	
	public Hall createHall(Hall hall) {
		return hallRepo.save(hall);
	}
	
	public List<Hall> findHallByQuery(
			String cityName,
			Integer minPrice,
			Integer maxPrice,
			Integer minCapacity,
			Integer maxCapacity) {
		return hallRepo.findHallByQuery(cityName, minPrice, maxPrice, minCapacity, maxCapacity);
	}
	
	public Hall findHallById(Long id) {
		Optional<Hall> optionalHall = hallRepo.findById(id);
		if(optionalHall.isPresent()) {
			return optionalHall.get();
		}
		else {
			return null;
		}
	}
	
	public Hall updateHall(Hall hall) {
		return hallRepo.save(hall);
	}
	
	public boolean deleteHall(Long id) {
		hallRepo.deleteById(id);
		boolean isFound = hallRepo.existsById(id);
		return !isFound;
	}
}
