package com.asshofa.mapper;

import com.asshofa.model.pojo.AccountPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper {
    AccountPojo getAccount(String username);
    AccountPojo getUsername(String username);
    void createAccount(AccountPojo account);
    void updateAccount(AccountPojo account);
    void deleteAccount(String username);
}
