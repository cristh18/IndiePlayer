package com.cristto.indieplayer.api.models;

import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("title")
    private String title;

    @SerializedName("id")
    private int id;

    @SerializedName("stream_url")
    private String streamURL;

    @SerializedName("artwork_url")
    private String artworkURL;

    public Track(String title, int id, String streamURL, String artworkURL) {
        this.title = title;
        this.id = id;
        this.streamURL = streamURL;
        this.artworkURL = artworkURL;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getStreamURL() {
        return streamURL;
    }

    public String getArtworkURL() {
        return artworkURL;
    }
}
