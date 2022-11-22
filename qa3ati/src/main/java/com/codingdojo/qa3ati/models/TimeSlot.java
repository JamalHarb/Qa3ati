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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "time_slots")
public class TimeSlot {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private Integer fromHour;
	
	@NotNull
	private Integer toHour;
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(
//			name = "dates_times",
//			joinColumns = @JoinColumn(name = "time_slot_id"),
//			inverseJoinColumns = @JoinColumn(name = "reserve_date_id")
//			)
//	private List<ReserveDate> dates;
	
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
    public TimeSlot() {}
    
//    Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
//	public List<ReserveDate> getDates() {
//		return dates;
//	}
//	public void setDates(List<ReserveDate> dates) {
//		this.dates = dates;
//	}
	
}
