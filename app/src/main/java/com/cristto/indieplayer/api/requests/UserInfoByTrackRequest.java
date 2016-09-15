package com.cristto.indieplayer.api.requests;

import android.content.Context;
import android.util.Log;

import com.cristto.indieplayer.api.config.ServiceGenerator;
import com.cristto.indieplayer.api.events.UserByTrackFailedEvent;
import com.cristto.indieplayer.api.events.UserByTrackSuccessEvent;
import com.cristto.indieplayer.api.managers.IUserByTrackManager;
import com.cristto.indieplayer.api.models.Track;
import com.cristto.indieplayer.api.models.User;
import com.cristto.indieplayer.api.services.TracksService;
import com.cristto.indieplayer.api.services.UserService;
import com.cristto.indieplayer.providers.RxBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserInfoByTrackRequest implements IUserByTrackManager {

    private final String TAG = UserInfoByTrackRequest.class.getName();
    private RxBus rxBus = RxBus.getrxBusInstance();

    public void getUserInfo(Context context) {
        TracksService tracksService = ServiceGenerator.createService(context, TracksService.class);
        UserService userService = ServiceGenerator.createService(context, UserService.class);
        tracksService.getRecentTracks(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))
                .concatMap(Observable::from)
                .concatMap(
                        (Track track) -> userService.getUserInfo(track.getUserId())
                ).toList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ".concat(e.getMessage()));
                        if (rxBus.hasObservers()) {
                            rxBus.send(new UserByTrackFailedEvent(e));
                        }
                    }

                    @Override
                    public void onNext(List<User> users) {
                        Log.e(TAG, "onNext");
                        if (rxBus.hasObservers()) {
                            rxBus.send(new UserByTrackSuccessEvent(users));
                        }
                    }
                });
    }

}
