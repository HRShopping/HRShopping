package com.example.helloworld.huaruanshopping.presenter.implView;

import com.example.helloworld.huaruanshopping.bean.ProductListBean;

/**
 * Created by helloworld on 2017/3/11.
 */

public interface IActivityProductDescribe {
    void getProduceDescribeData(ProductListBean productListBean);
    void OnSuccessAddToCar();
    void OnFailedAddToCar();
}
