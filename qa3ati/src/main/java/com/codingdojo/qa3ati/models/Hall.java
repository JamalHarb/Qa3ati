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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="halls")
public class Hall {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message = "Hall name should be provided.")
	private String name;
	
	@NotNull
	@Min(value = 1, message = "Price must be greater than zero.")
	private int basicPrice;
	
	@NotNull
	@Min(value = 1, message = "capacity must be greater than zero.")
	private int capacity;
	
	@NotNull
	private String description;
	
	@NotNull
	private String phoneNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="creator_id")
	private User creator;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "users_booked_halls",
    		joinColumns = @JoinColumn(name = "hall_id"),
    		inverseJoinColumns = @JoinColumn(name = "booker_id")
    		)
    private List<User> bookers;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;
	
	@OneToMany(mappedBy = "hall", fetch = FetchType.LAZY)
	private List<Feature> features;
	
	@OneToMany(mappedBy = "hall", fetch = FetchType.LAZY)
	private List<Photo> photos;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "halls_dates",
			joinColumns = @JoinColumn(name = "hall_id"),
			inverseJoinColumns = @JoinColumn(name = "reserve_date_id")
			)
	private List<ReserveDate> reserveDates;
	
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
	public Hall() {
		
	}
	
//	Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBasicPrice() {
		return basicPrice;
	}
	public void setBasicPrice(int basicPrice) {
		this.basicPrice = basicPrice;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public List<User> getBookers() {
		return bookers;
	}
	public void setBookers(List<User> bookers) {
		this.bookers = bookers;
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
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public List<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public List<ReserveDate> getReserveDates() {
		return reserveDates;
	}
	public void setReserveDates(List<ReserveDate> reserveDates) {
		this.reserveDates = reserveDates;
	}
	
}
