package com.asshofa.service;

import com.asshofa.model.pojo.AccountPojo;
import com.asshofa.model.pojo.LoginPojo;
import com.asshofa.model.response.DataResponse;

public interface AuthenticationService {
    DataResponse<AccountPojo> signUp(AccountPojo accountPojo);
    AccountPojo authenticate(LoginPojo loginPojo);
}
