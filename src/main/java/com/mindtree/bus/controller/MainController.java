package com.mindtree.bus.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.bus.entity.Bus;
import com.mindtree.bus.entity.Passenger;
import com.mindtree.bus.exceptions.BusException;
import com.mindtree.bus.service.impl.ServiceImpl;

@RestController
@RequestMapping(path = "/busservice")
public class MainController {
	static String msg = "";

	@Autowired
	public ServiceImpl service;

	@PostMapping("/add/bus")
	public ResponseEntity<?> addBus(@RequestBody Bus bus) {
		Map<String, Object> tempBus = new HashMap<>();
		try {
			tempBus.put("body", service.addBus(bus));
			tempBus.put("msg", "bus added successfully");

		} catch (BusException e) {
			msg = e.getLocalizedMessage();
		}
		return new ResponseEntity<>(tempBus, HttpStatus.CREATED);
	}

	@PostMapping("/add/passenger/{busId}")
	public ResponseEntity<?> addPassenger(@RequestBody Passenger passenger, @PathVariable Integer busId) {
		Map<String, Object> tempPassenger = new HashMap<>();
		try {
			tempPassenger.put("body", service.addPassenger(passenger, busId));
			tempPassenger.put("msg", "passenger added successfully");
		} catch (BusException e) {
			msg = e.getLocalizedMessage();
		}
		return new ResponseEntity<>(tempPassenger, HttpStatus.CREATED);

	}

	@GetMapping("/all/{busName}")
	public ResponseEntity<?> getPassengersByBus(@PathVariable String busName) {
		try {
			return new ResponseEntity<>(service.getAllPassengersByBus(busName), HttpStatus.OK);
		} catch (BusException e) {
			msg = e.getLocalizedMessage();
		}
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/update/passenger/{passengerId}")
	public ResponseEntity<?> updatePassenger(@RequestBody int age, @PathVariable Integer passengerId) {
		boolean flag = false;
		try {
			flag = service.updatePassengerById(passengerId, age);

		} catch (BusException e) {
			msg = e.getLocalizedMessage();
		}
		if (flag == true) {
			return new ResponseEntity<>("Updated Succesfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/passenger/{passengerId}")
	public ResponseEntity<?> deletePassenger(@PathVariable Integer passengerId) {
		boolean flag = false;
		try {
			flag = service.deletePassengerById(passengerId);

		} catch (BusException e) {
			msg = e.getLocalizedMessage();
		}
		if (flag == true) {
			return new ResponseEntity<>("passenger deleted Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
	}

}
