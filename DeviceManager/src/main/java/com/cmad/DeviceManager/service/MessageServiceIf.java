package com.cmad.DeviceManager.service;

import java.util.List;

import com.cmad.DeviceManager.domain.Message;
import com.cmad.DeviceManager.dto.DeviceStatsDto;

public interface MessageServiceIf {
	
	public List<Message> getMessages();
	
	public List<Message> getFilteredMessages(String deviceName, Integer severity) ;

	public Message addMessage(String deviceName, Message message);
	
	public List<DeviceStatsDto> getDeviceStats(String deviceName);
}
