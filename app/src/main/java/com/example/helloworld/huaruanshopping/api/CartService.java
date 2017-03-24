package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.ListCart;
import com.example.helloworld.huaruanshopping.bean.OrderProducts;
import com.example.helloworld.huaruanshopping.bean.Response;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by helloworld on 2017/3/13.
 */

public interface CartService {
    //    http://119.29.24.119/cart_getCart.action?userid=1&token=d9ad10220a3585c883379040f3e10bchtrja66a91900htr2
    @FormUrlEncoded
    @POST("/cart_getCart.action")
    Observable<ListCart> getListCart(@Field("userid") int userid, @Field("token") String token);

    //    修改购物车商品数量(数量，购物项id)
//    http://119.29.24.119/cart_updateCartNumber.action?number=1&id=5
    @FormUrlEncoded
    @POST("/cart_updateCartNumber.action")
    Observable<Response> updateCart(@Field("number") int number, @Field("id") int id);

    //    删除购物车Item
//    http://119.29.24.119/cart_deleteCart.action?id=1
    @FormUrlEncoded
    @POST("/cart_deleteCart.action")
    Observable<Response> deleteCartItem(@Field("id") int id);

//    http://119.29.24.119/cart_placeOrderImmediately.action?ptids=1,2,3&numbers=1,2,1
// &suserid=1&token=532a8a18b75079da0c48414014600600d64737f36e330997

    //    判断是否有库存
    @FormUrlEncoded
    @POST("/cart_placeOrderImmediately.action")
    Observable<Response> orderImmediately(@Field("ptids") String ptids,
                                          @Field("numbers") String numbers,
                                          @Field("userid") int userid,
                                          @Field("token") String token);

    //    http://172.16.169.22:8080/forder_saveOrder.action?order_json=json&userid=1
//    http://119.29.24.119/forder_saveOrder.action?order_json=json&userid=1
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @FormUrlEncoded
    @POST("/forder_saveOrder.action")
    Observable<Response> order(@Field("order_json") String listCart,
                               @Field("userid") int userid);


}
