package com.nsu.resturantqr.service;

import com.nsu.resturantqr.dto.CategoryDto;
import com.nsu.resturantqr.model.Category;
import com.nsu.resturantqr.model.MenuItems;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RestaurantService {
    public Category CreateCategory(CategoryDto categoryDto);
    public List<Category> getAllCategories();
    Page<MenuItems> getAllMenuItemsByCategory(Long categoryId, int pageNo, int pageSize,
                                              String sortBy, String sortDir);

}
