package com.bookadaisical.service.interfaces;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    String saveImage(UUID bookId, MultipartFile file) throws Exception;
    public byte[] getImageById(UUID id);
}
