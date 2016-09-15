package com.cristto.indieplayer.api.events;

import com.cristto.indieplayer.api.models.Track;

import java.util.List;

public class TracksSuccessEvent {

    private List<Track> tracks;

    public TracksSuccessEvent(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
