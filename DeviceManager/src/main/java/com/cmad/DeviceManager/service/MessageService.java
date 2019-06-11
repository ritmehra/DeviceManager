package com.cmad.DeviceManager.service;

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
		// TODO Auto-generated method stub
		return null;
	}

	public Message addMessage(String deviceName, Message message) {
		
		Device device = deviceRepository.findByDeviceName(deviceName);
		if(device==null)
			throw new DeviceNotFoundException();
		
		message.setDevice(device);
        return messageRepository.save(message);
   }

	
}
