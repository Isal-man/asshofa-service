package com.asshofa.management.config;

import com.asshofa.management.exception.custom.NotFoundException;
import com.asshofa.management.model.entity.Users;
import com.asshofa.management.repository.UsersRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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
import java.util.Optional;

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
            Optional<Users> users = usersRepository.findByUsername(username);
            if (!users.isPresent()) throw new NotFoundException("user not found");
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", users.get().getUsername());
            claims.put("role", users.get().getRole());
            claims.put("gambar", users.get().getGambar());
            claims.put("createdAt", users.get().getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            claims.put("updatedAt", users.get().getUpdatedAt() != null ? users.get().getUpdatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : null);

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
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expirationTime = claims.getExpiration();
            return expirationTime.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            logger.error("Error checking token expiration", e);
            return true;
        }
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}
