package com.reviews.restaurant.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

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
        ListBucketsResponse listBuckets = this.s3Client.listBuckets();
        return listBuckets.buckets()
                .stream()
                .map(Bucket::name)
                .toList();
    }

    @Override
    public Boolean uploadFile(String bucketName, String key, Path fileLocation) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        PutObjectResponse putObjectResponse = this.s3Client.putObject(putObjectRequest,fileLocation);
        return putObjectResponse.sdkHttpResponse().isSuccessful();
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
