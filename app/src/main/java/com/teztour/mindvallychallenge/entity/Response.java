package com.teztour.mindvallychallenge.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response
    {
        @Expose
        @SerializedName("urls")
        private Urls urls;

        @Expose
        @SerializedName("current_user_collections")
        private String[] current_user_collections;

        @Expose
        @SerializedName("color")
        private String color;

        @Expose
        @SerializedName("width")
        private String width;

        @Expose
        @SerializedName("created_at")
        private String created_at;

        @Expose
        @SerializedName("links")
        private Links links;

        @Expose
        @SerializedName("id")
        private String id;

        @Expose
        @SerializedName("categories")
        private Categories[] categories;

        @Expose
        @SerializedName("liked_by_user")
        private String liked_by_user;

        @Expose
        @SerializedName("user")
        private User user;

        @Expose
        @SerializedName("height")
        private String height;

        @Expose
        @SerializedName("likes")
        private String likes;

        public Urls getUrls ()
        {
            return urls;
        }

        public void setUrls (Urls urls)
        {
            this.urls = urls;
        }

        public String[] getCurrent_user_collections ()
        {
            return current_user_collections;
        }

        public void setCurrent_user_collections (String[] current_user_collections)
        {
            this.current_user_collections = current_user_collections;
        }

        public String getColor ()
        {
            return color;
        }

        public void setColor (String color)
        {
            this.color = color;
        }

        public String getWidth ()
        {
            return width;
        }

        public void setWidth (String width)
        {
            this.width = width;
        }

        public String getCreated_at ()
        {
            return created_at;
        }

        public void setCreated_at (String created_at)
        {
            this.created_at = created_at;
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

        public Categories[] getCategories ()
        {
            return categories;
        }

        public void setCategories (Categories[] categories)
        {
            this.categories = categories;
        }

        public String getLiked_by_user ()
        {
            return liked_by_user;
        }

        public void setLiked_by_user (String liked_by_user)
        {
            this.liked_by_user = liked_by_user;
        }

        public User getUser ()
        {
            return user;
        }

        public void setUser (User user)
        {
            this.user = user;
        }

        public String getHeight ()
        {
            return height;
        }

        public void setHeight (String height)
        {
            this.height = height;
        }

        public String getLikes ()
        {
            return likes;
        }

        public void setLikes (String likes)
        {
            this.likes = likes;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [urls = "+urls+", current_user_collections = "+current_user_collections+", color = "+color+", width = "+width+", created_at = "+created_at+", links = "+links+", id = "+id+", categories = "+categories+", liked_by_user = "+liked_by_user+", user = "+user+", height = "+height+", likes = "+likes+"]";
        }

}
