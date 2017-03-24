package com.example.helloworld.huaruanshopping.acitiviy;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.ProductListBean;
import com.example.helloworld.huaruanshopping.bean.ProtypeSetBean;
import com.example.helloworld.huaruanshopping.presenter.ActivityProductDescribePresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityProductDescribe;
import com.example.helloworld.huaruanshopping.view.GlideImageLoader;
import com.youth.banner.Banner;


import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.QBadgeView;

/**
 * Created by helloworld on 2017/1/19.
 */

public class ProductDescribeActivity extends AppCompatActivity implements IActivityProductDescribe {
    Toolbar toolbar;
    Banner banner;
    RadioGroup sortGroup;
    private List<String> mpicList = new ArrayList<>();
    private Button carBtn;
    private TextView addToCar;
    private int defaultCarNum = 5;
    private TextView productName;
    private TextView productPrice;
    private ImageButton addBtn, reduceBtn;
    private TextView amountTv;
    private int defaultnumber = 1;
    ActivityProductDescribePresenter presenter;
    int pid = 0;
    private final static String TAG = "111";
    private TextView productRemark;
    private float y;
    private int id = 0;
    private int userid = 1;
    private RelativeLayout contentLayout;
    private QBadgeView qBadgeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_describe);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //透明导航栏
        presenter = new ActivityProductDescribePresenter(this);
        initToolbar();
        initView();
        initData();
    }

    private void initView() {
        contentLayout = (RelativeLayout) findViewById(R.id.Contentlayout);
        addBtn = (ImageButton) findViewById(R.id.addBtn);
        reduceBtn = (ImageButton) findViewById(R.id.reduceBtn);
        amountTv = (TextView) findViewById(R.id.carItemNum);
        carBtn = (Button) findViewById(R.id.carBtn);
        productPrice = (TextView) findViewById(R.id.describePrice);
        productName = (TextView) findViewById(R.id.describeName);
        productRemark = (TextView) findViewById(R.id.describeRemark);
        qBadgeView = (QBadgeView) new QBadgeView(getApplicationContext()).bindTarget(carBtn).setBadgeNumber(defaultCarNum);
        y = qBadgeView.getTranslationY();
        addToCar = (TextView) findViewById(R.id.addToCar);
        addToCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToCar(id, defaultnumber, userid);
            }
        });
        reduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountTv.setText("" + (--defaultnumber));
                addBtn.setClickable(true);
                if (defaultnumber == 1) {
                    reduceBtn.setClickable(false);
                }
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(reduceBtn, "ScaleX", 1.0f, 1.4f, 1.0f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(reduceBtn, "ScaleY", 1.0f, 1.4f, 1.0f);
                AnimatorSet set = new AnimatorSet();
                set.play(animator1).with(animator2);
                set.setDuration(400).start();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountTv.setText("" + (++defaultnumber));
                reduceBtn.setClickable(true);

                if (defaultnumber == 20) {
                    addBtn.setClickable(false);
                }
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(addBtn, "ScaleX", 1.0f, 1.4f, 1.0f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(addBtn, "ScaleY", 1.0f, 1.4f, 1.0f);
                AnimatorSet set = new AnimatorSet();
                set.play(animator1).with(animator2);
                set.setDuration(400).start();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        pid = intent.getIntExtra("pid", 0);
        presenter.getProductDescribe(1);
        Log.d(TAG, "initData: " + pid);
    }

    private void initBanner() {
        banner = (Banner) findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(mpicList);
        banner.isAutoPlay(false);
        banner.start();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initSort(final List<ProtypeSetBean> ListSort) {
        sortGroup = (RadioGroup) findViewById(R.id.sortGroup);
        //颜色状态选择器
        ColorStateList csl = getResources().getColorStateList(R.color.describe_color_selector);
        for (int i = 0; i < ListSort.size(); i++) {
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 10;
            params.bottomMargin = 10;
            RadioButton radioButton = new RadioButton(this);
            radioButton.setBackgroundResource(R.drawable.describe_sort_selector);
            radioButton.setTextColor(csl);
            radioButton.setText("类别" + ListSort.get(i).getName());
            radioButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            radioButton.setLayoutParams(params);

            final int finalI = i;
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Log.d(TAG, "onCheckedChanged: " + ListSort.get(finalI).getId());
                    }
                }
            });
            sortGroup.addView(radioButton);
            if (i == 0) {
                radioButton.setChecked(true);
                id = ListSort.get(i).getId();
            }
        }

    }

    @Override
    public void getProduceDescribeData(ProductListBean productListBean) {
        productName.setText(productListBean.getName());
        productPrice.setText("￥" + productListBean.getPrice());
        initSort(productListBean.getProtypeSet());
        setBannerPics(productListBean.getProtypeSet());
        productRemark.setText(productListBean.getRemark());
    }

    private void setBannerPics(List<ProtypeSetBean> ListSort) {
//        mpicList.clear();
        for (int i = 0; i < ListSort.size(); i++) {
            Log.d(TAG, "setBannerPics: " + ListSort.get(i).getPic());
            mpicList.add(HttpMethods.BASE_URL + ListSort.get(i).getPic());
        }
        initBanner();
    }

    @Override
    public void OnSuccessAddToCar() {
        Snackbar.make(contentLayout, "成功添加至购物车", Snackbar.LENGTH_SHORT).show();
        qBadgeView.setBadgeNumber(++defaultCarNum);
        addToCarAnimation(qBadgeView);
    }

    @Override
    public void OnFailedAddToCar() {

    }

    public void addToCarAnimation(View view) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "ScaleX", 1.0f, 1.3f, 1.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "ScaleY", 1.0f, 1.3f, 1.0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(view, "translationY", y, y - 30, y);
        AnimatorSet set = new AnimatorSet();
        set.play(animator1).with(animator2).with(animator3);
        set.setDuration(1500).start();
    }
}
