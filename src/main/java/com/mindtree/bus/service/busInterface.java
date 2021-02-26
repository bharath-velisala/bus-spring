package com.mindtree.bus.service;

import java.util.List;

import com.mindtree.bus.entity.Bus;
import com.mindtree.bus.entity.Passenger;
import com.mindtree.bus.exceptions.BusException;

public interface busInterface {
	Bus addBus(Bus bus)throws BusException;
	Passenger addPassenger(Passenger passenger,int busId) throws BusException;
	public List<Passenger>getAllPassengersByBus(String busName) throws BusException;
	boolean updatePassengerById(int Id,int age) throws BusException;
	boolean deletePassengerById(Integer passengerId) throws BusException;

}
