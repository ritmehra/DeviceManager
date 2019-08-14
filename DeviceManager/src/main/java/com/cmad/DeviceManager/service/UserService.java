package com.cmad.DeviceManager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmad.DeviceManager.domain.ApplicationUser;
import com.cmad.DeviceManager.repository.UserRepository;

@Service
public class UserService implements UserServiceIf{
	
	@Autowired
	UserRepository applicationUserRepository;
	
	
	public ApplicationUser getUserDetails(String name) {
		ApplicationUser applicationUser = applicationUserRepository.findByUserName(name);
	    return applicationUser;
    }
	
}
