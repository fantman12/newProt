package com.protest.protesting.mapper;

import com.protest.protesting.entity.S3Entity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisitorImagesMapper {
    public void insertVisitorImage(S3Entity s3Entity);
    public S3Entity getVisitorImage(int targetSeq);
}
