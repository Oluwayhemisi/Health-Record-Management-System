package com.example.Health.Record.Management.System.services;

import com.example.Health.Record.Management.System.controller.UserController;
import com.example.Health.Record.Management.System.entity.Role;
import com.example.Health.Record.Management.System.entity.User;
import com.example.Health.Record.Management.System.exceptions.UserException;
import com.example.Health.Record.Management.System.payload.LoginRequest;
import com.example.Health.Record.Management.System.payload.LoginResponseDto;
import com.example.Health.Record.Management.System.payload.UserRequest;
import com.example.Health.Record.Management.System.payload.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;



    @Test
    void testToCreateRegisterUser(){
        createUser();
        assertEquals(1,userService.getAllUsers().size());

    }
    private UserResponse createUser() throws UserException {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("yy@gmail.com");
        userRequest.setUserName("Addah");
        userRequest.setPassword("5555");
        userRequest.setRoles(Role.ROLE_PATIENT);

        return userService.register(userRequest);
    }


    @Test
    void testRegisterUserThrowsExceptionForExistingUser() throws UserException {
        UserRequest userRequest = createValidUserRequest();
        userRequest.setEmail("yy@gmail.com");
        userRequest.setUserName("Addah");
        userRequest.setPassword("5555");
        userRequest.setRoles(Role.ROLE_PATIENT);
        userService.register(userRequest);

        assertThrows(UserException.class, () -> userService.register(userRequest));
    }

    @Test
    void testFindUserByEmail() throws UserException {
        UserRequest userRequest = createValidUserRequest();
        User foundUser = userService.findUserByEmail(userRequest.getEmail());

        assertNotNull(foundUser);


    }


    private UserRequest createValidUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("yy@gmail.com");
        userRequest.setUserName("Addah");
        userRequest.setPassword("5555");
        userRequest.setRoles(Role.ROLE_PATIENT);
        userService.register(userRequest);
        return userRequest;
    }


    @Test
    void testLoginUser_success() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("yy@gmail.com");
        userRequest.setUserName("Addah");
        userRequest.setPassword("5555");
        userRequest.setRoles(Role.ROLE_PATIENT);
        userService.register(userRequest);

        // Set up test data
        LoginRequest loginDto = new LoginRequest("yy@gmail.com", "5555");

        // Call the endpoint
        ResponseEntity<LoginResponseDto> response = userController.loginUser(loginDto);

        // Assertions
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        LoginResponseDto loginResponse = response.getBody();
        assertThat(loginResponse.getUserName()).isEqualTo("Addah");
        assertThat(loginResponse.getEmail()).isEqualTo("yy@gmail.com");
        assertThat(loginResponse.getToken()).isNotEmpty();
    }

















//
//    @Test
//    void testLoginUserSuccessfully() throws Exception {
//        // Register a user
//        UserRequest userRequest = createValidUserRequest();
//        userService.register(userRequest);
//
//        // Login with the registered user credentials
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail(userRequest.getEmail());
//        loginRequest.setPassword(userRequest.getPassword());
//
//        mockMvc.perform(post("/api/v1/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").isNotEmpty())
//                .andExpect(jsonPath("$.userName").value(userRequest.getUserName()))
//                .andExpect(jsonPath("$.email").value(userRequest.getEmail()));
//    }

}
