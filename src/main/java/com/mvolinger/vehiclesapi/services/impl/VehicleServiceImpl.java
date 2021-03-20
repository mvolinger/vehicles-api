package com.mvolinger.vehiclesapi.services.impl;

import com.mvolinger.vehiclesapi.dto.VehicleDTO;
import com.mvolinger.vehiclesapi.dto.FuelConsumptionDTO;
import com.mvolinger.vehiclesapi.entities.Vehicle;
import com.mvolinger.vehiclesapi.repositories.VehicleRepository;
import com.mvolinger.vehiclesapi.services.VehicleService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

  @NonNull
  private final VehicleRepository vehicleRepository;

  @Override
  public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
      vehicleRepository.save(new Vehicle(vehicleDTO));
      return vehicleDTO;
  }

  @Override
  public List<FuelConsumptionDTO> getFuelConsumption(Double fuelPrice, Double cityKm, Double highwayKm) {
      List<FuelConsumptionDTO> fuelConsumptionDTOS = new ArrayList<>();

      List<Vehicle> vehicles = vehicleRepository.findAll();

      vehicles.forEach(vehicle -> {
          Double totalFuelConsumption = computeFuelConsumption(vehicle, cityKm, highwayKm);
          Double totalAmountSpent = computeAmountSpent(totalFuelConsumption, fuelPrice);

          FuelConsumptionDTO fuelConsumptionDTO = new FuelConsumptionDTO();
          fuelConsumptionDTO.setName(vehicle.getName());
          fuelConsumptionDTO.setBrand(vehicle.getBrand());
          fuelConsumptionDTO.setModel(vehicle.getModel());
          fuelConsumptionDTO.setManufactureDate(vehicle.getManufactureDate());
          fuelConsumptionDTO.setConsumedFuel(totalFuelConsumption);
          fuelConsumptionDTO.setAmountSpent(totalAmountSpent);

          fuelConsumptionDTOS.add(fuelConsumptionDTO);
      });

      fuelConsumptionDTOS.sort(Comparator.comparing(FuelConsumptionDTO::getAmountSpent));

      return fuelConsumptionDTOS;
  }

  private Double computeFuelConsumption(Vehicle vehicle, Double cityKm, Double highwayKm) {
      return (cityKm / vehicle.getCityFuelConsumption()) + (highwayKm / vehicle.getHighwayFuelConsumption());
  }

  private Double computeAmountSpent(Double consumedFuel, Double fuelPrice) {
      return fuelPrice * consumedFuel;
  }

}


