package com.codingdojo.qa3ati.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codingdojo.qa3ati.models.City;
import com.codingdojo.qa3ati.models.Hall;

@Repository
public interface HallRepo extends CrudRepository<Hall, Long> {
	List<Hall> findAll();
	
//	@Query(value = "SELECT * FROM halls JOIN cities ON cities.id = halls.city_id "
//			+ "JOIN users_booked_halls ON users_booked_halls.hall_id = halls.id "
//			+ "JOIN users ON users.id = users_booked_halls.booker_id "
//			+ "JOIN dates ON users.id = dates.booker_id "
//			+ "JOIN halls_dates ON halls_dates.reserve_date_id = dates.id "
//			+ "WHERE "
//			+ "(cities.city_name = :cityName OR :cityName IS null) "
//			+ "AND (halls.basic_price >= :minP OR :minP IS null) "
//			+ "AND (halls.basic_price <= :maxP OR :maxP IS null) "
//			+ "AND (halls.capacity >= :minC OR :minC IS null) "
//			+ "AND (halls.capacity <= :maxC OR :maxC IS null) "
//			+ "AND (dates.date >= :minD OR :minD IS null) "
//			+ "AND (dates.date <= :maxD OR :maxD IS null) "
//			+ "AND (dates.from_hour >= :minH OR :minH IS null) "
//			+ "AND (dates.to_hour <= :maxH OR :maxH IS null)",
//			nativeQuery = true)
//	List<Hall> findHallByQuery(
//			@Param("cityName") String cityName,
//			@Param("minP") Integer minPrice,
//			@Param("maxP") Integer maxPrice,
//			@Param("minC") Integer minCapacity,
//			@Param("maxC") Integer maxCapacity,
//			@Param("minD") Date minDate,
//			@Param("maxD") Date maxDate,
//			@Param("minH") Integer minHour,
//			@Param("maxH") Integer maxHour);
	
	@Query(value = "SELECT * FROM halls JOIN cities ON cities.id = halls.city_id WHERE "
			+ "(cities.city_name = :cityName OR :cityName IS null) "
			+ "AND (halls.basic_price >= :minP OR :minP IS null) "
			+ "AND (halls.basic_price <= :maxP OR :maxP IS null) "
			+ "AND (halls.capacity >= :minC OR :minC IS null) "
			+ "AND (halls.capacity <= :maxC OR :maxC IS null)",
			nativeQuery = true)
	List<Hall> findHallByQuery(
			@Param("cityName") String cityName,
			@Param("minP") Integer minPrice,
			@Param("maxP") Integer maxPrice,
			@Param("minC") Integer minCapacity,
			@Param("maxC") Integer maxCapacity);
	
}
