package com.ivann.kezaco;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;

public class Media implements Parcelable {

    private final String media;
    private final String theme;
    private final String [] answer;

    public Media(String media, String theme, String[] answer) {
        this.media = media;
        this.theme = theme;
        this.answer = answer;
    }

    protected Media(Parcel in) {
        media = in.readString();
        theme = in.readString();
        answer = in.createStringArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(media);
        dest.writeString(theme);
        dest.writeStringArray(answer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };
}
