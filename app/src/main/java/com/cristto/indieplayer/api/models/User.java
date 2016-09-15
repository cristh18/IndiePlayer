package com.cristto.indieplayer.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("permalink_url")
    @Expose
    private String permalinkUrl;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("website")
    @Expose
    private Object website;
    @SerializedName("website_title")
    @Expose
    private Object websiteTitle;
    @SerializedName("track_count")
    @Expose
    private Integer trackCount;
    @SerializedName("playlist_count")
    @Expose
    private Integer playlistCount;
    @SerializedName("plan")
    @Expose
    private String plan;
    @SerializedName("followers_count")
    @Expose
    private Integer followersCount;
    @SerializedName("followings_count")
    @Expose
    private Integer followingsCount;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getUsername() {
        return username;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getUri() {
        return uri;
    }

    public String getPermalinkUrl() {
        return permalinkUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Object getCountry() {
        return country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public Object getCity() {
        return city;
    }

    public Object getWebsite() {
        return website;
    }

    public Object getWebsiteTitle() {
        return websiteTitle;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public Integer getPlaylistCount() {
        return playlistCount;
    }

    public String getPlan() {
        return plan;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public Integer getFollowingsCount() {
        return followingsCount;
    }
}
