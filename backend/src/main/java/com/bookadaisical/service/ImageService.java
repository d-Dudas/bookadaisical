package com.bookadaisical.service;

import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import org.apache.commons.lang3.exception.ContextedRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookadaisical.model.Image;
import com.bookadaisical.repository.ImageRepository;
import com.bookadaisical.service.interfaces.IImageService;
import com.bookadaisical.utils.ImageUtils;

@Service
public class ImageService implements IImageService {
    
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String saveImage(MultipartFile imageFile) throws Exception {
        var imageToSave = Image.builder()
            .imageData(ImageUtils.compressImage(imageFile.getBytes()))
            .build();
        imageRepository.save(imageToSave);
        return "file uploaded successfully : " + imageFile.getOriginalFilename();
    }

    public byte[] getImageById(Long id) {
        Optional<Image> dbImage = imageRepository.findById(id);

        return dbImage.map(image -> {
            try {
                return ImageUtils.decompressImage(image.getImageData());
            } catch (DataFormatException | IOException exception) {
                throw new ContextedRuntimeException("Error downloading an image", exception)
                    .addContextValue("Image ID", image.getId());
            }
        }).orElse(null);
    }
}
