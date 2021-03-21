package com.mvolinger.vehiclesapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuelConsumptionDTO {

  private String name;

  private String brand;

  private String model;

  @JsonProperty(value= "year")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
  private LocalDate manufactureDate;

  private Double consumedFuel;

  private Double amountSpent;

}
