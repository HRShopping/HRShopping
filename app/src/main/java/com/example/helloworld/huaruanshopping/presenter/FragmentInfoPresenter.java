package com.example.helloworld.huaruanshopping.presenter;

import android.util.Log;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.Response;
import com.example.helloworld.huaruanshopping.fragment.fragmentInfo;
import com.example.helloworld.huaruanshopping.presenter.biz.IFragmentInfoBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentUpdateInfo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by helloworld on 2017/3/22.
 */

public class FragmentInfoPresenter implements IFragmentInfoBiz {
    IFragmentUpdateInfo fragmentUpdateInfo;
    public static final String TAG = "111";

    public FragmentInfoPresenter(IFragmentUpdateInfo fragmentInfo) {
        this.fragmentUpdateInfo = fragmentInfo;
    }

    public void updateUserName(int id, String userName) {
        Observable<Response> observable = HttpMethods.getInstance().getUpdateInfoService().updateUserName(id, userName);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: " + response.getStatus());
                        fragmentUpdateInfo.onSuccessUpdate();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
