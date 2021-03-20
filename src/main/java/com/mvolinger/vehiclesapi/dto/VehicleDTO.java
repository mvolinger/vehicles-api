package com.mvolinger.vehiclesapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

  private String name;

  private String brand;

  private String model;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate manufactureDate;

  private Double cityFuelConsumption;

  private Double highwayFuelConsumption;

}

