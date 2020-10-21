package com.protest.protesting.service;

import com.protest.protesting.entity.IamportInformationEntity;
import com.protest.protesting.mapper.IamportInformationMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IamportInformationServiceImpl implements IamportInformationService{
    @Autowired
    IamportInformationMapper iamportInformationMapper;

    @Override
    public Object insertIamportLog(JSONObject jsonObject) {
        iamportInformationMapper.insertIamportLog(jsonObject);

        return null;
    }

}
