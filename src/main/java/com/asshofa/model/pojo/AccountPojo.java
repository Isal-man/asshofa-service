package com.asshofa.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class AccountPojo implements UserDetails {
    @NotBlank(message = "username is required")
    @Size(max = 15, message = "username max length is 15")
    private String username;
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, max = 15, message = "Password must have at least 5 characters and a maximum of 15 characters")
    private String password;
    @NotBlank(message = "Full name cannot be empty")
    @Size(min = 3, max = 100, message = "Full name must have at least 3 characters and a maximum of 50 " +
            "characters")
    private String fullName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;
    private String image;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
