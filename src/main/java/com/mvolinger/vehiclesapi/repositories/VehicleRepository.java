package com.mvolinger.vehiclesapi.repositories;

import com.mvolinger.vehiclesapi.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
