package com.nsu.resturantqr.dto;

import lombok.Data;

@Data
public class MenuItemDto {

    private Long id;
    private String name;
    private double price;
    private double quantity;
    private String description;
    private String ImagePath;
    private Long menuId;
    private Long categoryId;
}
