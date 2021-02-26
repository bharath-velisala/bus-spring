package com.mindtree.bus.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer passenegerId;
	private String passengerName;
	private Integer age;
	
	@ManyToOne
	@JoinColumn(name="bus_id")
	private Bus bus;

	public Passenger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Passenger(Integer passenegerId, String passengerName, Integer age, Bus bus) {
		super();
		this.passenegerId = passenegerId;
		this.passengerName = passengerName;
		this.age = age;
		this.bus = bus;
	}

	public Integer getPassenegerId() {
		return passenegerId;
	}

	public void setPassenegerId(Integer passenegerId) {
		this.passenegerId = passenegerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	@JsonBackReference
	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}
	
	
	
	
}
