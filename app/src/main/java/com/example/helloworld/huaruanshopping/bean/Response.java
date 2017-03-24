package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by helloworld on 2017/3/13.
 */

public class Response {
    /**
     * status : success
     * msg : 成功添加至购物车
     */
    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String msg;

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
}
