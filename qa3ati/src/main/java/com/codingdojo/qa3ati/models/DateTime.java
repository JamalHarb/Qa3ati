package com.codingdojo.qa3ati.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dates_times")
public class DateTime {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_slot_id")
	private TimeSlot timeSlot;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reserve_date_id")
	private ReserveDate reserveDate;
}
