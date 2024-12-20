package com.nsu.resturantqr.repository;

import com.nsu.resturantqr.model.Category;
import com.nsu.resturantqr.model.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
