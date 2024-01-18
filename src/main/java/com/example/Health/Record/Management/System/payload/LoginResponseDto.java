package com.example.Health.Record.Management.System.payload;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String userName;
   private String email;
    private String token;
}
