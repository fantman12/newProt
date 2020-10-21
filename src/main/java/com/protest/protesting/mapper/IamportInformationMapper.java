package com.protest.protesting.mapper;

import com.protest.protesting.entity.IamportInformationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

@Mapper
public interface IamportInformationMapper {
    public void insertIamportLog(JSONObject jsonObject);
}
