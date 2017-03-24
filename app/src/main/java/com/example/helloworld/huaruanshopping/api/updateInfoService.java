package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.Response;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by helloworld on 2017/3/22.
 */

public interface updateInfoService {
    //    http://172.16.169.19:8080/user_updateHeadImg.action?id=1&base64_img=
    @FormUrlEncoded
    @POST("/user_updateHeadImg.action")
    Observable<Response> uploadHeadImg(@Field("id") int userid, @Field("base64_img") String img);

    //    http://www.chjcal.cc/user_updateUsername.action?id=1&username=username
    @FormUrlEncoded
    @POST("/user_updateUsername.action")
    Observable<Response> updateUserName(@Field("id") int id, @Field("username") String username);
}
