package com.mirhoseini.westwing.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohsen on 29/06/16.
 */
public class Videos implements Parcelable {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.thumbnail);
    }

    public Videos() {
    }

    public Videos(String url, String thumbnail) {
        this.url = url;
        this.thumbnail = thumbnail;
    }

    protected Videos(Parcel in) {
        this.url = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Parcelable.Creator<Videos> CREATOR = new Parcelable.Creator<Videos>() {
        @Override
        public Videos createFromParcel(Parcel source) {
            return new Videos(source);
        }

        @Override
        public Videos[] newArray(int size) {
            return new Videos[size];
        }
    };
}
