package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.ListProduct;
import com.example.helloworld.huaruanshopping.bean.Product;
import com.example.helloworld.huaruanshopping.bean.productDescribe;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by helloworld on 2017/3/11.
 */

public interface ProductDescribeService {
    //    /product_getProduct.action?pid=1
    @GET("/product_getProduct.action")
    Observable<productDescribe> getProductDescribe(@Query("pid") int pid);
}
