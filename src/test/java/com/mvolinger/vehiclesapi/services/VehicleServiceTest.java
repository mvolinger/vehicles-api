package com.mvolinger.vehiclesapi.services;

import com.mvolinger.vehiclesapi.controllers.AbstractRestController;
import com.mvolinger.vehiclesapi.dto.VehicleDTO;
import com.mvolinger.vehiclesapi.entities.Vehicle;
import com.mvolinger.vehiclesapi.repositories.VehicleRepository;
import com.mvolinger.vehiclesapi.services.impl.VehicleServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Random;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest extends AbstractRestController {

  private static final String VEHICLE_NAME = String.valueOf(new Random().nextInt());
  private static final String VEHICLE_BRAND = String.valueOf(new Random().nextInt());
  private static final String VEHICLE_MODEL = String.valueOf(new Random().nextInt());
  private static final Double CITY_FUEL_CONSUMPTION = 0.415540283284770;
  private static final Double HIGHWAY_FUEL_CONSUMPTION = 0.5489156548180;

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
}