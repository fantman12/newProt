package com.protest.protesting.entity;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class S3Entity {
    private int seq;
    private int visitor_seq;
    private String image_url;
    private String created_at;
    private String updated_at;
    private int is_deleted;
}
