package com.ckeditor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ckeditor.entity.MenuEntity;
import com.ckeditor.service.MenuCategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private MenuCategoryService menuService;

    @GetMapping
    public List<MenuEntity> getCategories() {
        return menuService.getAllCategories();
    }

    @GetMapping("/top-level")
    public List<MenuEntity> getTopLevelCategories() {
        return menuService.getTopLevelCategories();
    }

    // Returns nested categories
    @GetMapping("/nested")
    public List<MenuEntity> getNestedCategories() {
        return menuService.getNestedCategories();
    }
}
