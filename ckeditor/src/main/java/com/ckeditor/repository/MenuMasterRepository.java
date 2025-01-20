package com.ckeditor.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ckeditor.entity.Menu;

@Repository
public interface MenuMasterRepository extends JpaRepository<Menu,Long> {
	List<Menu> findByIsvalidTrue();
}
