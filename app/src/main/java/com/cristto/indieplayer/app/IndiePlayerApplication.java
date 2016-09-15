package com.cristto.indieplayer.app;

import android.app.Application;

import com.cristto.indieplayer.R;

public class IndiePlayerApplication extends Application{

    private static IndiePlayerApplication app;

    @Override
    public void onCreate() {
        super.onCreate();

        if (app == null) {
            app = this;
        }
    }

    public static IndiePlayerApplication getApp() {
        return app;
    }

    public static String getBaseUrl(){
        return getApp().getApplicationContext().getString(R.string.base_url);
    }

    public static String getClientId(){
        return getApp().getApplicationContext().getString(R.string.client_id);
    }

    public static String getTracksUrl(){
        return getApp().getApplicationContext().getString(R.string.url_tracks_service);
    }
}
