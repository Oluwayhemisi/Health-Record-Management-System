package com.example.Health.Record.Management.System.payload;

import com.example.Health.Record.Management.System.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;

    private String userName;

    private String email;

    private String password;

    private Role roles;

}
