package com.codingdojo.qa3ati.services;

import org.springframework.stereotype.Service;

import com.codingdojo.qa3ati.models.ReserveDate;
import com.codingdojo.qa3ati.repositories.ReserveDateRepo;

@Service
public class ReserveDateService {
	private final ReserveDateRepo reserveDateRepo;

	public ReserveDateService(ReserveDateRepo reserveDateRepo) {
		this.reserveDateRepo = reserveDateRepo;
	}
	
	public ReserveDate createReserveDate(ReserveDate reserveDate) {
		return reserveDateRepo.save(reserveDate);
	}
	
}
