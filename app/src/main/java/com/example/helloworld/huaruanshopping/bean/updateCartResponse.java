package com.example.helloworld.huaruanshopping.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by helloworld on 2017/3/14.
 */

public class updateCartResponse implements Parcelable {
    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String msg;

    protected updateCartResponse(Parcel in) {
        status = in.readString();
        msg = in.readString();
    }

    public static final Creator<updateCartResponse> CREATOR = new Creator<updateCartResponse>() {
        @Override
        public updateCartResponse createFromParcel(Parcel in) {
            return new updateCartResponse(in);
        }

        @Override
        public updateCartResponse[] newArray(int size) {
            return new updateCartResponse[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(msg);
    }
}
