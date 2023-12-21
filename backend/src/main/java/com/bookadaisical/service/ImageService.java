package com.bookadaisical.service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.DataFormatException;
import org.apache.commons.lang3.exception.ContextedRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookadaisical.model.Book;
import com.bookadaisical.model.Image;
import com.bookadaisical.repository.BookRepository;
import com.bookadaisical.repository.ImageRepository;
import com.bookadaisical.service.interfaces.IImageService;
import com.bookadaisical.utils.ImageUtils;

@Service
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, BookRepository bookRepository) {
        this.imageRepository = imageRepository;
        this.bookRepository = bookRepository;
    }

    public String saveImage(UUID bookId, MultipartFile imageFile) throws Exception {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new Exception("Book not found with ID: " + bookId));

        String imageName = imageFile.getOriginalFilename();
        if (imageName != null) {
            imageName = imageName.replaceFirst("[.][^.]+$", "");
        }
        var imageToSave = Image.builder()
            .imageData(ImageUtils.compressImage(imageFile.getBytes()))
            .imageName(imageName)
            .book(book)
            .build();

        book.getImages().add(imageToSave);
        imageRepository.save(imageToSave);

        return "file uploaded successfully : " + imageFile.getOriginalFilename();
    }

    public byte[] getImageById(UUID id) {
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
