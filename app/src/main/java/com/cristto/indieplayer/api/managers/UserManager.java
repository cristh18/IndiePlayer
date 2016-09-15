package com.cristto.indieplayer.api.managers;

import android.content.Context;

import com.cristto.indieplayer.api.requests.UserRequest;

public class UserManager implements IUserManager {

    private static UserManager getUserRestManager = new UserManager();

    public static UserManager getInstance() {
        return getUserRestManager;
    }

    public UserManager() {
    }

    @Override
    public void getUser(Context context) {
        UserRequest userRequest = new UserRequest();
        userRequest.getUser(context);
    }
}
