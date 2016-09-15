package com.cristto.indieplayer.api.services;

import com.cristto.indieplayer.api.models.Track;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TracksService {

    @GET("/tracks?client_id=289d8f92b9bb8df920b1d48726e18dc9")
    Observable<List<Track>> getRecentTracks(@Query("created_at[from]") String date);

}
