package com.example.Health.Record.Management.System.services;

import com.example.Health.Record.Management.System.entity.User;
import com.example.Health.Record.Management.System.exceptions.UserException;
import com.example.Health.Record.Management.System.payload.UserRequest;
import com.example.Health.Record.Management.System.payload.UserResponse;

import java.util.List;

public interface UserService {

    public User findUserByEmail(String email) throws UserException;

    UserResponse register(UserRequest userDto) throws UserException;


    List<User> getAllUsers();
}
