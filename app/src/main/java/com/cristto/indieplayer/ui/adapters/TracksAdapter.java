package com.cristto.indieplayer.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cristto.indieplayer.api.models.Track;
import com.cristto.indieplayer.ui.views.items.TrackItem;

import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Track> tracks;

    public TracksAdapter(Context context, List<Track> tracks) {
        this.context = context;
        this.tracks = tracks;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TrackItem trackItem = new TrackItem(context);
        return new RecyclerView.ViewHolder(trackItem) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (tracks.size() > position) {
            Track track = tracks.get(position);
            ((TrackItem) holder.itemView).setTrack(track, position);
        }
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public void setItems(List<Track> items) {
        this.tracks = items;
        notifyDataSetChanged();
    }

}
