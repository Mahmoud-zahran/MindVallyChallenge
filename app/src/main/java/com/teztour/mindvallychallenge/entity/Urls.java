package com.teztour.mindvallychallenge.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Urls {
    @Expose
    @SerializedName("thumb")
    private String thumb;
    @Expose
    @SerializedName("small")
    private String small;
    @Expose
    @SerializedName("regular")
    private String regular;
    @Expose
    @SerializedName("full")
    private String full;
    @Expose
    @SerializedName("raw")
    private String raw;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
