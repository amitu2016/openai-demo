package com.amitu.springai.speech;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amitu.springai.services.OpenAiService;

@Controller
public class SpeechToMCQController {
	
	 // Define the folder where images will be saved
		private static final String UPLOAD_DIR = "D:\\Current_Downloads_Folder\\Documents\\springai\\audio\\uploads";
	    
	    @Autowired
	    private OpenAiService service;

	    // Display the image upload form
	    @GetMapping("/showSpeechToMCQ")
	    public String showUploadForm() {
	        return "speechToMCQ";
	    }

	    @PostMapping("/speechToMCQ")
	    public String uploadImage(String prompt, @RequestParam("file") MultipartFile file, Model model, 
	    		RedirectAttributes redirectAttributes) {
	    	
	    	if (file.isEmpty()) {
	            model.addAttribute("message", "Please select a file to upload");
	            return "speechToMCQ";
	        }

	        try {
	            // Ensure the directory exists
	            Path uploadDir = Paths.get(UPLOAD_DIR);
	            if (Files.notExists(uploadDir)) {
	                Files.createDirectories(uploadDir); // Create the directory if it doesn't exist
	            }

	            // Save the uploaded file to the specified directory
	            Path path = uploadDir.resolve(file.getOriginalFilename());
	            Files.write(path, file.getBytes(), StandardOpenOption.CREATE);
	            
	            // Generate explanation and add to the model
	            String transcription = service.generateMCQ(path.toString());
	            model.addAttribute("transcription", transcription);
	                   
	        } catch (IOException e) {
	            e.printStackTrace();
	            model.addAttribute("message", "Failed to upload file");
	        }
	    	
	        return "speechToMCQ";
	    }

}
