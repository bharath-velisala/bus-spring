package com.mindtree.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mindtree.bus.entity.Bus;
import com.mindtree.bus.entity.Passenger;
@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer>{
	public List<Passenger>getPassengerByBus(Bus bus);

}
