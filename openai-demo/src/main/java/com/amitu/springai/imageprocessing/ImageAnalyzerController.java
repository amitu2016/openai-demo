package com.amitu.springai.imageprocessing;

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
public class ImageAnalyzerController {

    private static final String UPLOAD_DIR = "D:\\Current_Downloads_Folder\\Documents\\springai\\images\\uploads";
    
    @Autowired
    private OpenAiService service;

    // Display the image upload form
    @GetMapping("showImageAnalyzer")
    public String showUploadForm() {
        return "imageAnalyzer";
    }

    @PostMapping("/imageAnalyzer")
    public String uploadImage(String prompt, @RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
    	if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "imageAnalyzer";
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
            
            String response =service.explainImage(prompt,path.toString());
            model.addAttribute("explanation",response);
                   

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload file");
        }

        return "imageAnalyzer";
    }
}