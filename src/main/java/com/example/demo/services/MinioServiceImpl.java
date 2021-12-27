package com.example.demo.services;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class MinioServiceImpl implements MinioService{

    @Resource
    private MinioClient minioClient;

    @Value("${spring.minio.bucket}")
    private String bucket;

    public String saveImage(MultipartFile file) {
        try {
            int idx = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
            String suffix = file.getOriginalFilename().substring(idx + 1);
            String fileName = UUID.randomUUID() + "." + suffix;

            minioClient.putObject(PutObjectArgs.builder()
                    .stream(file.getInputStream(), file.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                    .object(fileName)
                    .contentType(file.getContentType())
                    .bucket(bucket)
                    .build());
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public String getUrl(String path) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(bucket)
                    .object(path).
                    method(Method.GET)
                    .expiry(7, TimeUnit.DAYS).build());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
