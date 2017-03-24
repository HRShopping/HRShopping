package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.ListProduct;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by helloworld on 2017/3/8.
 */

public interface SortProductService {
    //    http://119.29.24.119/product_listProduct.action?cid=1&pageNum=1
    @GET("/product_listProduct.action")
    Observable<ListProduct> getSortListProduct(@Query("cid") int cid, @Query("pageNum") int pageNum);
}
