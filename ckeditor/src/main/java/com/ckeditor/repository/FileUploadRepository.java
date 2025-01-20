package com.ckeditor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ckeditor.entity.FileUpload;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload,Long> {

	List<FileUpload> findByvalidTrue();
}
