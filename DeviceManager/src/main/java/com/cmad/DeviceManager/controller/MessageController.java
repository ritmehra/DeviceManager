package com.cmad.DeviceManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmad.DeviceManager.Exception.DeviceNotFoundException;
import com.cmad.DeviceManager.Exception.InvalidMessageException;
import com.cmad.DeviceManager.domain.Message;
import com.cmad.DeviceManager.service.MessageServiceIf;


@RestController
public class MessageController {
	
	@Autowired
	private MessageServiceIf messageService;

	@GetMapping("/messages")
	public List<Message> geMessages(@RequestBody Message message) throws DeviceNotFoundException{
		
		return null;
	}
	
	@PostMapping("/device/{deviceName}/message")
	public ResponseEntity<Message> postMessage(@PathVariable (value = "deviceName") String deviceName, @RequestBody Message message){
		try {
			System.out.println("deviceName:"+deviceName);
			messageService.addMessage(deviceName,message);
			return new ResponseEntity<Message>(message, HttpStatus.CREATED);
		} catch (InvalidMessageException ime) {
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
