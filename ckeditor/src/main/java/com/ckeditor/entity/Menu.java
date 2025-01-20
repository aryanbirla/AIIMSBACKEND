package com.ckeditor.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Menu {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long menu_id;
	
	private String menu_name;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	@JsonBackReference // BY ENABLING THIS I AM ABLE TO ADD AND UPDATE THE CHANGES FROM THE MENU MASTER 
//	@JsonIgnoreProperties("children") // BY ENABLING THIS THE PARENT IN THE CONTENT MASTER IS FILLING AUTOMATICALLY
    private Menu parent; // THIS IS THE CHANGE TO BE DONE ALL OVER THE FILE 
    
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	@JsonManagedReference
    private List<Menu> children = new ArrayList<>();
    
    private Integer display_order;
    private String entry_user_id;
    private String entry_date;
    private Boolean isQuickLink;    
	private Boolean isvalid;
    private Date last_modified_date;
    @JsonProperty("last_modified_user_id")
    private String last_modified_userid;  
    private String hindiMenuName;
    
    
    //custom getter for the parent id
    @JsonProperty("parent_id")
    public Long getParentId() {
        return parent != null ? parent.getMenu_id() : null;
    }

	
	public String getHindiMenuName() {
		return hindiMenuName;
	}
	public void setHindiMenuName(String hindiMenuName) {
		this.hindiMenuName = hindiMenuName;
	}
	public Long getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	
	public Boolean getIsQuickLink() {
		return isQuickLink;
	}
	public void setIsQuickLink(Boolean isQuickLink) {
		this.isQuickLink = isQuickLink;
	}
	
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public Integer getDisplay_order() {
		return display_order;
	}
	public void setDisplay_order(Integer display_order) {
		this.display_order = display_order;
	}
	public String getEntry_user_id() {
		return entry_user_id;
	}
	public void setEntry_user_id(String entry_user_id) {
		this.entry_user_id = entry_user_id;
	}
	public String getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}
	public Boolean getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}
	public Date getLast_modified_date() {
		return last_modified_date;
	}
	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}
	public String getLast_modified_userid() {
		return last_modified_userid;
	}
	public void setLast_modified_userid(String last_modified_userid) {
		this.last_modified_userid = last_modified_userid;
	}

	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Menu(Long menu_id, String menu_name, Menu parent, List<Menu> children, Integer display_order,
			String entry_user_id, String entry_date, Boolean isQuickLink, Boolean isvalid, Date last_modified_date,
			String last_modified_userid, String hindiMenuName) {
		super();
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.parent = parent;
		this.children = children;
		this.display_order = display_order;
		this.entry_user_id = entry_user_id;
		this.entry_date = entry_date;
		this.isQuickLink = isQuickLink;
		this.isvalid = isvalid;
		this.last_modified_date = last_modified_date;
		this.last_modified_userid = last_modified_userid;
		this.hindiMenuName = hindiMenuName;
	}
   
	
    
    
	
}
