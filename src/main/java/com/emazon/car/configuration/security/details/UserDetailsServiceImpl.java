package com.emazon.car.configuration.security.details;

import com.emazon.car.adapters.driven.feigns.client.AuthorizationFeign;
import com.emazon.car.adapters.driven.feigns.dto.request.AuthorizationRequest;
import com.emazon.car.adapters.driven.feigns.dto.response.AuthorizationResponse;
import com.emazon.car.configuration.security.exceptions.NotAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthorizationFeign authorizationFeign;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        AuthorizationRequest authRequest = AuthorizationRequest.builder()
                .token(token)
                .build();
        AuthorizationResponse authResponse = authorizationFeign.authorize(authRequest);

        if (!authResponse.isAuthorized()) throw new NotAuthorizedException(authResponse.getEmail());

        Collection<? extends GrantedAuthority> authorities =
                Set.of(new SimpleGrantedAuthority(authResponse.getRole()));

        return new User(authResponse.getEmail(), token, authorities);
    }
}
