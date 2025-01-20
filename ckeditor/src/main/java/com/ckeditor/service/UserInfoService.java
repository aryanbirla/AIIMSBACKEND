package com.ckeditor.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ckeditor.entity.UserInfo;
import com.ckeditor.repository.UserInfoRepository;


import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByEmail(username); // Assuming 'email' is used as username

        // Converting UserInfo to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
    
    public UserInfo searchByUsername(String username) {
    	Optional<UserInfo> userinfo = repository.findByEmail(username);
    	UserInfo userDetails = new UserInfo();
    	if(userinfo.isPresent()) {
    		userDetails = userinfo.get();
		
    	}
    	return userDetails;
    }
   
    public String addUser(UserInfo userInfo) {
     userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        
        // Log before saving
        System.out.println("Saving user: " + userInfo);
        
        // Save user
        userInfo = repository.save(userInfo);
        
        // Log after saving
        System.out.println("User saved: " + userInfo);
        
        return "User Added Successfully";
    }

}
