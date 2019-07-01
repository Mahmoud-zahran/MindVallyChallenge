package com.teztour.mindvallychallenge.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories
{
    @Expose
    @SerializedName("photo_count")
    private String photo_count;

    @Expose
    @SerializedName("links")
    private Links links;

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("title")
    private String title;


    public String getPhoto_count ()
    {
        return photo_count;
    }

    public void setPhoto_count (String photo_count)
    {
        this.photo_count = photo_count;
    }

    public Links getLinks ()
    {
        return links;
    }

    public void setLinks (Links links)
    {
        this.links = links;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [photo_count = "+photo_count+", links = "+links+", id = "+id+", title = "+title+"]";
    }
}