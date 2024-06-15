package com.asshofa.service;

import com.asshofa.model.pojo.AccountPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DefaultResponse;

public interface AccountService {
    DataResponse<AccountPojo> getAccount(String username);
    AccountPojo getUsername(String username);
    DataResponse<AccountPojo> update(AccountPojo data);
    DefaultResponse delete(String username);
}
