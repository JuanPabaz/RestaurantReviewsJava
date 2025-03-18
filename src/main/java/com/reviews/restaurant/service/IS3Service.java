package com.reviews.restaurant.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

public interface IS3Service {

    Boolean checkIfBucketExists(String bucketName);

    List<String> getAllBuckets();

    Boolean uploadFile(String bucketName, String key, Path fileLocation);

    Boolean uploadFile(String bucketName, String key, MultipartFile file);

    String generatePresignedUploadUrl(String bucketName, String key, Duration duration);

    String generatePresignedDownloadloadUrl(String bucketName, String key, Duration duration);
}
