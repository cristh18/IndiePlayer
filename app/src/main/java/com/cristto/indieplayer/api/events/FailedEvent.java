package com.cristto.indieplayer.api.events;

public class FailedEvent {

    private Throwable throwable;

    public FailedEvent(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
