package com.riquelmi.apipizzeria.controller;

import com.riquelmi.apipizzeria.config.JWTUtil;
import com.riquelmi.apipizzeria.persistence.entity.UserEntity;
import com.riquelmi.apipizzeria.service.dto.LoginDto;
import com.riquelmi.apipizzeria.utils.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public @ResponseBody ResponseObject login(@RequestBody LoginDto loginDto) {
        try {
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            Authentication authentication = this.authenticationManager.authenticate(login);

            // Verifica si la autenticación fue exitosa
            if (authentication.isAuthenticated()) {
                String jwt = this.jwtUtil.create(loginDto.getUsername());
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.AUTHORIZATION, jwt);

                return new ResponseObject(ResponseObject.CODE_SUCCESS, "Login successful", jwt);
            } else {
                return new ResponseObject(ResponseObject.CODE_ERROR, "Login failed", null);
            }
        } catch (Exception e) {
            return new ResponseObject(ResponseObject.CODE_ERROR, "An error occurred during login", null);
        }
    }
}
