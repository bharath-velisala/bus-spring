package com.mindtree.bus.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Bus {

	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;
	private String busName;
	private String busType;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="bus",fetch = FetchType.LAZY)
	private List<Passenger>passenger;

	public Bus(Integer busId, String busName, String busType, List<Passenger> players) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.busType = busType;
		this.passenger = players;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	@JsonManagedReference
	public List<Passenger> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}
	
	

	
	
}
