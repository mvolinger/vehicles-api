package com.mvolinger.vehiclesapi.services;

import com.mvolinger.vehiclesapi.dto.VehicleDTO;
import com.mvolinger.vehiclesapi.dto.FuelConsumptionDTO;

import java.util.List;

public interface VehicleService {

    VehicleDTO createVehicle(VehicleDTO vehicleDTO);

    List<FuelConsumptionDTO> getFuelConsumption(Double fuelPrice, Double cityKm, Double highwayKm);
}
