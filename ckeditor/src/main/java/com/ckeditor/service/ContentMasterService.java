package com.ckeditor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ckeditor.entity.ContentMaster;
import com.ckeditor.repository.ContentMasterRepository;

@Service
public class ContentMasterService {

	@Autowired
	private ContentMasterRepository contentRepo;
	
	public List<ContentMaster> getAllList(){
		return contentRepo.findAll();
	}
	
	public Optional<ContentMaster> getContentById(long id) {
		return contentRepo.findById(id);
	}
	
	public ContentMaster createContent(ContentMaster content) {
		return contentRepo.save(content);
	}
	
	public void deleteContent(long menu_id) {
		Optional<ContentMaster> optionalContent = contentRepo.findById(menu_id);
		ContentMaster content = new ContentMaster();
		
		if(optionalContent.isPresent()) {
			content = optionalContent.get();
			content.setValid(false);
			contentRepo.save(content);
		}
		else {
			throw new IllegalArgumentException("Menu with ID " + menu_id + " does not exist.");
		}

	}
}
