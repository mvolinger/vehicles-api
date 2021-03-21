package com.mvolinger.vehiclesapi.services;

import com.mvolinger.vehiclesapi.controllers.AbstractRestController;
import com.mvolinger.vehiclesapi.dto.FuelConsumptionDTO;
import com.mvolinger.vehiclesapi.dto.VehicleDTO;
import com.mvolinger.vehiclesapi.entities.Vehicle;
import com.mvolinger.vehiclesapi.repositories.VehicleRepository;
import com.mvolinger.vehiclesapi.services.impl.VehicleServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest extends AbstractRestController {

  private static final String VEHICLE_NAME = String.valueOf(new Random().nextInt());
  private static final String VEHICLE_BRAND = String.valueOf(new Random().nextInt());
  private static final String VEHICLE_MODEL = String.valueOf(new Random().nextInt());
  private static final Double CITY_FUEL_CONSUMPTION = 1.0;
  private static final Double HIGHWAY_FUEL_CONSUMPTION = 1.0;
  private static final Double FUEL_PRICE = 1.0;
  private static final Double CITY_KM = 1.0;
  private static final Double HIGHWAY_KM = 1.0;

  @Mock
  private VehicleRepository vehicleRepository;

  @InjectMocks
  private VehicleServiceImpl vehicleService;

  @AfterEach
  public void tearDown() {
    verifyNoMoreInteractions(vehicleRepository);
  }

  @Test
  public void shouldCreateNewVehicleSuccessfully() {
    VehicleDTO vehicleDTO = VehicleDTO.builder()
            .name(VEHICLE_NAME)
            .brand(VEHICLE_BRAND)
            .model(VEHICLE_MODEL)
            .cityFuelConsumption(CITY_FUEL_CONSUMPTION)
            .highwayFuelConsumption(HIGHWAY_FUEL_CONSUMPTION)
            .build();

    Vehicle vehicle = Vehicle.builder()
            .name(VEHICLE_NAME)
            .brand(VEHICLE_BRAND)
            .model(VEHICLE_MODEL)
            .cityFuelConsumption(CITY_FUEL_CONSUMPTION)
            .highwayFuelConsumption(HIGHWAY_FUEL_CONSUMPTION)
            .build();

    when(vehicleRepository.save(refEq(vehicle))).thenReturn(vehicle);

    vehicleService.createVehicle(vehicleDTO);

    verify(vehicleRepository, times(1)).save(refEq(vehicle));
  }

  @Test
  public void shouldGetFuelConsumptionSuccessfully() {
      Vehicle vehicle = Vehicle.builder()
              .name(VEHICLE_NAME)
              .brand(VEHICLE_BRAND)
              .model(VEHICLE_MODEL)
              .cityFuelConsumption(CITY_FUEL_CONSUMPTION)
              .highwayFuelConsumption(HIGHWAY_FUEL_CONSUMPTION)
              .build();

      List<Vehicle> allVehicles = new ArrayList<>();
      allVehicles.add(vehicle);

      when(vehicleRepository.findAll()).thenReturn(allVehicles);

      List<FuelConsumptionDTO> fuelConsumptionDTOS = vehicleService.getFuelConsumption(FUEL_PRICE, CITY_KM, HIGHWAY_KM);

      Assertions.assertEquals(1, fuelConsumptionDTOS.size());
      Assertions.assertEquals(vehicle.getName(), fuelConsumptionDTOS.get(0).getName());
      Assertions.assertEquals(vehicle.getBrand(), fuelConsumptionDTOS.get(0).getBrand());
      Assertions.assertEquals(vehicle.getModel(), fuelConsumptionDTOS.get(0).getModel());
      Assertions.assertEquals(vehicle.getManufactureDate(), fuelConsumptionDTOS.get(0).getManufactureDate());
      Assertions.assertEquals(2.0, fuelConsumptionDTOS.get(0).getConsumedFuel());
      Assertions.assertEquals(2.0, fuelConsumptionDTOS.get(0).getAmountSpent());

  }

}