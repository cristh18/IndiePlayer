package com.cristto.indieplayer.api.events;

import com.cristto.indieplayer.api.models.User;

public class UserSuccessEvent {

    private User user;

    public UserSuccessEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
