package com.reviews.restaurant.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

@Service
public class S3ServiceImpl implements IS3Service {

    private final S3Client s3Client;

    public S3ServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public Boolean checkIfBucketExists(String bucketName) {
        try{
            this.s3Client.headBucket(bucket -> bucket.bucket(bucketName));
            return true;
        }catch (S3Exception e){
            return false;
        }
    }

    @Override
    public List<String> getAllBuckets() {
        return List.of();
    }

    @Override
    public Boolean uploadFile(String bucketName, String key, Path fileLocation) {
        return null;
    }

    @Override
    public String generatePresignedUploadUrl(String bucketName, String key, Duration duration) {
        return "";
    }

    @Override
    public String generatePresignedDownloadloadUrl(String bucketName, String key, Duration duration) {
        return "";
    }
}
