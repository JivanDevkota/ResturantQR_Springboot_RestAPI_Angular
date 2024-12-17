package com.nsu.resturantqr.service;

import com.nsu.resturantqr.dto.MenuItemDto;
import org.springframework.data.domain.Page;

public interface MenuItemsService {
    public Page<MenuItemDto> getMenuItem(int page, int size,
                                         String sortBy, String sortDir);

}
