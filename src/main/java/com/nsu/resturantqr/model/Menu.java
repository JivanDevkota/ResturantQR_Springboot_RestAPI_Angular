package com.nsu.resturantqr.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Data
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL)
    private List<MenuItems>menuItems;

    @OneToOne(mappedBy = "menu")
    private Restaurant restaurant;
}
