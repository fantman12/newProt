package com.protest.protesting.mapper;

import com.protest.protesting.entity.AccountEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    public String getTime();
    public AccountEntity getUser(String id);
    public AccountEntity getUserByName(AccountEntity accountEntity);

    public void signInUser(AccountEntity accountEntity);
}
