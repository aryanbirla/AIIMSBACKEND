package com.ckeditor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ckeditor.entity.DashBoardContent;
import com.ckeditor.repository.DashBoardContentRepository;

@Service
public class DashBoardContentService {
	
	@Autowired
	private DashBoardContentRepository dashBoardContentRepository;
	
	public void save(DashBoardContent content) {
		dashBoardContentRepository.save(content);
	}
	
	public DashBoardContent getContentcontent(long id) {
		return dashBoardContentRepository.findContentById(id);
	}
	
	public Optional<DashBoardContent> getContent(long id) {
		return dashBoardContentRepository.findById(id);
	}

}
