package com.asshofa.service.impl;

import com.asshofa.exception.custom.NotFoundException;
import com.asshofa.mapper.AccountMapper;
import com.asshofa.model.pojo.AccountPojo;
import com.asshofa.model.response.DataResponse;
import com.asshofa.model.response.DefaultResponse;
import com.asshofa.model.response.ResponseMessage;
import com.asshofa.service.AccountService;
import com.asshofa.util.interceptor.LoggingHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final LoggingHolder loggingHolder;
    private static final Logger log = LogManager.getLogger(AccountServiceImpl.class);

    public AccountServiceImpl(AccountMapper accountMapper, LoggingHolder loggingHolder) {
        this.accountMapper = accountMapper;
        this.loggingHolder = loggingHolder;
    }

    @Override
    public DataResponse<AccountPojo> getAccount(String username) {
        try {
            AccountPojo data = accountMapper.getAccount(username);
            if (data != null) {
                return new DataResponse<>(ResponseMessage.DATA_FETCHED, data, loggingHolder);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            log.error("Error when get account", e);
            throw e;
        }
    }

    @Override
    public AccountPojo getUsername(String username) {
        try {
            AccountPojo data = accountMapper.getUsername(username);
            if (data != null) {
                return data;
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            log.error("Error when get account", e);
            throw e;
        }
    }

    @Override
    public DataResponse<AccountPojo> update(AccountPojo account) {
        try {
            accountMapper.updateAccount(account);
            AccountPojo data = accountMapper.getAccount(account.getUsername());
            if (data != null) {
                return new DataResponse<>(ResponseMessage.DATA_UPDATED, data, loggingHolder);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            log.error("Error when update account", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(String username) {
        try {
           AccountPojo data = accountMapper.getAccount(username);
           if (data != null) {
               accountMapper.deleteAccount(username);
               return new DefaultResponse(ResponseMessage.DATA_DELETED, loggingHolder);
           } else {
               throw new NotFoundException();
           }
        } catch (Exception e) {
            log.error("Error when delete account", e);
            throw e;
        }
    }
}
