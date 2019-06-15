package com.cmad.DeviceManager.service;

import java.text.ParseException;
import java.util.List;

import com.cmad.DeviceManager.domain.Message;

public interface MessageServiceIf {
	
	public List<Message> getMessages();
	
	public List<Message> getFilteredMessages(String deviceName, Integer severity) ;

	public Message addMessage(String deviceName, Message message);
}
