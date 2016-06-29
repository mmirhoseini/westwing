
package com.mirhoseini.westwing.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohsen on 29/06/16.
 */
public class Images implements Parcelable {

    @SerializedName("banner")
    @Expose
    private Banner banner;

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.banner, flags);
    }

    public Images() {
    }

    public Images(Banner banner) {
        this.banner = banner;
    }

    protected Images(Parcel in) {
        this.banner = in.readParcelable(Banner.class.getClassLoader());
    }

    public static final Parcelable.Creator<Images> CREATOR = new Parcelable.Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel source) {
            return new Images(source);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };
}
