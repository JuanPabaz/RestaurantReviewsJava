package com.reviews.restaurant.service;

import com.reviews.restaurant.entities.Image;
import com.reviews.restaurant.entities.Restaurant;
import com.reviews.restaurant.entities.Review;
import com.reviews.restaurant.exceptions.BadCreateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.reviews.restaurant.utils.Constants.*;

@Service
public class S3ServiceImpl implements IS3Service {

    private final S3Client s3Client;

    @Value("${spring.destination.folder}")
    private String destinationFolder;

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
    public Boolean uploadFile(String bucketName, String key, MultipartFile file) {
        try {
            Path tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
            file.transferTo(tempFile.toFile());
            return uploadFile(bucketName, key, tempFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String generatePresignedUploadUrl(String bucketName, String key, Duration duration) {
        return "";
    }

    @Override
    public String generatePresignedDownloadloadUrl(String bucketName, String key, Duration duration) {
        return "";
    }

    public List<Image> saveImages(List<MultipartFile> images, Review review, Restaurant restaurant) {
        List<Image> imageResponse = new ArrayList<>();
        try{
            if (images != null) {
                for (MultipartFile image : images) {
                    String key = RESTAURANT_BUCKET_FOLDER + image.getOriginalFilename();
                    Path tempFile = Files.createTempFile("upload-", image.getOriginalFilename());
                    image.transferTo(tempFile.toFile());

                    boolean uploadSuccess = uploadFile(RESTAURANT_BUCKET, key, tempFile);
                    if (uploadSuccess) {
                        Image imageEntity = new Image();
                        imageEntity.setImage(BUCKET_URL + key);
                        imageEntity.setReview(review);
                        imageEntity.setRestaurant(restaurant);
                        imageResponse.add(imageEntity);
                    }
                }
            }
        }catch (IOException e){
            throw new BadCreateRequest("Error al cargar imagen");
        }

        return imageResponse;
    }
}
