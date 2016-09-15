package com.cristto.indieplayer.api.managers;

import android.content.Context;

import com.cristto.indieplayer.api.requests.UserInfoByTrackRequest;
import com.cristto.indieplayer.api.requests.UserRequest;

public class UserByTrackManager implements IUserByTrackManager {

    private static UserByTrackManager getUserByTrackRestManager = new UserByTrackManager();

    public static UserByTrackManager getInstance() {
        return getUserByTrackRestManager;
    }

    public UserByTrackManager() {
    }

    @Override
    public void getUserInfo(Context context) {
        UserInfoByTrackRequest userInfoByTrackRequest = new UserInfoByTrackRequest();
        userInfoByTrackRequest.getUserInfo(context);
    }
}
