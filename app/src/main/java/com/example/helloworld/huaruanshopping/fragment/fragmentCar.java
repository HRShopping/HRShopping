package com.example.helloworld.huaruanshopping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.acitiviy.OrderActivity;
import com.example.helloworld.huaruanshopping.adapter.CarListAdatper;
import com.example.helloworld.huaruanshopping.bean.ListCart;
import com.example.helloworld.huaruanshopping.bean.OrderProducts;
import com.example.helloworld.huaruanshopping.bean.Product;
import com.example.helloworld.huaruanshopping.presenter.FragmentCartPresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentCart;
import com.example.helloworld.huaruanshopping.util.DensityUtil;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helloworld on 2017/1/15.
 */

public class fragmentCar extends Fragment implements IFragmentCart {
    private View view;
    private SwipeMenuListView swipeMenuListView;
    private List<ListCart.CartBean> mProductList = new ArrayList<>();
    private CarListAdatper adapter;
    private Button selectAll;
    private TextView orderBtn;
    private FragmentCartPresenter presenter;
    private final static String TAG = "111";
    private List<Integer> mPtids = new ArrayList<>();
    private List<Integer> mNumbers = new ArrayList<>();
    private TextView carLoadingTv;

    public static fragmentCar newInstance() {
        Bundle args = new Bundle();
        fragmentCar fragment = new fragmentCar();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FragmentCartPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(getContext()).inflate(R.layout.fragment_car, null, false);
        carLoadingTv = (TextView) view.findViewById(R.id.carLoadingTv);
        initData();
        initListView();
        Log.d(TAG, "onCreateView: ");
        return view;
    }

    private void initOrderBtn() {
        orderBtn = (TextView) view.findViewById(R.id.orderBtn);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initSeletAllBtn() {
        isSelectAll();
        selectAll = (Button) view.findViewById(R.id.selectAllBtn);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelectAll()) {
                    selectAll.setTextColor(getResources().getColor(R.color.colorseparation));
                    for (int i = 0; i < adapter.getCount(); i++) {
                        adapter.getCheckeds().put(i, false);
                        adapter.getmNumbers().clear();
                        adapter.getmPtids().clear();
                    }
                } else {
                    selectAll.setTextColor(getResources().getColor(R.color.colorDeleteCarItem));
                    for (int i = 0; i < adapter.getCount(); i++) {
                        adapter.getCheckeds().put(i, true);
                        adapter.getmNumbers().add(mProductList.get(i).getNumber());
                        adapter.getmPtids().add(mProductList.get(i).getId());
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private boolean isSelectAll() {
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getCheckeds().get(i) == false) {
                return false;
            }
        }
        return true;
    }

    private void initData() {
        presenter.getData(1, "532a8a18b75079da0c48414014600600d64737f36e330997");
    }

    private void initListView() {
        swipeMenuListView = (SwipeMenuListView) view.findViewById(R.id.carListView);
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                deleteItem.setBackground(R.color.colorDeleteCarItem);
                deleteItem.setWidth(DensityUtil.dip2px(getContext(), 90));
                deleteItem.setIcon(R.drawable.white_delete_32);
                menu.addMenuItem(deleteItem);
            }
        };
        swipeMenuListView.setMenuCreator(creator);
        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Log.d(TAG, "onMenuItemClick: " + position);
                        adapter.deleteItem(position);
//                        Toast.makeText(getContext(), "!!!!", Toast.LENGTH_SHORT).show();
                        Snackbar.make(getView(), "删除成功", Snackbar.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void getCartList(List<ListCart.CartBean> cartBeanList) {
        for (int i = 0; i < cartBeanList.size(); i++) {
            mProductList.add(cartBeanList.get(i));
        }
        adapter = new CarListAdatper(getContext(), mProductList);
        initSeletAllBtn();
        swipeMenuListView.setAdapter(adapter);
        initOrderBtn();
        carLoadingTv.setVisibility(View.GONE);
    }
}
