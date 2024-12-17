package com.nsu.resturantqr.dto;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class RestaurantDto {
    private Long id;
    private String name;
    private String address;
}
