package com.team.forapps.user.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String id;
    private String password;
    private String checkPassword;
}
