package com.jt.controller;

import com.jt.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;



}
