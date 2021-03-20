package com.mvolinger.vehiclesapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mvolinger.vehiclesapi.dto.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String brand;

  private String model;

  @JsonFormat(pattern = "dd-MM-yyyy")
  @JsonSerialize(using= LocalDateSerializer.class)
  @JsonDeserialize(using= LocalDateDeserializer.class)
  private LocalDate manufactureDate;

  private Double cityFuelConsumption;

  private Double highwayFuelConsumption;

  private Double consumedFuel;

  private Double amountSpent;

  public Vehicle(VehicleDTO vehicleDTO){
    this.setName(vehicleDTO.getName());
    this.setBrand(vehicleDTO.getBrand());
    this.setModel(vehicleDTO.getModel());
    this.setManufactureDate(vehicleDTO.getManufactureDate());
    this.setCityFuelConsumption(vehicleDTO.getCityFuelConsumption());
    this.setHighwayFuelConsumption(vehicleDTO.getHighwayFuelConsumption());
  }
}
