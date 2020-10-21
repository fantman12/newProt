package com.protest.protesting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class IamportInformationEntity {

    private int seq;
	private String birthday;
    private String gender;
    private String nation;
    private String name;
    private String request_num;
	private String request_at;
    private String phone_number;
    private String agency;
    private String CI;
    private String DI;
    private String result_code;
    private String error_message;
    private String created_at;
	private String updated_at;
	private int is_deleted;
}
