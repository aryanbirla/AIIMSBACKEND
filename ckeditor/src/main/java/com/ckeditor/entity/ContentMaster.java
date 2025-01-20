package com.ckeditor.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ContentMaster {

	 @Id
	 private long menu_id;
	 private String description;
	 private String content;
	 private boolean enableDates;
	 
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	 private Date from_date;
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	 private Date to_date;
	 private long entry_user_id;
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	 private Date entry_date;
	 private long last_modified_by;
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	 private Date last_modified_date;
	 private boolean isValid;
	 
	 
	 public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public long getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(long menu_id) {
		this.menu_id = menu_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isEnableDates() {
		return enableDates;
	}
	public void setEnableDates(boolean enableDates) {
		this.enableDates = enableDates;
	}
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	public long getEntry_user_id() {
		return entry_user_id;
	}
	public void setEntry_user_id(long entry_user_id) {
		this.entry_user_id = entry_user_id;
	}
	public Date getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	public long getLast_modified_by() {
		return last_modified_by;
	}
	public void setLast_modified_by(long last_modified_by) {
		this.last_modified_by = last_modified_by;
	}
	public Date getLast_modified_date() {
		return last_modified_date;
	}
	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}
	

	public ContentMaster(long menu_id, String description, String content, boolean enableDates, Date from_date,
			Date to_date, long entry_user_id, Date entry_date, long last_modified_by, Date last_modified_date,
			boolean isValid) {
		super();
		this.menu_id = menu_id;
		this.description = description;
		this.content = content;
		this.enableDates = enableDates;
		this.from_date = from_date;
		this.to_date = to_date;
		this.entry_user_id = entry_user_id;
		this.entry_date = entry_date;
		this.last_modified_by = last_modified_by;
		this.last_modified_date = last_modified_date;
		this.isValid = isValid;
	}
	public ContentMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
