package com.asshofa.management.service.impl;

import com.asshofa.management.config.JwtTokenProvider;
import com.asshofa.management.model.entity.Users;
import com.asshofa.management.model.pojo.LoginUserPojo;
import com.asshofa.management.model.pojo.RefreshTokenPojo;
import com.asshofa.management.model.pojo.RegisterUserPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.JwtResponse;
import com.asshofa.management.model.response.ResponseMessage;
import com.asshofa.management.repository.UsersRepository;
import com.asshofa.management.service.AuthService;
import com.asshofa.management.util.PasswordUtil;
import com.asshofa.management.util.interceptor.LoggingHolder;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final LoggingHolder loggingHolder;

    private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    @Override
    public DataResponse<Users> registerUser(RegisterUserPojo registerUserPojo) {
        try {
            Users user = new Users();
            user.setUsername(registerUserPojo.getUsername());
            user.setPassword(PasswordUtil.encode(registerUserPojo.getPassword()));
            user.setRole(registerUserPojo.getRole());
            return new DataResponse<>(ResponseMessage.DATA_CREATED, usersRepository.save(user), loggingHolder);
        } catch (Exception e) {
            logger.error("error when register user", e);
            throw e;
        }
    }

    @Override
    public JwtResponse authenticateUser(LoginUserPojo loginUserPojo) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserPojo.getUsername(), loginUserPojo.getPassword()));
            String token = jwtTokenProvider.generateToken(loginUserPojo.getUsername());
            return new JwtResponse(token);
        } catch (Exception e) {
            logger.error("error when authenticate user.", e);
            throw e;
        }
    }

    @Override
    public Boolean validateToken(String token) {
        try {
            return jwtTokenProvider.validateToken(token);
        } catch (Exception e) {
            logger.error("error when validate token", e);
            throw e;
        }
    }

    @Override
    public String getUsernameFromToken(String token) {
        try {
            return jwtTokenProvider.getUsernameFromToken(token);
        } catch (Exception e) {
            logger.error("error when validate token", e);
            throw e;
        }
    }

    @Override
    public JwtResponse refreshToken(RefreshTokenPojo token) {
        try {
            if (validateToken(token.getRefreshToken())) {
                String username = jwtTokenProvider.getUsernameFromToken(token.getRefreshToken());
                String newToken = jwtTokenProvider.generateToken(username);
                return new JwtResponse(newToken);
            } else {
                throw new DataIntegrityViolationException("token not valid");
            }
        } catch (Exception e) {
            logger.error("error when refresh token", e);
            throw e;
        }
    }
}
