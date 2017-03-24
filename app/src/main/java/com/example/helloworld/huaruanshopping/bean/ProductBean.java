package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by helloworld on 2017/2/21.
 */

public class ProductBean {
    /**
     * id : 8
     * name : qqq
     * price : 43.0
     */
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
