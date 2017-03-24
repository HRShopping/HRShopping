package com.example.helloworld.huaruanshopping.presenter;

import android.util.Log;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.Response;
import com.example.helloworld.huaruanshopping.bean.productDescribe;
import com.example.helloworld.huaruanshopping.presenter.biz.IActivityProductDescribeBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityProductDescribe;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/3/11.
 */

public class ActivityProductDescribePresenter implements IActivityProductDescribeBiz {
    IActivityProductDescribe implView;
    private final static String TAG = "111";

    public ActivityProductDescribePresenter(IActivityProductDescribe implView) {
        this.implView = implView;
    }

    @Override
    public void getProductDescribe(int pid) {
        Observable<productDescribe> observable = HttpMethods.getInstance().getProductDescribeService().getProductDescribe(pid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<productDescribe>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(productDescribe product) {
                        Log.d(TAG, "onNext: " + product.getProductList().toString());
                        implView.getProduceDescribeData(product.getProductList().get(0));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addToCar(int id, int number, int userid) {
        Observable<Response> observable = HttpMethods.getInstance().getPostProductService().getPostRespnose(id, number, userid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: " + response.getMsg()+response.getStatus());
                        implView.OnSuccessAddToCar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
