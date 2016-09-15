package com.cristto.indieplayer.api.services;

import com.cristto.indieplayer.app.IndiePlayerApplication;

public abstract class HttpConstants {

    public static final String TRACKS_SERVICE_URL = IndiePlayerApplication.getTracksUrl();
    public static final String CLIENT_ID = IndiePlayerApplication.getClientId();

    protected abstract String getTrackServiceUrl();

}
