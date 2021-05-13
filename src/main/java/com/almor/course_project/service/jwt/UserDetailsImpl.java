package com.almor.course_project.service.jwt;

import com.almor.course_project.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Long id;

    private String name;
    @JsonIgnore
    private String password;

    private String email;

    private boolean isEnabled;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetails buildUserDetails(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(user.getId(), user.getName(), user.getPassword(),
                user.getEmail(), user.isEnabled(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
