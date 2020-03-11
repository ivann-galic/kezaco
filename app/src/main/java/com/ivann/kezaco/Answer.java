package com.ivann.kezaco;

import android.os.Parcel;
import android.os.Parcelable;

class Answer implements Parcelable {
    public final String sentence;
    public final boolean isGoodAnswer;

    public Answer(String sentence, boolean isGoodAnswer) {
        this.sentence = sentence;
        this.isGoodAnswer = isGoodAnswer;
    }

    protected Answer(Parcel in) {
        sentence = in.readString();
        isGoodAnswer = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sentence);
        dest.writeByte((byte) (isGoodAnswer ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
