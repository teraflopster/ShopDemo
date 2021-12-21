package com.example.demo.services;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {

    String saveImage(MultipartFile file);

    String getUrl(String path);

}
