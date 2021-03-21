package com.mvolinger.vehiclesapi.controllers;

import com.mvolinger.vehiclesapi.dto.VehicleDTO;
import com.mvolinger.vehiclesapi.entities.Vehicle;
import com.mvolinger.vehiclesapi.repositories.VehicleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import java.util.Random;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class VehicleControllerIT extends AbstractRestController {

  private static final String VEHICLE_NAME = String.valueOf(new Random().nextInt());
  private static final String VEHICLE_BRAND = String.valueOf(new Random().nextInt());
  private static final String VEHICLE_MODEL = String.valueOf(new Random().nextInt());
  private static final Double CITY_FUEL_CONSUMPTION = 0.415540283284770;
  private static final Double HIGHWAY_FUEL_CONSUMPTION = 0.5489156548180;

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private VehicleController vehicleController;

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
  }

  @AfterEach
  public void tearDown() {
    vehicleRepository.deleteAll();
  }

  @Test
  public void shouldCreateNewVehicleSuccessfully() throws Exception {
    VehicleDTO input = VehicleDTO.builder()
            .name(VEHICLE_NAME)
            .brand(VEHICLE_BRAND)
            .model(VEHICLE_MODEL)
            .cityFuelConsumption(CITY_FUEL_CONSUMPTION)
            .highwayFuelConsumption(HIGHWAY_FUEL_CONSUMPTION)
            .build();

    mockMvc.perform(post(VehicleController.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(input)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name", notNullValue()))
            .andExpect(jsonPath("$.brand", notNullValue()))
            .andExpect(jsonPath("$.model", notNullValue()))
            .andExpect(jsonPath("$.cityFuelConsumption", notNullValue()))
            .andExpect(jsonPath("$.highwayFuelConsumption", notNullValue()));

    List<Vehicle> allSavedVehicles = vehicleRepository.findAll();
    Assertions.assertEquals(1, allSavedVehicles.size());

    Vehicle savedVehicle = allSavedVehicles.get(0);
    Assertions.assertEquals(VEHICLE_NAME, savedVehicle.getName());
    Assertions.assertEquals(VEHICLE_BRAND, savedVehicle.getBrand());
    Assertions.assertEquals(VEHICLE_MODEL, savedVehicle.getModel());
    Assertions.assertEquals(CITY_FUEL_CONSUMPTION, savedVehicle.getCityFuelConsumption());
    Assertions.assertEquals(HIGHWAY_FUEL_CONSUMPTION, savedVehicle.getHighwayFuelConsumption());
  }

}