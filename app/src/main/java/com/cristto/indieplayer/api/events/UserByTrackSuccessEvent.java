package com.cristto.indieplayer.api.events;

import com.cristto.indieplayer.api.models.User;

import java.util.List;

public class UserByTrackSuccessEvent {

    private List<User> users;

    public UserByTrackSuccessEvent(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
