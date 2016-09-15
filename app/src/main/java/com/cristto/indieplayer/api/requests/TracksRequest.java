package com.cristto.indieplayer.api.requests;

import android.content.Context;
import android.util.Log;

import com.cristto.indieplayer.api.config.ServiceGenerator;
import com.cristto.indieplayer.api.events.TrackFailedEvent;
import com.cristto.indieplayer.api.events.TracksSuccessEvent;
import com.cristto.indieplayer.api.managers.ITracksManager;
import com.cristto.indieplayer.api.models.Track;
import com.cristto.indieplayer.api.services.TracksService;
import com.cristto.indieplayer.providers.RxBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TracksRequest implements ITracksManager {

    private static final String TAG = TracksRequest.class.getSimpleName();
    private Subscription subscription;
    private RxBus rxBus = RxBus.getRxBusInstance();

    @Override
    public void getTracks(Context context) {
        TracksService service = ServiceGenerator.createService(context, TracksService.class);
        subscription = service.getRecentTracks(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Track>>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, ">>> onCompleted");
                        subscription.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (rxBus.hasObservers()) {
                            rxBus.send(new TrackFailedEvent(e));
                        }
                    }

                    @Override
                    public void onNext(List<Track> tracks) {
                        Log.e(TAG, "onNext: ".concat(String.valueOf(tracks.size())));
                        if (rxBus.hasObservers()) {
                            rxBus.send(new TracksSuccessEvent(tracks));
                        }
                    }
                });
    }
}
