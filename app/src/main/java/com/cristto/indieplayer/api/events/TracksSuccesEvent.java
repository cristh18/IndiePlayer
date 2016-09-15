package com.cristto.indieplayer.api.events;

import com.cristto.indieplayer.api.models.Track;

import java.util.List;

public class TracksSuccesEvent {

    private List<Track> tracks;

    public TracksSuccesEvent(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
