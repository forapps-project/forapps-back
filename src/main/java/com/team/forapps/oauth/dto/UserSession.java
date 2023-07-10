package com.team.forapps.oauth.dto;

import com.team.forapps.user.domain.User;

public class UserSession {

    private final String email;

    private UserSession(User user) {
        this.email = user.getEmail();
    }

    public static UserSession of(User user) {
        return new UserSession(user);
    }

    public String getId() {
        return email;
    }
}
