package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.ListProduct;


import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by helloworld on 2017/2/22.
 */

public interface HomeProductService {
    //    product_listProduct.action?cid=1&&pageNum=1
//    http://119.29.24.119/product_findNewestProduct.action?pageNum=1
    @GET("/product_findNewest_Or_HighestSalesProduct.action")
    Observable<ListProduct> getHomeListProduct(@Query("pageNum") int pageNum, @Query("flag") int flag);
}
