package com.cristto.indieplayer.api.events;

public class TrackFailedEvent {

    private Throwable throwable;

    public TrackFailedEvent(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
