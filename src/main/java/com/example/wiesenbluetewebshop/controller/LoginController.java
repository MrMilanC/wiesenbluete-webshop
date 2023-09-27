package com.example.wiesenbluetewebshop.controller;

import com.example.wiesenbluetewebshop.dto.LoginRequest;
import com.example.wiesenbluetewebshop.dto.LoginResponse;
import com.example.wiesenbluetewebshop.security.jwt.JwtIssuer;
import com.example.wiesenbluetewebshop.security.principal.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();

        List<String> roles = principal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = jwtIssuer.issue(
                principal.getUserId(),
                principal.getUsername(),
                roles);

        System.out.println("Token gemacht");
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());
        System.out.println(token);

        return LoginResponse.builder()
                .accessToken(token)
                .ok(true)
                .build();
    }
}
