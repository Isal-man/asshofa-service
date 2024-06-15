package com.asshofa.service.impl;

import com.asshofa.mapper.AccountMapper;
import com.asshofa.model.pojo.AccountPojo;
import com.asshofa.model.pojo.LoginPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.ResponseMessage;
import com.asshofa.service.AuthenticationService;
import com.asshofa.util.DateHelper;
import com.asshofa.util.interceptor.LoggingHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AccountMapper accountMapper;
    private final DateHelper dateHelper;
    private final PasswordEncoder passwordEncoder;
    private final LoggingHolder loggingHolder;

    private static final Logger log = LogManager.getLogger(AuthenticationServiceImpl.class);

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, AccountMapper accountMapper, DateHelper dateHelper, PasswordEncoder passwordEncoder, LoggingHolder loggingHolder) {
        this.authenticationManager = authenticationManager;
        this.accountMapper = accountMapper;
        this.dateHelper = dateHelper;
        this.passwordEncoder = passwordEncoder;
        this.loggingHolder = loggingHolder;
    }

    @Override
    public DataResponse<AccountPojo> signUp(AccountPojo account) {
        try {
            account.setCreatedAt(dateHelper.getCurrentTimestamp());
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountMapper.createAccount(account);

            AccountPojo data = accountMapper.getAccount(account.getUsername());
            return new DataResponse<>(ResponseMessage.DATA_CREATED, data, loggingHolder);
        } catch (Exception e) {
            log.error("Error when create account", e);
            throw e;
        }
    }

    @Override
    public AccountPojo authenticate(LoginPojo loginPojo) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginPojo.getUsername(), loginPojo.getPassword()));

        return accountMapper.getAccount(loginPojo.getUsername());
    }

}
