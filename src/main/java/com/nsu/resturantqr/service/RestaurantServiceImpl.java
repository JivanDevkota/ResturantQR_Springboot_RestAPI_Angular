package com.nsu.resturantqr.service;

import com.nsu.resturantqr.dto.CategoryDto;
import com.nsu.resturantqr.model.Category;
import com.nsu.resturantqr.model.MenuItems;
import com.nsu.resturantqr.repository.CategoryRepository;
import com.nsu.resturantqr.repository.MenuItemsRepository;
import com.nsu.resturantqr.repository.MenuRepository;
import com.nsu.resturantqr.repository.TableRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MenuItemsRepository menuItemsRepository;
    
    @Autowired
    private TableRepository tableRepository;

    public Category CreateCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return categoryRepository.save(category);
    }

    public List<Category>getAllCategories() {
        return categoryRepository.findAll();
    }

    public Page<MenuItems>getAllMenuItemsByCategory(Long categoryId, int pageNo, int pageSize,
                                                    String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy);
        Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
        Page<MenuItems> menuItemsByCategoryId = menuItemsRepository.
                getMenuItemsByCategoryId(categoryId, pageable);
        return menuItemsByCategoryId;
    }

}
