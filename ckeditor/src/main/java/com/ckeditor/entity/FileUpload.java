package com.ckeditor.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FileUpload {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long file_id;
	
	private String file_name;
	
	private String file_link;
	
	private long enter_user_id;
	
	private Date entry_date;
	
	private long last_modified_user_id ;

	private Date last_modified_date;
	
	private boolean valid;
	
	private String rendering_name;

	public FileUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileUpload(long file_id, String file_name, String file_link, long enter_user_id, Date entry_date,
			long last_modified_user_id, Date last_modified_date, boolean valid, String rendering_name) {
		super();
		this.file_id = file_id;
		this.file_name = file_name;
		this.file_link = file_link;
		this.enter_user_id = enter_user_id;
		this.entry_date = entry_date;
		this.last_modified_user_id = last_modified_user_id;
		this.last_modified_date = last_modified_date;
		this.valid = valid;
		this.rendering_name = rendering_name;
	}

	public String getRendering_name() {
		return rendering_name;
	}

	public void setRendering_name(String rendering_name) {
		this.rendering_name = rendering_name;
	}

	public long getFile_id() {
		return file_id;
	}

	public void setFile_id(long file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_link() {
		return file_link;
	}

	public void setFile_link(String file_link) {
		this.file_link = file_link;
	}

	public long getEnter_user_id() {
		return enter_user_id;
	}

	public void setEnter_user_id(long enter_user_id) {
		this.enter_user_id = enter_user_id;
	}

	

	public long getLast_modified_user_id() {
		return last_modified_user_id;
	}

	public void setLast_modified_user_id(long last_modified_user_id) {
		this.last_modified_user_id = last_modified_user_id;
	}

	

	public Date getEntry_date() {
		return entry_date;
	}



	public void setEntry_date(Date entryDate) {
		this.entry_date = entryDate;
	}



	public Date getLast_modified_date() {
		return last_modified_date;
	}



	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}



	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
