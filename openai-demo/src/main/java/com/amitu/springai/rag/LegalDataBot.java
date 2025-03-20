package com.amitu.springai.rag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.amitu.springai.services.OpenAiService;

@Controller
public class LegalDataBot {
	
	@Autowired
	private OpenAiService service;

	@GetMapping("/showLegalDataBot")
	public String showLegalDataBot() {
		return "legalDataBot";

	}

//	@PostMapping("/legalDataBot")
//	public String legalDataBot(@RequestParam String query, Model model) {
//		model.addAttribute("response", service.answer(query));
//		return "legalDataBot";
//
//	}

}
