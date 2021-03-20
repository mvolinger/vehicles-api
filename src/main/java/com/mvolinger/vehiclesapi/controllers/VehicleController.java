package com.mvolinger.vehiclesapi.controllers;

import com.mvolinger.vehiclesapi.dto.VehicleDTO;
import com.mvolinger.vehiclesapi.dto.FuelConsumptionDTO;
import com.mvolinger.vehiclesapi.services.VehicleService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api("The Vehicles API")
@Slf4j
@RestController
@RequestMapping(VehicleController.BASE_URL)
@AllArgsConstructor
public class VehicleController {

  public static final String BASE_URL = "/api/vehicle";

  public static final String FUEL_PRICE = "fuelPrice";
  public static final String CITY_KM = "cityKm";
  public static final String HIGHWAY_KM = "highwayKm";

  private final VehicleService vehicleService;

  @ApiOperation(value = "Perform the creation of a vehicle", notes = "Attention! manufactureDate must be dd-MM-yyyy")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = "Successfully created vehicle")
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VehicleDTO createVehicle(@RequestBody VehicleDTO vehicleDTO) {
      return vehicleService.createVehicle(vehicleDTO);
  }

  @ApiOperation(value = "Get information about vehicles fuel consumption by params")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Information about fuel consumption gotten successfully")
  })
  @GetMapping(params = {FUEL_PRICE, CITY_KM, HIGHWAY_KM})
  @ResponseStatus(HttpStatus.OK)
  public List<FuelConsumptionDTO> getFuelConsumption(
          @RequestParam(FUEL_PRICE) Double fuelPrice,
          @RequestParam(CITY_KM) Double cityKm,
          @RequestParam(HIGHWAY_KM) Double highwayKm){
      return vehicleService.getFuelConsumption(fuelPrice, cityKm, highwayKm);
  }
}
