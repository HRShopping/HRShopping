package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by helloworld on 2017/2/21.
 */

public class ListProduct {
    @SerializedName("status")
    private String status;
    @SerializedName("productList")
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
