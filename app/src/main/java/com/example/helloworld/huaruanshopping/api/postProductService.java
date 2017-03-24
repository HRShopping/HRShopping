package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.Response;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by helloworld on 2017/3/13.
 */

public interface postProductService {
    //    http://119.29.24.119/protype_addCart.action?id=1&number=1&userid=1
    @FormUrlEncoded
    @POST("/protype_addCart.action")
    Observable<Response> getPostRespnose(@Field("id") int id, @Field("number") int number, @Field("userid") int userid);
}
