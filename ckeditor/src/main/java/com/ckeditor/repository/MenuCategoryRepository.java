package com.ckeditor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ckeditor.entity.MenuEntity;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuEntity, Long> {

    // Fetch top-level categories (categories with no parent)
    @Query("SELECT c FROM MenuEntity c WHERE c.parent IS NULL")
    List<MenuEntity> findTopLevelCategories();

    // Fetch all categories (for building the nested structure)
    List<MenuEntity> findAll();
}
