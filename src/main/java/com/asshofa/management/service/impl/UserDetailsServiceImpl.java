package com.asshofa.management.service.impl;

import com.asshofa.management.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username)
                .map(users -> User.builder()
                        .username(users.getUsername())
                        .password(users.getPassword())
                        .roles("ROLE_" + users.getRole().toUpperCase()) // Tambahkan ROLE_
                        .build()
                ).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
