package com.cmad.DeviceManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmad.DeviceManager.Exception.DeviceNotFoundException;
import com.cmad.DeviceManager.domain.Message;
import com.cmad.DeviceManager.service.MessageServiceIf;


@RestController
public class MessageController {
	
	@Autowired
	private MessageServiceIf messageService;

	@GetMapping("/messages")
	public List<Message> geMessages(){
		
		return null;
	}
	
	@GetMapping("/messages/filter")
	public List<Message> geFilteredMessages(@RequestBody Message message) throws DeviceNotFoundException{
		
		return null;
	}
}
