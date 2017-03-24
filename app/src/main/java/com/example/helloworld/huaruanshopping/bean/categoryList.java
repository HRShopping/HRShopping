package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by helloworld on 2017/3/7.
 */

public class categoryList {
    @SerializedName("categoryList")
    private List<Category> categoryList;
    @SerializedName("status")
    private String status;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
