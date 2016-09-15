package com.cristto.indieplayer.api.services;

import com.cristto.indieplayer.api.models.User;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface UserService {

    @GET("/users/{user_id}/?client_id=289d8f92b9bb8df920b1d48726e18dc9")
    Observable<User> getUserInfo(@Path("user_id") String userId);
}
