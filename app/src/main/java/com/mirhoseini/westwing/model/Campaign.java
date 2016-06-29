package com.mirhoseini.westwing.model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohsen on 29/06/16.
 */
public class Campaign implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("subline")
    @Expose
    private String subline;

    @SerializedName("navigation_url")
    @Expose
    private String navigationUrl;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("start_time_formatted")
    @Expose
    private String startTimeFormatted;

    @SerializedName("images")
    @Expose
    private Images images;

    @SerializedName("videos")
    @Expose
    private List<Videos> videos = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubline() {
        return subline;
    }

    public void setSubline(String subline) {
        this.subline = subline;
    }

    public String getNavigationUrl() {
        return navigationUrl;
    }

    public void setNavigationUrl(String navigationUrl) {
        this.navigationUrl = navigationUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTimeFormatted() {
        return startTimeFormatted;
    }

    public void setStartTimeFormatted(String startTimeFormatted) {
        this.startTimeFormatted = startTimeFormatted;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List<Videos> getVideos() {
        return videos;
    }

    public void setVideos(List<Videos> videos) {
        this.videos = videos;
    }



    /*

    @SerializedName("newsletter_urlkey")
    @Expose
    private String newsletterUrlkey;
    @SerializedName("url_key")
    @Expose
    private String urlKey;
    @SerializedName("banner_url_original")
    @Expose
    private Object bannerUrlOriginal;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("message_line_style")
    @Expose
    private String messageLineStyle;
    @SerializedName("promotion_line")
    @Expose
    private Object promotionLine;
    @SerializedName("themeday")
    @Expose
    private Object themeday;
    @SerializedName("end_time_formatted")
    @Expose
    private String endTimeFormatted;
    @SerializedName("remaining_days_formatted")
    @Expose
    private String remainingDaysFormatted;
    @SerializedName("message_line")
    @Expose
    private String messageLine;
    @SerializedName("banner_url")
    @Expose
    private String bannerUrl;
    @SerializedName("badge_url")
    @Expose
    private String badgeUrl;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("id_campaign")
    @Expose
    private String idCampaign;
    */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.subline);
        dest.writeString(this.navigationUrl);
        dest.writeString(this.description);
        dest.writeString(this.startTimeFormatted);
        dest.writeParcelable(this.images, flags);
        dest.writeList(this.videos);
    }

    public Campaign() {
    }

    public Campaign(String name, String subline, String navigationUrl, String description, String startTimeFormatted, Images images, List<Videos> videos) {
        this.name = name;
        this.subline = subline;
        this.navigationUrl = navigationUrl;
        this.description = description;
        this.startTimeFormatted = startTimeFormatted;
        this.images = images;
        this.videos = videos;
    }

    protected Campaign(android.os.Parcel in) {
        this.name = in.readString();
        this.subline = in.readString();
        this.navigationUrl = in.readString();
        this.description = in.readString();
        this.startTimeFormatted = in.readString();
        this.images = in.readParcelable(Images.class.getClassLoader());
        this.videos = new ArrayList<Videos>();
        in.readList(this.videos, Videos.class.getClassLoader());
    }

    public static final android.os.Parcelable.Creator<Campaign> CREATOR = new android.os.Parcelable.Creator<Campaign>() {
        @Override
        public Campaign createFromParcel(android.os.Parcel source) {
            return new Campaign(source);
        }

        @Override
        public Campaign[] newArray(int size) {
            return new Campaign[size];
        }
    };
}
