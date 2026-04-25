package com.criscahub.erp_lite.persistence.aws.adapters;

import com.criscahub.erp_lite.domain.exception.MyBussinessException;
import com.criscahub.erp_lite.domain.ports.ImageStorageService;
import com.criscahub.erp_lite.domain.product.ProductImage;
import com.criscahub.erp_lite.persistence.aws.models.AwsConfigModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Slf4j
@Service
@RequiredArgsConstructor
public class AwsImageStorageAdapter implements ImageStorageService {

    private final S3Client s3Client;

    private  final AwsConfigModel awsConfig;

    /**
     *
     * @param url https://amazonaws/erp-products/products/mac-o1.png
     * @return products/mac-01.png
     */
    private String getKeyFromUrl(String url){
        var bucketName = awsConfig.bucketName();
        var parts = url.split("/"+bucketName+"/");
        if(parts.length>1){
            return parts[1];
        }
        log.warn("No bucket name found for url:"+url);
        return null;
    }

    private String buildUrlImg(String key){
        final var placeholder = "%s/%s/%s";
        return String.format(
                placeholder,
                this.awsConfig.endpoint(),
                this.awsConfig.bucketName(),
                key
        );
    }

    private String determineContentType(String filename){
        final var extension = filename.substring(filename.lastIndexOf(".")+1).toLowerCase();

        return switch (extension){
            case "jpg"->"image/jpeg";
            case "png"->"image/png";
            case "webp"->"image/webp";
            default -> "application/octet-stream";
        };
    }

    @Override
    public ProductImage upload(String imageName, byte[] imageData) {
        try {
            final var key = "products/"+imageName;
            final var putObjectRequest = PutObjectRequest
                    .builder()
                    .bucket(awsConfig.bucketName())
                    .key(key)
                    .contentType(this.determineContentType(imageName))
                    .contentLength((long) imageData.length)
                    .build();
            this.s3Client.putObject(putObjectRequest, RequestBody.fromBytes(imageData));
            final var imgUrl = this.buildUrlImg(key);
            log.info("Uploading image successfully");
            return new ProductImage(imgUrl);

        }catch (S3Exception s3e){
            log.error("Error uploading image",s3e);
            throw  new MyBussinessException("Error uploading image"+ s3e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected Error uploading image",e);
            throw  new RuntimeException("Error uploading image"+ e.getMessage());
        }
    }

    @Override
    public void delete(ProductImage img) {
        try {
            final var key = this.getKeyFromUrl(img.imageUrl());
            final var deleteObjRequest = DeleteObjectRequest
                    .builder()
                    .bucket(awsConfig.bucketName())
                    .key(key)
                    .build();
            this.s3Client.deleteObject(deleteObjRequest);
            log.info("Deleted image success{}", img.imageUrl());

        }catch (S3Exception s3e){
            log.error("Error deleteding image",s3e);
            throw  new MyBussinessException("Error deleteding image"+ s3e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected Error deleteding image",e);
            throw  new RuntimeException("Error deleteding image"+ e.getMessage());
        }
    }

    @Override
    public byte[] download(ProductImage img) {
       try {
           final var key = this.getKeyFromUrl(img.imageUrl());
           final var getObjRequest = GetObjectRequest
                   .builder()
                   .bucket(awsConfig.bucketName())
                   .key(key)
                   .build();

           final var bytes = this.s3Client.getObjectAsBytes(getObjRequest).asByteArray();
           log.info("Download image: {} bytes", bytes.length);
           return bytes;

       }catch (S3Exception s3e){
           log.error("Error downloading image",s3e);
           throw  new MyBussinessException("Error downloading image"+ s3e.getMessage());
       } catch (Exception e) {
           log.error("Unexpected Error downloading image",e);
           throw  new RuntimeException("Error downloading image"+ e.getMessage());
       }
    }
}
