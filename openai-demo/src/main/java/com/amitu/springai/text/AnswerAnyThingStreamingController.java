package com.amitu.springai.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amitu.springai.services.OpenAiService;

import reactor.core.publisher.Flux;

@RestController
public class AnswerAnyThingStreamingController {

	@Autowired
	OpenAiService service;
	
	@GetMapping("/showStreamingAnswers")
	public String showStreamingResponse() {
		return "stream.html";
	}
	
	 @GetMapping("/stream")
	 public Flux<String> askAnything(@RequestParam("message") String message){
		return service.streamAnswer(message);
	 }
	 
}