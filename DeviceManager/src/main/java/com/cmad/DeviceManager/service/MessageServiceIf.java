package com.cmad.DeviceManager.service;

import java.util.List;

import com.cmad.DeviceManager.domain.ApplicationUser;
import com.cmad.DeviceManager.domain.Message;
import com.cmad.DeviceManager.dto.DeviceStatsDto;
import com.cmad.DeviceManager.dto.MessageStatsDto;

public interface MessageServiceIf {
	
	public List<Message> getMessages(ApplicationUser user);
	
	public List<Message> getFilteredMessages(String deviceName, Integer severity) ;

	public Message addMessage(String deviceName, Message message);
	
	public List<DeviceStatsDto> getDeviceStats(ApplicationUser user, String deviceName);
	
	public List<MessageStatsDto> getMessageStats(ApplicationUser user, String deviceName, Integer severity);
}
