package com.cristto.indieplayer.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristto.indieplayer.R;
import com.cristto.indieplayer.api.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private Context context;
    private List<User> users;

    public UsersAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.item_track, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewTrack.setText(context.getString(R.string.copy_username).concat(users.get(position).getUsername()));
        Picasso.with(context).load(users.get(position).getAvatarUrl()).into(holder.imageViewTrack);
    }

    @Override
    public int getItemCount() {
        return users.size();
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
