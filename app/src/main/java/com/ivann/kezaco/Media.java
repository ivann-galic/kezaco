package com.ivann.kezaco;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Media implements Parcelable {

    public final String media;
    public final String theme;
    public final ArrayList<Answer> answers;

    public Media(String media, String theme, ArrayList<Answer> answers) {
        this.media = media;
        this.theme = theme;
        this.answers = answers;
    }

    protected Media(Parcel in) {
        media = in.readString();
        theme = in.readString();
        answers = in.createTypedArrayList(Answer.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(media);
        dest.writeString(theme);
        dest.writeTypedList(answers);
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

    @Override
    public String toString() {
        return "Media{" +
                "media='" + media + '\'' +
                ", theme='" + theme + '\'' +
                ", answers=" + answers +
                '}';
    }
}