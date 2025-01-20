package com.ckeditor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ckeditor.entity.Menu;
import com.ckeditor.service.MenuMasterService;

@RestController
@RequestMapping("/api/menus")
public class MenuMasterController {

    @Autowired
    private MenuMasterService menuService;

    // Fetch all menus
    @GetMapping
    public List<Menu> getMenu() {
        return menuService.getAllMenus();
    }
    
    @GetMapping("/nested")
    public List<Menu> getNestedCategories() {
    	return menuService.getNestedCategories();
    }

    // Add a new menu
    @PostMapping
    public Menu addMenu(@RequestBody Menu menu, @RequestParam Long currentUserId) {
        // Call service to save the menu with the current user ID
    	System.out.println("CONTROLLER FOR ADDING THE NAME OF THE MENU LIST");
        return menuService.addMenu(menu, currentUserId);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Menu> editMenu(@PathVariable("id") Long id, @RequestBody Menu menu) {
        Optional<Menu> existingMenu = menuService.getMenuById(id);
        
        if (existingMenu.isPresent()) {
            Menu updatedMenu = existingMenu.get();
            updatedMenu.setMenu_name(menu.getMenu_name());
            
            updatedMenu.setHindiMenuName(menu.getHindiMenuName());
            
            if (menu.getParent() != null) {
                updatedMenu.setParent(menu.getParent());
            } else {
                updatedMenu.setParent(null);
            }
            
            updatedMenu.setDisplay_order(menu.getDisplay_order());
            updatedMenu.setIsvalid(menu.getIsvalid());
            updatedMenu.setLast_modified_userid(menu.getLast_modified_userid());
            updatedMenu.setLast_modified_date(menu.getLast_modified_date());
            
            updatedMenu.setIsQuickLink(menu.getIsQuickLink());

            Menu savedMenu = menuService.addMenu(updatedMenu,id);
            return ResponseEntity.ok(savedMenu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }	
    
    @PostMapping("/delete")
    public void delete( @RequestParam Long menuId) {
    	menuService.deleteMenu(menuId);
    }
}
