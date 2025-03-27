package com.amitu.springai.functioncalling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amitu.springai.services.OpenAiService;

@Controller
public class FCController {

	@Autowired
	OpenAiService service;

	@GetMapping("/showFunctionCalling")
	public String showChatPage() {
		return "functionCalling";
	}

	@PostMapping("/functionCalling")
	public String getChatResponse(@RequestParam("company") String company, Model model) {
		String response = service.getStockPrice(company);
		model.addAttribute("response", response);
		return "functionCalling";
	}

}
