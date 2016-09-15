package com.cristto.indieplayer.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.cristto.indieplayer.R;
import com.cristto.indieplayer.api.events.TrackFailedEvent;
import com.cristto.indieplayer.api.events.TracksSuccesEvent;
import com.cristto.indieplayer.api.managers.TracksManager;
import com.cristto.indieplayer.api.models.Track;
import com.cristto.indieplayer.databinding.ActivityMainBinding;
import com.cristto.indieplayer.providers.RxBus;
import com.cristto.indieplayer.ui.adapters.TracksAdapter;

import java.util.List;

import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private final String TAG = MainActivity.class.getName();
    private TracksAdapter adapter;
    private RecyclerView recyclerViewTracks;

    private RxBus rxBus = RxBus.getrxBusInstance();
    private CompositeSubscription subscriptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
        buildViews();
    }

    @Override
    public void onStart() {
        super.onStart();
        subscriptions = new CompositeSubscription();
        subscriptions
                .add(rxBus.toObserverable()
                        .subscribe(event -> {
                            if (event instanceof TracksSuccesEvent) {
                                fillAdapter(((TracksSuccesEvent) event).getTracks());
                            } else if (event instanceof TrackFailedEvent) {
                                showRequestError((TrackFailedEvent) event);
                            }
                        }));
    }

    private void showRequestError(TrackFailedEvent event) {
        Toast.makeText(this, getString(R.string.copy_request_error).concat(event.getThrowable().getMessage()), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        subscriptions.clear();
    }

    private void init() {
        initViews();
    }

    private void initViews() {
        recyclerViewTracks = activityMainBinding.recyclerViewTracks;
    }

    private void buildViews() {
        recyclerViewTracks.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewTracks.setHasFixedSize(true);
        recyclerViewTracks.setVerticalScrollBarEnabled(true);
        getTracks();
    }

    private void getTracks() {
        TracksManager tracksManager = new TracksManager();
        tracksManager.getTracks(this);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }


    private void fillAdapter(List<Track> tracks) {
        adapter = new TracksAdapter(this, tracks);
        recyclerViewTracks.setAdapter(adapter);
    }
}
