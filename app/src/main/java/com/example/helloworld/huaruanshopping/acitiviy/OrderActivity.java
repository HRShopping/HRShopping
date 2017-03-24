package com.example.helloworld.huaruanshopping.acitiviy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.TabLayoutAdapter;
import com.example.helloworld.huaruanshopping.fragment.fragmentAddress;
import com.example.helloworld.huaruanshopping.fragment.fragmentConfirm;
import com.example.helloworld.huaruanshopping.fragment.fragmentPayment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helloworld on 2017/1/22.
 */

public class OrderActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager mViewpager;
    private TabLayout mTabLayout;
    private TabLayoutAdapter adapter;
    private FragmentManager fm;
    private List<String> mListTitle = new ArrayList<>();
    private List<Fragment> mListFragment = new ArrayList<>();
    private TabLayout.Tab tabAddress;
    private TabLayout.Tab tabPayment;
    private TabLayout.Tab tabConfirm;
    private LinearLayout continueLayout;
    private TextView continueTv;
    fragmentAddress fragmentAddress = new fragmentAddress();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //透明导航栏
        fm = getSupportFragmentManager();
        initData();
        initToolbar();
        initContinue();
        initLayout();
    }

    private void initContinue() {
        continueLayout = (LinearLayout) findViewById(R.id.continueLayout);
        continueTv = (TextView) findViewById(R.id.continueTv);
        continueLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断当前页面
                if (mTabLayout.getSelectedTabPosition() == 0) {
//                    Log.d("TAG", "onTabSelected: "+fragmentAddress.sendInfo().get(0).toString());
//                    Toast.makeText(OrderActivity.this, fragmentAddress.sendInfo().get(0).toString(), Toast.LENGTH_SHORT).show();
                    tabPayment.select();
                } else if (mTabLayout.getSelectedTabPosition() == 1) {
                    tabConfirm.select();
                } else if (mTabLayout.getSelectedTabPosition() == 2) {
                    Toast.makeText(OrderActivity.this, "正在支付", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        mListTitle.add(new String("快递"));
        mListTitle.add(new String("支付方式"));
        mListTitle.add(new String("确认"));
        mListFragment.add(fragmentAddress);
        mListFragment.add(new fragmentPayment());
        mListFragment.add(new fragmentConfirm());
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new TabLayoutAdapter(fm, mListFragment, mListTitle);

        mViewpager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewpager);
        tabAddress = mTabLayout.getTabAt(0);
        tabPayment = mTabLayout.getTabAt(1);
        tabConfirm = mTabLayout.getTabAt(2);
        //设置Tab的图标
        tabAddress.setIcon(R.drawable.white_yes_16);
        tabPayment.setIcon(R.drawable.white_yes_16);
        tabConfirm.setIcon(R.drawable.white_yes_16);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == tabAddress) {
                    continueTv.setText("继续选择支付方式");
                    tabAddress.setIcon(R.drawable.white_yes_16);
                    tabPayment.setIcon(R.drawable.white_yes_16);
                    tabConfirm.setIcon(R.drawable.white_yes_16);
                } else if (tab == tabPayment) {
                    continueTv.setText("继续确认订单");
                    tabAddress.setIcon(R.drawable.green_yes_16);
                    tabPayment.setIcon(R.drawable.white_yes_16);
                    tabConfirm.setIcon(R.drawable.white_yes_16);
                } else if (tab == tabConfirm) {
                    continueTv.setText("马上下单");
                    tabAddress.setIcon(R.drawable.green_yes_16);
                    tabPayment.setIcon(R.drawable.green_yes_16);
                    tabConfirm.setIcon(R.drawable.white_yes_16);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
