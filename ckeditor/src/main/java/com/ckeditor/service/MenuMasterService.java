package com.ckeditor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ckeditor.entity.Menu;
import com.ckeditor.repository.MenuMasterRepository;

@Service
public class MenuMasterService {

    @Autowired
    private MenuMasterRepository menuRepository;

    // Get all menus
    public List<Menu> getAllMenus() {
        return menuRepository.findByIsvalidTrue();
    }

    // Add or update a menu
    public Menu addMenu(Menu menu, Long currentUserId) {
        // Set the current user ID as the 'last_modified_userid'
//        menu.setLast_modified_userid(String.valueOf(currentUserId));
        System.out.println("***************CURRENT USER ID***********************");
        System.out.println(currentUserId);
        System.out.println("**************************************");
        System.out.println(menu.getParent());
        
        if (menu.getParent() != null && menu.getParent().getMenu_id() != null) {
            Menu parentMenu = menuRepository.findById(menu.getParent().getMenu_id())
            .orElseThrow(() -> new IllegalArgumentException("Parent menu not found"));
            menu.setParent(parentMenu);
        }
        
        //LOGIC FOR SAVING ONLY THE DATE IN ENTRY DATE	
       //	 menu.setEntry_date(String.valueOf(menu.getEntry_date().split("T")[0])); 
        
//        menu.setLast_modified_date("set only the date here");
        
        
        // Save the menu and return the saved object
        return menuRepository.save(menu);
    }
    
    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }
    
    public void deleteMenu( Long menuId) {
    	Optional<Menu> tempMenu = menuRepository.findById(menuId);
    	
    	if(tempMenu.isPresent()) {
    		Menu menu1 = tempMenu.get();
    		
    		menu1.setIsvalid(false);
    		
    		menuRepository.save(menu1);
    	}
    	else {
    		 throw new IllegalArgumentException("Menu with ID " + menuId + " does not exist.");
    	}

    }
    
    public List<Menu> getNestedCategories() {
//    	List<Menu> allCategories = menuRepository.findAll();
    	List<Menu> allCategories = menuRepository.findByIsvalidTrue();
    	return buildNestedCategories(allCategories);
    }
    
    private List<Menu> buildNestedCategories(List<Menu> allCategories){
    	List<Menu> topLevelCategories = new ArrayList<>();
    	for(Menu category : allCategories) {
    		if(category.getParent() == null) {
    			topLevelCategories.add(category);
    		}
    		else {
    			Menu parent = category.getParent();
    			parent.getChildren().add(category);
    		}
    	}
    	return topLevelCategories;
    }
    
}
