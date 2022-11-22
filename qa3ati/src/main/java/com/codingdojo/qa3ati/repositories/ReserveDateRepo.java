package com.codingdojo.qa3ati.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.qa3ati.models.ReserveDate;

@Repository
public interface ReserveDateRepo extends CrudRepository<ReserveDate, Long> {

}
