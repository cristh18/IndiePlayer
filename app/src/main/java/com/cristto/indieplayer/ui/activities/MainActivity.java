package com.cristto.indieplayer.ui.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cristto.indieplayer.R;
import com.cristto.indieplayer.api.events.TracksSuccesEvent;
import com.cristto.indieplayer.api.managers.TracksManager;
import com.cristto.indieplayer.api.models.Track;
import com.cristto.indieplayer.databinding.ActivityMainBinding;
import com.cristto.indieplayer.providers.BusProvider;
import com.cristto.indieplayer.ui.adapters.TracksAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private List<Track> tracks;
    private TracksAdapter adapter;
    private RecyclerView recyclerViewTracks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
        buildViews();
    }

    private void init(){
        tracks = new ArrayList<>();
        adapter = new TracksAdapter(this, tracks);
        initViews();
    }

    private void initViews(){
        recyclerViewTracks = activityMainBinding.recyclerViewTracks;
    }

    private void buildViews(){
        recyclerViewTracks.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewTracks.setHasFixedSize(true);
        recyclerViewTracks.setVerticalScrollBarEnabled(true);
        recyclerViewTracks.setAdapter(adapter);
        getTracks();
    }

    private void getTracks(){
        TracksManager tracksManager = new TracksManager();
        tracksManager.getTracks(this);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onTracksSuccess(TracksSuccesEvent successEvent) {
        fillAdapter(successEvent.getTracks());
    }

    private void fillAdapter(List<Track> tracks) {
        adapter.setItems(tracks);
    }
}
