package com.codingdojo.qa3ati.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dates")
public class ReserveDate {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
//	@NotNull
//	private int day = 1;
//	
//	@NotNull
//	private String month = "Jan";
//	
//	@NotNull
//	private int year = 2022;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	@NotNull
	private Integer fromHour;
	
	@NotNull
	private Integer toHour;
	
	@NotNull
	private boolean isAvailable = true;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booker_id")
	private User booker;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "halls_dates",
			joinColumns = @JoinColumn(name = "reserve_date_id"),
			inverseJoinColumns = @JoinColumn(name = "hall_id")
			)
	private List<Hall> halls;
			
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
//    Constructor
    public ReserveDate() {}
    
//    Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public int getDay() {
//		return day;
//	}
//	public void setDay(int day) {
//		this.day = day;
//	}
//	public String getMonth() {
//		return month;
//	}
//	public void setMonth(String month) {
//		this.month = month;
//	}
//	public int getYear() {
//		return year;
//	}
//	public void setYear(int year) {
//		this.year = year;
//	}
	public List<Hall> getHalls() {
		return halls;
	}
	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
//	public List<TimeSlot> getTimeSlots() {
//		return timeSlots;
//	}
//	public void setTimeSlots(List<TimeSlot> timeSlots) {
//		this.timeSlots = timeSlots;
//	}
	public Integer getFromHour() {
		return fromHour;
	}
	public void setFromHour(Integer fromHour) {
		this.fromHour = fromHour;
	}
	public Integer getToHour() {
		return toHour;
	}
	public void setToHour(Integer toHour) {
		this.toHour = toHour;
	}
	public boolean getIsAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public User getBooker() {
		return booker;
	}
	public void setBooker(User booker) {
		this.booker = booker;
	}
	
}
