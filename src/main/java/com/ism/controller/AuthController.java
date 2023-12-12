package com.ism.controller;

import com.ism.model.AuthRequest;
import com.ism.model.AuthResponse;
import com.ism.service.CustomUserDetailsService;
import com.ism.SecurityConfig.JwtAuthentication.JWTUtility;
import com.ism.dto.UserDto;
import com.ism.model.User;
import com.ism.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    private final static String USER_ERROR_ALREADY_EXIST = "user already exists";
    private final static String REGISTER_URL = "/register";
    private final static String LOGIN_URL = "/login";
    private final static String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping(REGISTER_URL)
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        UserDto checkedUser = userService.getUserByUserName(userDto.getUsername());
        if(Objects.nonNull(checkedUser)){
            return ResponseEntity.badRequest().body(USER_ERROR_ALREADY_EXIST);
        }

        UserDto addedUserDto = userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedUserDto.getUsername());
    }


    @PostMapping(LOGIN_URL)
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception(INVALID_CREDENTIALS, e);
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setRole(userService.getUserByUserName(userDetails.getUsername()).getRole().getName());
        authResponse.setUsername(userDetails.getUsername());

        return ResponseEntity.ok().body(authResponse);

    }

}
