package com.example.helloworld.huaruanshopping.bean;

/**
 * Created by helloworld on 2017/2/25.
 */

import com.google.gson.annotations.SerializedName;


public class Category implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @SerializedName("id")
    private Integer id;
    @SerializedName("type")
    private String type;
    @SerializedName("hot")
    private Boolean hot;
    // Constructors

    /**
     * default constructor
     */
    public Category() {
    }

    /**
     * full constructor
     */
    public Category(String type, Boolean hot) {
        this.type = type;
        this.hot = hot;
    }

    /**
     * full constructor
     */
    public Category(Integer id, String type, Boolean hot) {
        this.id = id;
        this.type = type;
        this.hot = hot;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getHot() {
        return this.hot;
    }

    public void setHot(Boolean hot) {
        this.hot = hot;
    }
}