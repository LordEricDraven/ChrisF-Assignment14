package com.coderscampus.assignment14.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.domain.User;
import com.coderscampus.assignment14.service.MessageService;
import com.coderscampus.assignment14.service.UserService;

@Controller
@RequestMapping("/channels/{channelId}/messages")
public class MessageController {

	private final MessageService messageService;
	private final UserService userService;
	
	@Autowired
	public MessageController(MessageService messageService, UserService userService) {
		this.messageService = messageService;
		this.userService = userService;
	}
	
	@PostMapping("/send")
	public String sendMessage(@PathVariable Long channelId,
							  @RequestParam Long userId,
							  @RequestParam String content) {
		User user = userService.getUserById(userId);
		messageService.sendMessage(channelId,  user, content);
		return "redirect:/channels/" + channelId;
	}
	
	@GetMapping
	@ResponseBody
	public List<Message> getMessagesByChannel(@PathVariable Long channelId){
		return messageService.getMessagesByChannel(channelId);
	}
	
}
