package com.reviews.restaurant.service;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

public interface IS3Service {

    Boolean checkIfBucketExists(String bucketName);

    List<String> getAllBuckets();

    Boolean uploadFile(String bucketName, String key, Path fileLocation);

    String generatePresignedUploadUrl(String bucketName, String key, Duration duration);

    String generatePresignedDownloadloadUrl(String bucketName, String key, Duration duration);
}
