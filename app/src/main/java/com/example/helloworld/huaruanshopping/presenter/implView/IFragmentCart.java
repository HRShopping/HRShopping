package com.example.helloworld.huaruanshopping.presenter.implView;

import com.example.helloworld.huaruanshopping.bean.ListCart;

import java.util.List;

/**
 * Created by helloworld on 2017/3/13.
 */

public interface IFragmentCart {
    void getCartList(List<ListCart.CartBean> cartBeanList);
}
