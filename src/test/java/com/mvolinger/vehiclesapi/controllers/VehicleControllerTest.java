package com.mvolinger.vehiclesapi.controllers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.mvolinger.vehiclesapi.dto.FuelConsumptionDTO;
import com.mvolinger.vehiclesapi.dto.VehicleDTO;
import com.mvolinger.vehiclesapi.services.VehicleService;
import java.util.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest extends AbstractRestController {

  private static final String VEHICLE_NAME = String.valueOf(new Random().nextInt());
  private static final String VEHICLE_BRAND = String.valueOf(new Random().nextInt());
  private static final String VEHICLE_MODEL = String.valueOf(new Random().nextInt());
  private static final Double CITY_FUEL_CONSUMPTION = 0.415540283284770;
  private static final Double HIGHWAY_FUEL_CONSUMPTION = 0.5489156548180;
  private static final Double CONSUMED_FUEL = 0.28125852864654;
  private static final Double AMOUNT_SPENT = 0.652686842318598;
  private static final Double FUEL_PRICE = 0.549841564;
  private static final Double CITY_KM = 0.948541864561;
  private static final Double HIGHWAY_KM = 0.751884132547;

  @Mock
  private VehicleService vehicleService;

  @InjectMocks
  private VehicleController vehicleController;

  private MockMvc mockMvc;

  private VehicleDTO vehicleDTO;

  private FuelConsumptionDTO fuelConsumptionDTO;

  @BeforeEach
  public void setUp() {

    mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();

    vehicleDTO = VehicleDTO.builder()
       .name(VEHICLE_NAME)
       .brand(VEHICLE_BRAND)
       .model(VEHICLE_MODEL)
       .cityFuelConsumption(CITY_FUEL_CONSUMPTION)
       .highwayFuelConsumption(HIGHWAY_FUEL_CONSUMPTION)
       .build();

    fuelConsumptionDTO = FuelConsumptionDTO.builder()
       .name(VEHICLE_NAME)
       .brand(VEHICLE_BRAND)
       .model(VEHICLE_MODEL)
       .consumedFuel(CONSUMED_FUEL)
       .amountSpent(AMOUNT_SPENT)
       .build();
  }

  @AfterEach
  public void tearDown() {
    verifyNoMoreInteractions(vehicleService);
  }

  @Test
  public void shouldCreateNewVehicle() {
    VehicleDTO expectedOutput = new VehicleDTO();

    Mockito.when(vehicleService.createVehicle(vehicleDTO)).thenReturn(expectedOutput);

    VehicleDTO output = vehicleController.createVehicle(vehicleDTO);

    Assertions.assertEquals(expectedOutput, output);

    Mockito.verify(vehicleService, times(1)).createVehicle(vehicleDTO);
    Mockito.verifyNoMoreInteractions(vehicleService);
  }

  @Test
  public void shouldCreateNewVehicleSuccessfully() throws Exception {
    when(vehicleService.createVehicle((any(VehicleDTO.class)))).thenReturn(vehicleDTO);
    mockMvc.perform(post(VehicleController.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(vehicleDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name", equalTo(VEHICLE_NAME)))
            .andExpect(jsonPath("$.brand", equalTo(VEHICLE_BRAND)))
            .andExpect(jsonPath("$.model", equalTo(VEHICLE_MODEL)))
            .andExpect(jsonPath("$.cityFuelConsumption", equalTo(CITY_FUEL_CONSUMPTION)))
            .andExpect(jsonPath("$.highwayFuelConsumption", equalTo(HIGHWAY_FUEL_CONSUMPTION)));

    then(vehicleService).should(times(1)).createVehicle(vehicleDTO);
  }

  @Test
  public void shouldGetFuelConsumption() {
    List<FuelConsumptionDTO> expectedOutput = new ArrayList<>();

    Mockito.when(vehicleService.getFuelConsumption(FUEL_PRICE, CITY_KM, HIGHWAY_KM)).thenReturn(expectedOutput);

    List<FuelConsumptionDTO> output = vehicleController.getFuelConsumption(FUEL_PRICE, CITY_KM, HIGHWAY_KM);

    Assertions.assertEquals(expectedOutput, output);

    Mockito.verify(vehicleService, times(1)).getFuelConsumption(FUEL_PRICE, CITY_KM, HIGHWAY_KM);
    Mockito.verifyNoMoreInteractions(vehicleService);
  }

  @Test
  public void shouldGetFuelConsumptionSuccessfully() throws Exception {
    when(vehicleService.getFuelConsumption(FUEL_PRICE, CITY_KM, HIGHWAY_KM)).thenReturn(Collections.singletonList(fuelConsumptionDTO));
    mockMvc.perform(get(VehicleController.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .param(VehicleController.FUEL_PRICE, String.valueOf(FUEL_PRICE))
            .param(VehicleController.CITY_KM, String.valueOf(CITY_KM))
            .param(VehicleController.HIGHWAY_KM, String.valueOf(HIGHWAY_KM)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name", equalTo(VEHICLE_NAME)))
            .andExpect(jsonPath("$[0].brand", equalTo(VEHICLE_BRAND)))
            .andExpect(jsonPath("$[0].model", equalTo(VEHICLE_MODEL)))
            .andExpect(jsonPath("$[0].consumedFuel", equalTo(CONSUMED_FUEL)))
            .andExpect(jsonPath("$[0].amountSpent", equalTo(AMOUNT_SPENT)));

    then(vehicleService).should(times(1)).getFuelConsumption(FUEL_PRICE, CITY_KM, HIGHWAY_KM);
  }

}