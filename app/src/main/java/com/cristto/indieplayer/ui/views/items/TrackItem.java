package com.cristto.indieplayer.ui.views.items;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cristto.indieplayer.R;
import com.cristto.indieplayer.api.models.Track;
import com.cristto.indieplayer.databinding.ItemTrackBinding;
import com.squareup.picasso.Picasso;

public class TrackItem extends RelativeLayout {

    private ItemTrackBinding itemTrackBinding;
    private Track track;
    private int position;
    private TextView textViewTrack;
    private ImageView imageViewTrack;

    public TrackItem(Context context) {
        super(context);
        init();
    }

    public TrackItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TrackItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        itemTrackBinding = DataBindingUtil.inflate(inflater, R.layout.item_track, this, false);
        initViews();
    }

    private void initViews() {
        textViewTrack = itemTrackBinding.trackTitle;
        imageViewTrack = itemTrackBinding.trackImage;
    }

    public void setTrack(Track track, int position) {
        this.track = track;
        this.position = position;
        loadViews();
    }

    private void loadViews() {
        textViewTrack.setText(track.getTitle());
        Picasso.with(getContext()).load(track.getArtworkURL()).into(imageViewTrack);
    }
}
