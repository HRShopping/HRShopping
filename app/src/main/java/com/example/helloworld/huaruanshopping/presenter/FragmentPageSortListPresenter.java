package com.example.helloworld.huaruanshopping.presenter;

import android.util.Log;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.ListProduct;
import com.example.helloworld.huaruanshopping.presenter.biz.IFragmentPageSortListBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentBaseView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/3/8.
 */

public class FragmentPageSortListPresenter implements IFragmentPageSortListBiz {
    private String TAG = "111";
    IFragmentBaseView iFragmentPageSortList;

    public FragmentPageSortListPresenter(IFragmentBaseView iFragmentPageSortList) {
        this.iFragmentPageSortList = iFragmentPageSortList;
    }

    @Override
    public void getPageSortListProduct(int cid, int pageNum) {
        Observable<ListProduct> observable = HttpMethods.getInstance().getSortProductService().getSortListProduct(cid, pageNum);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListProduct>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListProduct listProduct) {
//                        Log.d(TAG, "onNext: " + listProduct.getProductList().size());
                        iFragmentPageSortList.showLoading();
                        iFragmentPageSortList.showData(listProduct.getProductList());
//                        Log.d(TAG, "onNext: " + listProduct.getProductList().get(0).getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        iFragmentPageSortList.hideLoading();

                    }
                });
    }
}
