package com.cmad.DeviceManager.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmad.DeviceManager.Exception.DeviceNotFoundException;
import com.cmad.DeviceManager.Exception.InvalidMessageException;
import com.cmad.DeviceManager.domain.ApplicationUser;
import com.cmad.DeviceManager.domain.Message;
import com.cmad.DeviceManager.dto.DeviceStatsDto;
import com.cmad.DeviceManager.dto.MessageDto;
import com.cmad.DeviceManager.dto.MessageStatsDto;
import com.cmad.DeviceManager.service.MessageServiceIf;
import com.cmad.DeviceManager.service.UserServiceIf;


@RestController
@CrossOrigin
public class MessageController {
	
	@Autowired
	private MessageServiceIf messageService;
	
	@Autowired
    private UserServiceIf userService;
	
	@GetMapping("/messages")
	public ResponseEntity<List<MessageDto>> geMessages(Authentication authentication,@RequestParam("deviceName") String deviceName, 
			@RequestParam("severity") Integer severity){
		
		ApplicationUser user = userService.getUserDetails(authentication.getName());
		System.out.println("Logged in user :"+user.getUserName());
		
		List<MessageDto> messageDtoList = new ArrayList<MessageDto>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			List<Message> messages =null;
			
			if((deviceName != null && !deviceName.isEmpty()) || (severity!=null))
				messages = messageService.getFilteredMessages(deviceName, severity);
			else
				messages = messageService.getMessages(user);
			
			for(Message message: messages) {
				MessageDto messageDto = new MessageDto();
				messageDto.setDeviceName(message.getDevice().getDeviceName());
				messageDto.setMessage(message.getMessage());
				messageDto.setSeverity(message.getSeverity());
				messageDto.setLastUpdatedDate(dateFormat.format(message.getLastUpdatedDate()));
				
				messageDtoList.add(messageDto);
			}
		    return new ResponseEntity<List<MessageDto>>(messageDtoList, HttpStatus.OK);
		}catch(InvalidMessageException ime) {
			return new ResponseEntity<List<MessageDto>>(messageDtoList, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<MessageDto>>(messageDtoList, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/device/stats")
	public ResponseEntity<List<DeviceStatsDto>> getDeviceStats(Authentication authentication,@RequestParam("deviceName") String deviceName){
		List<DeviceStatsDto> deviceStats = new ArrayList<DeviceStatsDto>();
		ApplicationUser user = userService.getUserDetails(authentication.getName());
		
		try {
			deviceStats = messageService.getDeviceStats(user,deviceName);
			return new ResponseEntity<List<DeviceStatsDto>>(deviceStats, HttpStatus.OK);
		}catch(DeviceNotFoundException de) {
			return new ResponseEntity<List<DeviceStatsDto>>(deviceStats, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<DeviceStatsDto>>(deviceStats, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
	}
	
	@GetMapping("/message/stats")
	public ResponseEntity<List<MessageStatsDto>> getMessageStats(Authentication authentication,@RequestParam("deviceName") String deviceName,
			@RequestParam("severity") Integer severity){
		List<MessageStatsDto> messageStats = new ArrayList<MessageStatsDto>();
		ApplicationUser user = userService.getUserDetails(authentication.getName());
		
		try {
			messageStats = messageService.getMessageStats(user, deviceName, severity);
			return new ResponseEntity<List<MessageStatsDto>>(messageStats, HttpStatus.OK);
		}catch(DeviceNotFoundException de) {
			return new ResponseEntity<List<MessageStatsDto>>(messageStats, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<MessageStatsDto>>(messageStats, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/device/{deviceName}/message")
	public ResponseEntity<Message> postMessage(@PathVariable (value = "deviceName") String deviceName, @RequestBody Message message){
		try {
			messageService.addMessage(deviceName,message);
			return new ResponseEntity<Message>(message, HttpStatus.CREATED);
		} catch (InvalidMessageException ime) {
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
