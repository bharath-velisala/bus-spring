package com.mindtree.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mindtree.bus.entity.Bus;
@Repository
public interface BusRepository extends JpaRepository<Bus, Integer>{
	public Bus getBusByBusName(String busName);
	

}
