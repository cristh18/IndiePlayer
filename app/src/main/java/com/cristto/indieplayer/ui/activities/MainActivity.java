package com.cristto.indieplayer.ui.activities;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cristto.indieplayer.R;
import com.cristto.indieplayer.api.events.TrackFailedEvent;
import com.cristto.indieplayer.api.events.TracksSuccessEvent;
import com.cristto.indieplayer.api.events.UserByTrackFailedEvent;
import com.cristto.indieplayer.api.events.UserByTrackSuccessEvent;
import com.cristto.indieplayer.api.events.UserFailedEvent;
import com.cristto.indieplayer.api.events.UserSuccessEvent;
import com.cristto.indieplayer.api.managers.TracksManager;
import com.cristto.indieplayer.api.managers.UserByTrackManager;
import com.cristto.indieplayer.api.managers.UserManager;
import com.cristto.indieplayer.api.models.Track;
import com.cristto.indieplayer.api.models.User;
import com.cristto.indieplayer.databinding.ActivityMainBinding;
import com.cristto.indieplayer.providers.RxBus;
import com.cristto.indieplayer.ui.adapters.TracksAdapter;
import com.cristto.indieplayer.ui.adapters.UsersAdapter;

import java.util.List;

import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private TracksAdapter tracksAdapter;
    private UsersAdapter usersAdapter;
    private RecyclerView recyclerViewTracks;
    private LinearLayout containerUserInfo;
    private RxBus rxBus = RxBus.getRxBusInstance();
    private CompositeSubscription subscriptions;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
        buildViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_user:
                getUser();
                return true;
            case R.id.action_tracks:
                getTracks();
                return true;
            case R.id.action_users:
                fetchUserByTracks();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        subscriptions = new CompositeSubscription();
        validateSubscriptions();
    }

    private void validateSubscriptions() {
        subscriptions
                .add(rxBus.toObservable()
                        .subscribe(event -> {
                            if (event instanceof TracksSuccessEvent) {
                                fillTracksAdapter(((TracksSuccessEvent) event).getTracks());
                            } else if (event instanceof TrackFailedEvent) {
                                showRequestError((TrackFailedEvent) event);
                            } else if (event instanceof UserSuccessEvent) {
                                showUserInfo(((UserSuccessEvent) event).getUser());
                            } else if (event instanceof UserFailedEvent) {
                                showRequestError((UserFailedEvent) event);
                            } else if (event instanceof UserByTrackSuccessEvent) {
                                fillUsersAdapter(((UserByTrackSuccessEvent) event).getUsers());
                            } else if (event instanceof UserByTrackFailedEvent) {
                                showRequestError((UserByTrackFailedEvent) event);
                            }
                        }));
    }

    private void showRequestError(TrackFailedEvent event) {
        Toast.makeText(this, getString(R.string.copy_request_error).concat(event.getThrowable().getMessage()), Toast.LENGTH_LONG).show();
    }

    private void showRequestError(UserFailedEvent event) {
        Toast.makeText(this, getString(R.string.copy_request_error).concat(event.getThrowable().getMessage()), Toast.LENGTH_LONG).show();
    }

    private void showRequestError(UserByTrackFailedEvent event) {
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
        containerUserInfo = activityMainBinding.containerUserInfo;
        progressDialog = new ProgressDialog(this);
    }

    private void buildViews() {
        recyclerViewTracks.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewTracks.setHasFixedSize(true);
        recyclerViewTracks.setVerticalScrollBarEnabled(true);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        getTracks();
    }

    private void getTracks() {
        showProgressDialog(getString(R.string.copy_loading));
        TracksManager tracksManager = new TracksManager();
        tracksManager.getTracks(this);
    }

    private void getUser() {
        UserManager userManager = new UserManager();
        userManager.getUser(this);
    }

    private void fetchUserByTracks() {
        showProgressDialog(getString(R.string.copy_loading));
        UserByTrackManager userByTrackManager = new UserByTrackManager();
        userByTrackManager.getUserInfo(this);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }


    private void fillTracksAdapter(List<Track> tracks) {
        containerUserInfo.setVisibility(View.GONE);
        recyclerViewTracks.setVisibility(View.VISIBLE);
        tracksAdapter = new TracksAdapter(this, tracks);
        recyclerViewTracks.setAdapter(tracksAdapter);
        progressDialog.dismiss();
    }

    private void fillUsersAdapter(List<User> users) {
        containerUserInfo.setVisibility(View.GONE);
        recyclerViewTracks.setVisibility(View.VISIBLE);
        usersAdapter = new UsersAdapter(this, users);
        recyclerViewTracks.setAdapter(usersAdapter);
        progressDialog.dismiss();
    }

    private void showUserInfo(User user) {
        recyclerViewTracks.setVisibility(View.GONE);
        containerUserInfo.setVisibility(View.VISIBLE);
        activityMainBinding.textViewUsername.setText(user.getUsername());
    }

    protected void showProgressDialog(String message) {
        progressDialog.setMessage(message);
        progressDialog.show();
    }
}
