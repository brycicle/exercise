package com.exercise.auth.controller;

import com.exercise.auth.dto.account.AccountRequest;
import com.exercise.auth.dto.jwt.JwtResponse;
import com.exercise.auth.service.impl.JwtUserDetailsServiceImpl;
import com.exercise.auth.util.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public final class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsServiceImpl jwtUserDetailsService;

    @PostMapping
    @SneakyThrows
    public ResponseEntity<JwtResponse> authenticate(@RequestBody final AccountRequest request) {
        log.info("authenticate {} : {}", request.getUsername(), request.getPassword());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (final DisabledException disabledException) {
            throw new RuntimeException("USER_DISABLED", disabledException);
        } catch (final BadCredentialsException badCredentialsException) {
            throw new RuntimeException("Invalid Username/Password", badCredentialsException);
        }

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
