package com.javarush.jira.login.internal.web;

import com.javarush.jira.login.AuthUser;
import com.javarush.jira.login.internal.UserRepository;
import com.javarush.jira.login.internal.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        AuthUser authUser = new AuthUser(userRepository.getExistedByEmail(request.getEmail()));
        var jwtToken = jwtService.generateToken(authUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
