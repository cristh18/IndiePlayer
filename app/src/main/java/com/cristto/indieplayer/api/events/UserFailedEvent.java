package com.cristto.indieplayer.api.events;

public class UserFailedEvent extends FailedEvent {
    public UserFailedEvent(Throwable throwable) {
        super(throwable);
    }
}
