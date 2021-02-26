package com.mindtree.bus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.bus.entity.Bus;
import com.mindtree.bus.entity.Passenger;
import com.mindtree.bus.exceptions.BusException;
import com.mindtree.bus.exceptions.ResourceNotFoundException;
import com.mindtree.bus.repository.BusRepository;
import com.mindtree.bus.repository.PassengerRepository;
import com.mindtree.bus.service.busInterface;


@Service
public class ServiceImpl implements busInterface {

	@Autowired
	private BusRepository busrepo;

	@Autowired
	private PassengerRepository passengerrepo;

	@Override
	public Bus addBus(Bus bus) throws BusException {
		try {
			return busrepo.save(bus);
		} catch (Exception e) {
			throw new BusException(e);
		}
		
	}

	@Override
	public Passenger addPassenger(Passenger passenger, int busId) throws BusException {
		Bus bus=null;
		try {
			bus = busrepo.findById(busId).orElseThrow(()-> new ResourceNotFoundException("bus not found with id: "+busId));
		} catch (ResourceNotFoundException e) {
			throw new BusException(e);
		}
		if(bus!=null) {
			passenger.setBus(bus);
		}
			return passengerrepo.save(passenger);
	}

	@Override
	public List<Passenger> getAllPassengersByBus(String busName) throws BusException {
		List<Passenger> passengers = passengerrepo.getPassengerByBus(busrepo.getBusByBusName(busName));

		if (passengers.isEmpty()) {
			throw new BusException("bus name not found ");
		}
		return passengers;
	}

	@Override
	public boolean updatePassengerById(int passengerId, int age) throws BusException {
		Passenger passenger=null;
		try {
			passenger = passengerrepo.findById(passengerId).orElseThrow(()-> new ResourceNotFoundException("passenger not found with id:"+passengerId));
		} catch (ResourceNotFoundException e) {
			throw new BusException(e);
		}
		if(passenger!=null) {
			passenger.setAge(age);;
			passengerrepo.save(passenger);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deletePassengerById(Integer passengerId) throws BusException {
		Passenger passenger = null;
		try {
			passenger = passengerrepo.findById(passengerId).orElseThrow(()-> new ResourceNotFoundException("passenger not found with id:"+passengerId));	
		} catch (ResourceNotFoundException e) {
			throw new BusException(e);
		}
		if (passenger != null) {
			passengerrepo.delete(passenger);
			return true;
		} else {
			return false;
		}
	}

}
