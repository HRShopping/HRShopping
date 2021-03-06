package com.example.helloworld.huaruanshopping.api;


import com.example.helloworld.huaruanshopping.bean.categoryList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by helloworld on 2017/2/25.
 */

public interface CategoryService {
//    http://119.29.24.119/category_query.action
    @GET("/category_query.action")
    Observable<categoryList> getCategory();
}
