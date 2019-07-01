package com.teztour.mindvallychallenge.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {
    @Expose
    @SerializedName("likes")
    private String likes;
    @Expose
    @SerializedName("photos")
    private String photos;
    @Expose
    @SerializedName("html")
    private String html;
    @Expose
    @SerializedName("self")
    private String self;

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}
