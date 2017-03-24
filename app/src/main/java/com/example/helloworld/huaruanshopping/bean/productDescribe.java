package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by helloworld on 2017/3/12.
 */

public class productDescribe {
    @SerializedName("status")
    private String status;
    @SerializedName("product")
    private List<ProductListBean> productList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }
}
