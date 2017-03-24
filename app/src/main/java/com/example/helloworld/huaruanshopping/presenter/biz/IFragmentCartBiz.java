package com.example.helloworld.huaruanshopping.presenter.biz;

import com.example.helloworld.huaruanshopping.bean.ListCart;
import com.example.helloworld.huaruanshopping.bean.OrderProducts;

import java.util.List;

/**
 * Created by helloworld on 2017/3/13.
 */

public interface IFragmentCartBiz {
    void getData(int userid, String token);

    void OrderImmediately(List<Integer> ptids, List<Integer> numbers, int userid, String token);

//    void OrderProducts();
}
