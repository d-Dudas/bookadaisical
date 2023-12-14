package com.bookadaisical.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.*;

import com.bookadaisical.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {
    
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("bookId") UUID bookId, @RequestParam("image") MultipartFile file) {
        try {
            String uploadImage = imageService.saveImage(bookId, file);
            return ResponseEntity.ok(uploadImage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to upload image");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable UUID id) {
        try {
            byte[] imageData = imageService.getImageById(id);
            return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
                .body(imageData);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
