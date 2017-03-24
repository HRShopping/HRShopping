package com.example.helloworld.huaruanshopping.presenter;

import android.util.Log;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.ListProduct;
import com.example.helloworld.huaruanshopping.listener.OnBaseListener;
import com.example.helloworld.huaruanshopping.presenter.biz.IFragmentHomeBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentBaseView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/2/21.
 */

public class FragmentHomePresenter implements IFragmentHomeBiz {
    IFragmentBaseView iFragmentHome;

    public FragmentHomePresenter(IFragmentBaseView iFragmentHome) {
        this.iFragmentHome = iFragmentHome;
    }

    public void getHomePrudoct(int pageNum, int flag) {
        Observable observable = HttpMethods.getInstance().getHomeProductApi().getHomeListProduct(pageNum, flag);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListProduct>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListProduct listProduct) {
//                        Log.d("aa", "onNext: " + listProduct.getProductList().get(0).getProtypeSet().get(0).getPic());
//                        iFragmentHome.showLoading();
                        if (listProduct != null) {
                            if (listProduct.getProductList().size() % 4 == 0) {
                                iFragmentHome.showData(listProduct.getProductList());
                            }
                        }
                        iFragmentHome.showFailedError();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        iFragmentHome.hideLoading();
                    }
                });
    }

    @Override
    public void getMoreHomePrudoctData(int pageNum) {

    }
}
