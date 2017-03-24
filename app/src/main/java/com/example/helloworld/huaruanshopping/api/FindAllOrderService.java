package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.Response;
import com.example.helloworld.huaruanshopping.bean.orderList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by helloworld on 2017/3/18.
 */

public interface FindAllOrderService {
    //   http://119.29.24.119/forder_findAllOrder.action?userid=1&pageNum=1&token=532a8a18b75079da0c48414014600600d64737f36e330997
    @FormUrlEncoded
    @POST("/forder_findAllOrder.action")
    Observable<orderList> getAllOrder(@Field("userid") int id, @Field("pageNum") int pageNum, @Field("token") String token);

    //    http://119.29.24.119/forder_deleteOrder.action?id=id
    @FormUrlEncoded
    @POST("/forder_deleteOrder.action")
    Observable<Response> deleteOrder(@Field("id") String id);

    //    http://119.29.24.119/forder_cancelOrder.action?id=id
    @FormUrlEncoded
    @POST("/forder_cancelOrder.action")
    Observable<Response> cancelOrder(@Field("id") String id);
}
