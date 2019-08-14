package com.cmad.DeviceManager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cmad.DeviceManager.domain.ApplicationUser;

@Repository
public interface UserRepository extends MongoRepository<ApplicationUser, String>{

	ApplicationUser findByUserName(String name);
}
