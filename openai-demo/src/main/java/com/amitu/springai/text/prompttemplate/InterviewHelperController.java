package com.amitu.springai.text.prompttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amitu.springai.services.OpenAiService;

@Controller
public class InterviewHelperController {
	@Autowired
	private OpenAiService service;

	@GetMapping("/showInterviewHelper")
	public String showInterviewHelper() {
		return "interviewHelper";
	}

	@PostMapping("/interviewHelper")
	public String interviewHelper(@RequestParam("company") String company,
			@RequestParam("jobTitle") String jobTitle,
			@RequestParam("strength") String strength, 
			@RequestParam("weakness") String weakness, Model model) {
		
		String response = service.getInterviewHelp(company, jobTitle, strength, weakness);
		model.addAttribute("response", response);
		return "interviewHelper";
	}

}
