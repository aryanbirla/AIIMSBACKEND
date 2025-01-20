package com.ckeditor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ckeditor.entity.FileUpload;
import com.ckeditor.repository.FileUploadRepository;

@Service
public class FileUploadService {
	
	@Autowired
	private FileUploadRepository fileRepo;
	
	public void saveFile(FileUpload file) {
		 fileRepo.save(file);
	}
	
	public List<FileUpload> getAllFiles(){
		return fileRepo.findByvalidTrue();
	}
	
	public void deleteFile(Long id) {
		 fileRepo.deleteById(id);
	}

}
