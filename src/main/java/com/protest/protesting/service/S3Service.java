package com.protest.protesting.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.protest.protesting.entity.S3Entity;
import com.protest.protesting.exception.BusinessException;
import com.protest.protesting.exception.ErrorCode;
import com.protest.protesting.mapper.VisitorImagesMapper;
import lombok.NoArgsConstructor;
//import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
@NoArgsConstructor
public class S3Service {
    private AmazonS3 s3Client;

//    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

//    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

//    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

//    @Value("${cloud.aws.region.static}")
    private String region;

    @Autowired VisitorImagesMapper visitorImagesMapper;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return s3Client.getUrl(bucket, fileName).toString();
    }

    public S3Entity insertVisitorImage(S3Entity s3Entity) {
        visitorImagesMapper.insertVisitorImage(s3Entity);

        int targetSeq = s3Entity.getSeq();

        S3Entity visitorInfo = null;
        visitorInfo = visitorImagesMapper.getVisitorImage(targetSeq);
        if (visitorInfo == null) {
            throw new BusinessException(ErrorCode.ERR_S3_UPLOAD);
        }
        return visitorInfo;
    }

    public S3Entity selectVisitorImage(int targetSeq) {
        S3Entity s3Entity = visitorImagesMapper.getVisitorImage(targetSeq);
        if (s3Entity == null) {
            throw new BusinessException(ErrorCode.ERR_S3_NOT_FOUND);
        }

        return s3Entity;
    }
}
