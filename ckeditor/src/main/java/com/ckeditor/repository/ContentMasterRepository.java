package com.ckeditor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ckeditor.entity.ContentMaster;

@Repository
public interface ContentMasterRepository extends JpaRepository<ContentMaster,Long>{

}
