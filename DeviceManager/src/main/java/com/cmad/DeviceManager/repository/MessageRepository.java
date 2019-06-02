package com.cmad.DeviceManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmad.DeviceManager.domain.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

}
