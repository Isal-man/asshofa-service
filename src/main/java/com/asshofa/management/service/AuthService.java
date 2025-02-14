package com.asshofa.management.service;

import com.asshofa.management.model.entity.Users;
import com.asshofa.management.model.pojo.LoginUserPojo;
import com.asshofa.management.model.pojo.RefreshTokenPojo;
import com.asshofa.management.model.pojo.RegisterUserPojo;
import com.asshofa.management.model.response.DataResponse;
import com.asshofa.management.model.response.JwtResponse;

public interface AuthService {

    DataResponse<Users> registerUser(RegisterUserPojo registerUserPojo);

    JwtResponse authenticateUser(LoginUserPojo loginUserPojo);

    Boolean validateToken(String token);

    String getUsernameFromToken(String token);

    JwtResponse refreshToken(RefreshTokenPojo token);

}
