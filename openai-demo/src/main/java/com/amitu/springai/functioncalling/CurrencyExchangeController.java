package com.amitu.springai.functioncalling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amitu.springai.services.OpenAiService;

@Controller
public class CurrencyExchangeController {

	@Autowired
	OpenAiService service;

	@GetMapping("/showCurrencyExchange")
	public String showChatPage() {
		return "currencyExchange";
	}

	@PostMapping("/currencyExchange")
	public String getChatResponse(@RequestParam("country") String company, Model model) {
		String response = service.getExchangeRate(company);
		model.addAttribute("response", response);
		return "currencyExchange";
	}
}
