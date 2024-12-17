package com.nsu.resturantqr.repository;


import com.nsu.resturantqr.model.MenuItems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems,Long> {

    Page<MenuItems> getMenuItemsByCategoryId(Long id, Pageable pageable);
}
