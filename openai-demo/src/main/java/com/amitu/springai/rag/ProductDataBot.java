package com.amitu.springai.rag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.amitu.springai.services.OpenAiService;

@Controller
public class ProductDataBot {

	@Autowired
	private OpenAiService service;

	@GetMapping("/showProductDataBot")
	public String showProductDataBot() {
		return "productDataBot";

	}

//	@PostMapping("/productDataBot")
//	public String productDataBot(@RequestParam String query, Model model) {
//		model.addAttribute("response", service.answer(query));
//		return "productDataBot";
//
//	}

}