package com.ckeditor.requests;

import jakarta.persistence.Lob;

public class ContentRequest {
	@Lob
	private String content;
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ContentRequest(String content, long id) {
		super();
		this.content = content;
		this.id = id;
	}

	public ContentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
