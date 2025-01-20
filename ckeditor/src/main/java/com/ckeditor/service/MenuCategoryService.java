package com.ckeditor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ckeditor.entity.MenuEntity;
import com.ckeditor.repository.MenuCategoryRepository;

@Service
public class MenuCategoryService {

    @Autowired
    private MenuCategoryRepository menuRepository;

    // Fetches top-level categories (without children)
    public List<MenuEntity> getTopLevelCategories() {
        return menuRepository.findTopLevelCategories();
    }

    // Fetches all categories (no recursive fetching of children)
    public List<MenuEntity> getAllCategories() {
        return menuRepository.findAll();
    }

    // Fetches nested categories (categories organized in a parent-child structure)
    public List<MenuEntity> getNestedCategories() {
        List<MenuEntity> allCategories = menuRepository.findAll();
        return buildNestedCategories(allCategories);
    }

    // Builds the nested categories structure (recursive logic to organize categories)
    private List<MenuEntity> buildNestedCategories(List<MenuEntity> allCategories) {
        List<MenuEntity> topLevelCategories = new ArrayList<>();

        // Iterate through all categories and organize them into a tree structure
        for (MenuEntity category : allCategories) {
            if (category.getParent() == null) {
                // Top-level categories (parent is null)
                topLevelCategories.add(category);
            } else {
                // Find the parent and add the category as a child
                MenuEntity parent = category.getParent();
                parent.getChildren().add(category); // Add the category as a child to its parent
            }
            
            
        }
        
        return topLevelCategories;
    }
}
