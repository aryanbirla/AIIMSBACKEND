package com.ckeditor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ckeditor.entity.ContentMaster;
import com.ckeditor.service.ContentMasterService;

@RestController
@RequestMapping("/api/content")
public class ContentMasterController {

	@Autowired
	private ContentMasterService contentservice;
	
	@GetMapping("/all")
	public List<ContentMaster> getContentList() {
		return contentservice.getAllList();
	}
	
	@GetMapping("/{id}")
	public ContentMaster getContent(@PathVariable("id") Long id) {

		Optional<ContentMaster> optionalContent = contentservice.getContentById(id);
		
		ContentMaster content = new ContentMaster();
		if(optionalContent.isPresent()) {
			 content = optionalContent.get();
		}
		
		return content;
	}
	
	
	
	@PostMapping("/save")
	public ContentMaster addContent(@RequestBody ContentMaster content) {
		return contentservice.createContent(content);
	}
	
	@PostMapping("/delete")
	public void Delete(@RequestParam Long menu_id) {
		 contentservice.deleteContent(menu_id);
	}
	
}
