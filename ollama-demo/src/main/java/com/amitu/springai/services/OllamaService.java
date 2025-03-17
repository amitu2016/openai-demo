package com.amitu.springai.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {
	
	private ChatClient chatClient;
	
	public OllamaService(ChatClient.Builder builder) {
		this.chatClient = builder.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory())).build();
	}
	
	public ChatResponse generateAnswer(String question) {
		return chatClient.prompt(question).call().chatResponse();
	}

	

}
