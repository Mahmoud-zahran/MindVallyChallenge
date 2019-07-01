package com.teztour.mindvallychallenge.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @Expose
    @SerializedName("links")
    private Links links;
    @Expose
    @SerializedName("profile_image")
    private Profile_image profile_image;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("id")
    private String id;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Profile_image getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(Profile_image profile_image) {
        this.profile_image = profile_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
