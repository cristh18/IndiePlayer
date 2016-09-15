package com.cristto.indieplayer.api.requests;

import android.content.Context;

import com.cristto.indieplayer.api.config.ServiceGenerator;
import com.cristto.indieplayer.api.events.UserFailedEvent;
import com.cristto.indieplayer.api.events.UserSuccessEvent;
import com.cristto.indieplayer.api.managers.IUserManager;
import com.cristto.indieplayer.api.models.User;
import com.cristto.indieplayer.api.services.UserService;
import com.cristto.indieplayer.providers.RxBus;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserRequest implements IUserManager {

    private static final String TAG = UserRequest.class.getSimpleName();
    private Subscription subscription;
    private RxBus rxBus = RxBus.getrxBusInstance();

    @Override
    public void getUser(Context context) {
        UserService service = ServiceGenerator.createService(context, UserService.class);
        subscription = service.getUserInfo("254802644")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        subscription.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (rxBus.hasObservers()) {
                            rxBus.send(new UserFailedEvent(e));
                        }
                    }

                    @Override
                    public void onNext(User user) {
                        if (rxBus.hasObservers()) {
                            rxBus.send(new UserSuccessEvent(user));
                        }
                    }
                });
    }
}
