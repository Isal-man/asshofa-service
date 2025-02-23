package com.asshofa.management.service.impl;

import com.asshofa.management.config.JwtTokenProvider;
import com.asshofa.management.exception.custom.UnauthorizedException;
import com.asshofa.management.model.entity.Users;
import com.asshofa.management.model.pojo.LoginUserPojo;
import com.asshofa.management.model.pojo.RegisterUserPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.JwtResponse;
import com.asshofa.management.model.response.ResponseMessage;
import com.asshofa.management.repository.UsersRepository;
import com.asshofa.management.service.AuthService;
import com.asshofa.management.util.Constant;
import com.asshofa.management.util.PasswordUtil;
import com.asshofa.management.util.interceptor.LoggingHolder;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final LoggingHolder loggingHolder;

    private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    @Override
    public DataResponse<Users> registerUser(RegisterUserPojo register) {
        try {

            Users user = new Users();
            user.setUsername(register.getUsername());
            user.setPassword(PasswordUtil.encode(register.getPassword()));
            user.setRole("USER");
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());

            if (register.getGambar() == null) {
                register.setGambar(Constant.DEFAULT_IMAGE);
            }

            return new DataResponse<>(Constant.VAR_SUCCESS, ResponseMessage.DATA_CREATED, usersRepository.save(user), loggingHolder);
        } catch (Exception e) {
            logger.error("error when register user", e);
            throw e;
        }
    }

    @Override
    public JwtResponse authenticateUser(LoginUserPojo login) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());

            if (!PasswordUtil.matchPassword(login.getPassword(), userDetails.getPassword())) throw new UnauthorizedException(ResponseMessage.UNAUTHORIZED);

            String token = jwtTokenProvider.generateToken(userDetails.getUsername());
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
    public JwtResponse refreshToken(String token) {
        try {
            if (Boolean.TRUE.equals(validateToken(token))) {
                String username = getUsernameFromToken(token);
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
