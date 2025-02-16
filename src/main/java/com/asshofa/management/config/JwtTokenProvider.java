package com.asshofa.management.config;

import com.asshofa.management.model.entity.Users;
import com.asshofa.management.repository.UsersRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    private final UsersRepository usersRepository;

    private static final Logger logger = LogManager.getLogger(JwtTokenProvider.class);

    public String generateToken(String username) {
        try {
            Users users = usersRepository.findByUsername(username);
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", users.getUsername());
            claims.put("role", users.getRole());
            claims.put("gambar", users.getGambar());
            claims.put("createdAt", users.getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            claims.put("updatedAt", users.getUpdatedAt() != null ? users.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : null);

            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        } catch (Exception e) {
            logger.error("error generating token", e);
            throw e;
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            logger.error("error when get usernaem from token.", e);
            throw e;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            logger.error("error when validate token.", e);
            throw e;
        }
    }

}
