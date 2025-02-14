package com.asshofa.management.service.impl;

import com.asshofa.management.model.entity.Users;
import com.asshofa.management.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    private static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {

            Users users = usersRepository.findByUsername(username);

            if (users == null) {
                throw new UsernameNotFoundException(username);
            }

            return User.builder()
                    .username(users.getUsername())
                    .password(users.getPassword())
                    .roles(users.getRole())
                    .build();

        } catch (Exception e) {
            logger.error("error when load user by username : {}", e.getMessage());
            throw e;
        }
    }
}
