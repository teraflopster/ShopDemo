package com.example.demo.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {
    @Value("${spring.minio.url}")
    private String end_point;

    @Value("${spring.minio.access-key}")
    private String username;

    @Value("${spring.minio.secret-key}")
    private String password;

    @Bean
    public MinioClient minioClient() {
        return new MinioClient.Builder()
                .endpoint(end_point)
                .credentials(username, password)
                .build();
    }
}