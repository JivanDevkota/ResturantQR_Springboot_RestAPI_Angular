package com.nsu.resturantqr.service;

import com.nsu.resturantqr.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl implements TableService{

    @Autowired
    private TableRepository tableRepository;


}
