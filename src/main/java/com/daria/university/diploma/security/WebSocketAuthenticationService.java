package com.daria.university.diploma.security;

import com.daria.university.diploma.model.dto.User;
import com.daria.university.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class WebSocketAuthenticationService {

    @Autowired
    UserService userService;

    public UsernamePasswordAuthenticationToken getAuthenticatedOrFail(final String  username, final String password)
            throws AuthenticationException {
        if (username == null || username.trim().length() == 0) {
            throw new AuthenticationCredentialsNotFoundException("Username was null or empty.");
        }
        if (password == null || password.trim().length() == 0) {
            throw new AuthenticationCredentialsNotFoundException("Password was null or empty.");
        }
        // Add your own logic for retrieving user in fetchUserFromDb()
        if (!userService.credentialsAreValid(username, password)) {
            throw new BadCredentialsException("Bad credentials for user " + username);
        }

        User user = userService.findByUsername(username);
        // null credentials, we do not pass the password along to prevent security flaw
        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                Collections.singleton((GrantedAuthority) () -> user.getRole().getName())
        );
    }
}