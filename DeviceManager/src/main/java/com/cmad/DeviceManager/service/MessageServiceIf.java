package com.cmad.DeviceManager.service;

import java.util.List;

import com.cmad.DeviceManager.domain.Message;

public interface MessageServiceIf {
	
	public List<Message> getMessages();

	public List<Message> getFilteredMessages(Message message);
}
