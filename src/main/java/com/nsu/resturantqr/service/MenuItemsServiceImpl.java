package com.nsu.resturantqr.service;

import com.nsu.resturantqr.dto.MenuDto;
import com.nsu.resturantqr.dto.MenuItemDto;
import com.nsu.resturantqr.helper.ImageHelper;
import com.nsu.resturantqr.model.*;
import com.nsu.resturantqr.model.Menu;
import com.nsu.resturantqr.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
public class MenuItemsServiceImpl implements MenuItemsService{

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private MenuRepository menuRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MenuItemDto createMenuItem(MenuItemDto menuItemDto
    , MultipartFile multipartFile) {
        String filePath;
        Category category = categoryRepository.findById(menuItemDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Menu menu = menuRepository.findById(menuItemDto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        MenuItems menuItems = modelMapper.map(menuItemDto, MenuItems.class);

        try{
        filePath= ImageHelper.uploadFile(multipartFile);  //img/....jpg
        }catch (IOException ex){
            throw new RuntimeException("Error while uploading file"+ ex.getMessage());
        }
        menuItems.setImagePath(filePath);
        menuItems.setMenu(menu);
        menuItems.setCategory(category);

        MenuItems savedMenu = menuItemsRepository.save(menuItems);
        return modelMapper.map(savedMenu, MenuItemDto.class);
    }



    public Menu createMenu(MenuDto menuDto) {
        Restaurant restaurant = restaurantRepository.findById(menuDto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("restaurant not found"));
        Menu menu = new Menu();
        menu.setRestaurant(restaurant);
//        menuDto.setRestaurantId(restaurant.getId());
           return menuRepository.save(menu);
    }

    public Page<MenuItemDto> getMenuItem(int page, int size,
                                         String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ?Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(page, size, sort);

        Page<MenuItems> menuItems = menuItemsRepository.findAll(pageable);
        long totalElements = menuItems.getTotalElements();
        System.out.println(totalElements);
        List<MenuItems> content = menuItems.getContent();
        System.out.println(content);
        return menuItems.map(menuItem -> modelMapper.map(menuItem, MenuItemDto.class));
    }

}
