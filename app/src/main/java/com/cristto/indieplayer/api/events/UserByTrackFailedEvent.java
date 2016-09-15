package com.cristto.indieplayer.api.events;

public class UserByTrackFailedEvent extends FailedEvent {
    public UserByTrackFailedEvent(Throwable throwable) {
        super(throwable);
    }
}
