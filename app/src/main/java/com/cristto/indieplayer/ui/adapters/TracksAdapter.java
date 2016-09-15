package com.cristto.indieplayer.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristto.indieplayer.R;
import com.cristto.indieplayer.api.models.Track;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {

    private Context context;
    private List<Track> tracks;

    public TracksAdapter(Context context, List<Track> tracks) {
        this.context = context;
        this.tracks = tracks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.item_track, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewTrack.setText(tracks.get(position).getTitle());
        Picasso.with(context).load(tracks.get(position).getArtworkURL()).into(holder.imageViewTrack);
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTrack;
        public ImageView imageViewTrack;

        public ViewHolder(final View inflate) {
            super(inflate);
            textViewTrack = (TextView) inflate.findViewById(R.id.textView_track_title);
            imageViewTrack = (ImageView) inflate.findViewById(R.id.imageView_track_image);
        }
    }

}
