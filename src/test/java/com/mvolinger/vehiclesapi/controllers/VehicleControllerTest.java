package com.mvolinger.vehiclesapi.controllers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.mvolinger.vehiclesapi.dto.FuelConsumptionDTO;
import com.mvolinger.vehiclesapi.dto.VehicleDTO;
import com.mvolinger.vehiclesapi.services.VehicleService;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

  private static final String VEHICLE_NAME = String.valueOf(new Random().nextInt());
  private static final String VEHICLE_BRAND = String.valueOf(new Random().nextInt());
  private static final String VEHICLE_MODEL = String.valueOf(new Random().nextInt());
  private static final Double CITY_FUEL_CONSUMPTION = 0.415540283284770;
  private static final Double HIGHWAY_FUEL_CONSUMPTION = 0.5489156548180;
  private static final Double FUEL_PRICE = 0.549841564;
  private static final Double CITY_KM = 0.948541864561;
  private static final Double HIGHWAY_KM = 0.751884132547;

  @Mock
  private VehicleService vehicleService;

  @InjectMocks
  private VehicleController vehicleController;

  @Test
  public void shouldCreateNewVehicleSuccessfully() {
    VehicleDTO vehicleDTO = VehicleDTO.builder()
            .name(VEHICLE_NAME)
            .brand(VEHICLE_BRAND)
            .model(VEHICLE_MODEL)
            .cityFuelConsumption(CITY_FUEL_CONSUMPTION)
            .highwayFuelConsumption(HIGHWAY_FUEL_CONSUMPTION)
            .build();

    VehicleDTO expectedOutput = new VehicleDTO();

    Mockito.when(vehicleService.createVehicle(vehicleDTO)).thenReturn(expectedOutput);

    VehicleDTO output = vehicleController.createVehicle(vehicleDTO);

    Assertions.assertEquals(expectedOutput, output);

    Mockito.verify(vehicleService, times(1)).createVehicle(vehicleDTO);
    verifyNoMoreInteractions(vehicleService);
  }

  @Test
  public void shouldGetFuelConsumptionSuccessfully() {
    List<FuelConsumptionDTO> expectedOutput = new ArrayList<>();

    Mockito.when(vehicleService.getFuelConsumption(FUEL_PRICE, CITY_KM, HIGHWAY_KM)).thenReturn(expectedOutput);

    List<FuelConsumptionDTO> output = vehicleController.getFuelConsumption(FUEL_PRICE, CITY_KM, HIGHWAY_KM);

    Assertions.assertEquals(expectedOutput, output);

    Mockito.verify(vehicleService, times(1)).getFuelConsumption(FUEL_PRICE, CITY_KM, HIGHWAY_KM);
    verifyNoMoreInteractions(vehicleService);
  }

}