package com.bookadaisical.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    String saveImage(MultipartFile file) throws Exception;
    public byte[] getImageById(Long id);
}
