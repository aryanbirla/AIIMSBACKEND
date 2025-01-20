package com.ckeditor.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ckeditor.entity.UserInfo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoDetails implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;  // Adding serialVersionUID for Serializable compatibility

    private String username; 
    private String password;
    private List<GrantedAuthority> authorities;

    // Constructor that maps UserInfo entity to UserDetails
    public UserInfoDetails(UserInfo userInfo) {
        this.username = userInfo.getEmail(); // Assuming 'email' is used as 'username'
        this.password = userInfo.getPassword();
        // Assuming 'roles' is a comma-separated string
        this.authorities = List.of(userInfo.getRoles().split(","))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Implement this if you need to check account expiry
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Implement this if you need to check if the account is locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Implement this if you need to check if credentials are expired
    }

    @Override
    public boolean isEnabled() {
        return true;  // Implement this if you need to check if the user is enabled
    }
}
