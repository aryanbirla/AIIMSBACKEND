package com.ckeditor.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ckeditor.entity.DashBoardContent;
import com.ckeditor.requests.ContentRequest;
import com.ckeditor.service.DashBoardContentService;

@RestController
@RequestMapping("/content")
@CrossOrigin(origins = "http://localhost:3000")
public class DashBoardContentController {
	
	@Autowired
	private DashBoardContentService dashBoardContentService;
	
	// USING SAVE MY LOGC
//	@PostMapping("/save")
//	public void SaveContent(@RequestBody ContentRequest contentRequest){
////		DashBoardContent content = new DashBoardContent();
//		DashBoardContent content = dashBoardContentService.getContentcontent(1);
//	
//		content.setContent(contentRequest.getContent()); 
//		
//		dashBoardContentService.save(content);
//	}
	
	
	@PutMapping("/save")
	public ResponseEntity<String> updateContent(@RequestBody ContentRequest contentRequest) {
	    // Fetch the existing content by a fixed ID (e.g., 1)
	    DashBoardContent existingContent = dashBoardContentService.getContentcontent(1);

	    if (existingContent != null) {
	        // Update the content
	        existingContent.setContent(contentRequest.getContent());
	        dashBoardContentService.save(existingContent);
	        return ResponseEntity.ok("Content updated successfully.");
	    } else {
	        // Handle the case where the content does not exist
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Content not found for the given ID.");
	    }
	}

	
	@PostMapping("/get")
	public Optional<DashBoardContent> getContent(@RequestBody ContentRequest contentRequest) {
		long id = contentRequest.getId();
		return this.dashBoardContentService.getContent(id);
	}
}
