package com.example.Health.Record.Management.System.controller;

import com.example.Health.Record.Management.System.entity.User;
import com.example.Health.Record.Management.System.exceptions.UserException;
import com.example.Health.Record.Management.System.payload.LoginRequest;
import com.example.Health.Record.Management.System.payload.LoginResponseDto;
import com.example.Health.Record.Management.System.payload.UserRequest;
import com.example.Health.Record.Management.System.payload.UserResponse;
import com.example.Health.Record.Management.System.services.UserService;
import com.example.Health.Record.Management.System.usersecurity.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;




    @PostMapping("/register")
    public ResponseEntity<UserResponse> Register(@Valid @RequestBody UserRequest userDto){
        UserResponse response = userService.register(userDto);
         return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody LoginRequest loginDto) throws UserException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        User user = userService.findUserByEmail(loginDto.getEmail());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateJWTToken(authentication);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setUserName(user.getUserName());
        loginResponseDto.setEmail(user.getEmail());
        loginResponseDto.setToken(token);
        return ResponseEntity.ok(loginResponseDto);
    }
}
