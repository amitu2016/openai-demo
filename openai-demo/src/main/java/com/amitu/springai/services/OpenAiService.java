package com.amitu.springai.services;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import com.amitu.springai.text.prompttemplate.dtos.CountryCuisines;

@Service
public class OpenAiService {
	
	private ChatClient chatClient;
	
	public OpenAiService(ChatClient.Builder builder) {
		this.chatClient = builder.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory())).build();
	}
	
	public ChatResponse generateAnswer(String question) {
		return chatClient.prompt(question).call().chatResponse();
	}

	public String getTravelGuidance(String city, String month, String language, String budget) {
		PromptTemplate promptTemplate = new PromptTemplate("Welcome to the {city} travel guide!\n"
				+ "If you're visiting in {month}, here's what you can do:\n"
				+ "1. Must-visit attractions.\n"
				+ "2. Local cuisine you must try.\n"
				+ "3. Useful phrases in {language}.\n"
				+ "4. Tips for traveling on a {budget} budget.\n"
				+ "Enjoy your trip!");
		
		Prompt prompt = promptTemplate.create(Map.of("city",city,"month",month,"language",language,"budget",budget));
		
		return chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getText();
	}

	public CountryCuisines getCuisines(String country, String numCuisines, String language) {
		PromptTemplate promptTemplate = new PromptTemplate("You are an expert in traditional cuisines.\n"
				+ "You provide information about a specific dish from a specific\n"
				+ "country.\n"
				+ "Answer the question: What is the traditional cuisine of {country}\n?"
				+ "Return a list of {numCuisines} in {language}\n"
				+ "Avoid giving information about fictional places. If the country is\n"
				+ "fictional\n"
				+ "or non-existent answer: I don't know.");
		
		Prompt prompt = promptTemplate.create(Map.of("country",country,"numCuisines",numCuisines,"language",language));
		return chatClient.prompt(prompt).call().entity(CountryCuisines.class);
	}

	public String getInterviewHelp(String company, String jobTitle, String strength, String weakness) {
		PromptTemplate promptTemplate = new PromptTemplate("Welcome to the {company} interview guide for {jobTitle} position.\n"
				+ "If you are preparing for {jobTitle} role, and have {strength} as strength and {weakness} as weakness,\n"
				+ "You can do the following: \n"
				+ "1. Tips to take adavantage of strength during interview.\n"
				+ "2. Tips to improvise on weakness during interview.\n"
				+ "3. Additional tips specific to {company} for interview preparation");
		
		Prompt prompt = promptTemplate.create(Map.of("company",company,"jobTitle",jobTitle,"strength",strength,"weakness",weakness));
		return chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getText();
	}

	

}
