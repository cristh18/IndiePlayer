package com.cristto.indieplayer.api.events;

public class TrackFailedEvent extends FailedEvent{


    public TrackFailedEvent(Throwable throwable) {
        super(throwable);
    }
}
