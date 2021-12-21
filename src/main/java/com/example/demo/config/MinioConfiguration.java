package com.example.demo.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {

    private static final String END_POINT = "http://localhost:9000";

    private static final String USERNAME = "admin";

    private static final String PASSWORD = "password";

    @Bean
    public MinioClient minioClient() {
        return new MinioClient.Builder()
                .endpoint(END_POINT)
                .credentials(USERNAME, PASSWORD)
                .build();
    }
}