package com.cmad.DeviceManager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmad.DeviceManager.Exception.DeviceNotFoundException;
import com.cmad.DeviceManager.domain.Device;
import com.cmad.DeviceManager.domain.Message;
import com.cmad.DeviceManager.repository.DeviceRepository;
import com.cmad.DeviceManager.repository.MessageRepository;

@Service
public class MessageService implements MessageServiceIf{
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	DeviceRepository deviceRepository;
	
    public List<Message> getMessages() {
		List<Message> messages = new ArrayList<Message>();
		messages = messageRepository.findAll();
		return messages;
	}

	public Message addMessage(String deviceName, Message message) {
		
		Device device = deviceRepository.findByDeviceName(deviceName);
		if(device==null)
			throw new DeviceNotFoundException();
		
		message.setDevice(device);
        return messageRepository.save(message);
   }

	@Override
	public List<Message> getFilteredMessages(String deviceName, Integer severity) {
		List<Message> messages = new ArrayList<Message>();
		Device device = null;
		
		if(deviceName!=null) {
			device = deviceRepository.findByDeviceName(deviceName);
		}
		
		if(device!=null && severity!=null)
			messages = messageRepository.findByDeviceAndSeverity(device, severity);
		else if(device!=null)
			messages = messageRepository.findByDevice(device);
		else if(severity!=null)
			messages = messageRepository.findBySeverity(severity);
		
		return messages;
	}

	
}
