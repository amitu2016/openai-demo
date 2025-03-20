package com.amitu.springai.embeddings;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amitu.springai.services.OpenAiService;

@Controller
public class ServiceTicketHelper {
	
	@Autowired
	private OpenAiService service;

	@GetMapping("/showServiceTicketHelper")
	public String showServiceTicketHelper() {
		return "supportTicketSearchHelper";

	}

//	@PostMapping("/supportTicketSearchHelper")
//	public String supportTicketSearchHelper(@RequestParam String query, Model model) {
//		List<Document> response = service.searchTickets(query);
//		model.addAttribute("response", response);
//		return "supportTicketSearchHelper";
//
//	}

}
