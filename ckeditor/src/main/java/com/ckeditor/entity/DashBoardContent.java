package com.ckeditor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DashBoardContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String content;
	
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
	public DashBoardContent(long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	public DashBoardContent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
