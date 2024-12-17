package com.nsu.resturantqr.controller;

import com.nsu.resturantqr.dto.CategoryDto;
import com.nsu.resturantqr.dto.MenuItemDto;
import com.nsu.resturantqr.model.Category;
import com.nsu.resturantqr.model.MenuItems;
import com.nsu.resturantqr.service.MenuItemsService;
import com.nsu.resturantqr.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuItemsService menuItemsService;

    @PostMapping("/create/category")
    public ResponseEntity<Category> CreateCategory(CategoryDto categoryDto) {
        Category category = restaurantService.CreateCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/all/category")
    public ResponseEntity<List<Category>> GetAllCategories() {
        List<Category> categories = restaurantService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("/all/menuitems")
    public ResponseEntity<?>getMenuItemPaginated(@RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
                                                 @RequestParam(name ="pageSize",defaultValue = "5")int pageSize,
                                                 @RequestParam(name ="sortBy",defaultValue = "id")String sortBy,
                                                 @RequestParam(name ="sortDir",defaultValue = "asc")String sortDir){

        Page<MenuItemDto> menuItem = menuItemsService.getMenuItem(pageNo, pageSize, sortBy, sortDir);
        if (ObjectUtils.isEmpty(menuItem.getContent())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(menuItem.getContent(),HttpStatus.OK);
    }

    @GetMapping("/all/menuitems/of/category{categoryId}")
    public ResponseEntity<?>getMenuItemPaginatedByCategoryId(
            @PathVariable("categoryId")Long categoryId,
            @RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
            @RequestParam(name ="pageSize",defaultValue = "5")int pageSize,
            @RequestParam(name ="sortBy",defaultValue = "id")String sortBy,
            @RequestParam(name ="sortDir",defaultValue = "asc")String sortDir){

        Page<MenuItems> menuItem = restaurantService.getAllMenuItemsByCategory(categoryId,pageNo, pageSize, sortBy, sortDir);
        if (ObjectUtils.isEmpty(menuItem.getContent())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(menuItem.getContent(),HttpStatus.OK);
    }


}
