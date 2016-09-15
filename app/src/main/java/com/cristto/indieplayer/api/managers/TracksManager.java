package com.cristto.indieplayer.api.managers;

import android.content.Context;

import com.cristto.indieplayer.api.requests.TracksRequest;

public class TracksManager implements ITracksManager {

    private static TracksManager getTracksRestManager = new TracksManager();

    public static TracksManager getInstance() {
        return getTracksRestManager;
    }

    public TracksManager() {
    }

    @Override
    public void getTracks(Context context) {
        TracksRequest tracksRequest = new TracksRequest();
        tracksRequest.getTracks(context);
    }
}
