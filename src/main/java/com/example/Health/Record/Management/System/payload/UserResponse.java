package com.example.Health.Record.Management.System.payload;

import com.example.Health.Record.Management.System.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;


    private String userName;

    private String email;

    private LocalDate dateJoined;

    private Role roles;
}
