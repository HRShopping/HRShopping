package com.example.helloworld.huaruanshopping.adapter;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.api.CartAdapterPostData;
import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.ListCart;
import com.example.helloworld.huaruanshopping.bean.Product;
import com.example.helloworld.huaruanshopping.bean.Response;
import com.example.helloworld.huaruanshopping.presenter.FragmentCartPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

/**
 * Created by helloworld on 2017/1/22.
 */

public class CarListAdatper extends BaseAdapter {
    public Context context;
    private List<ListCart.CartBean> mList;
    private static HashMap<Integer, Boolean> checkeds = null;
    private final static String TAG = "111";
//    private ProgressDialog progressDialog;
    private List<Integer> mPtids = new ArrayList<>();
    private List<Integer> mNumbers = new ArrayList<>();

    public List<Integer> getmNumbers() {
        return mNumbers;
    }

    public void setmNumbers(List<Integer> mNumbers) {
        this.mNumbers = mNumbers;
    }

    public List<Integer> getmPtids() {
        return mPtids;
    }

    public void setmPtids(List<Integer> mPtids) {
        this.mPtids = mPtids;
    }

    public static HashMap<Integer, Boolean> getCheckeds() {
        return checkeds;
    }

    public int num[];

    public static void setCheckeds(HashMap<Integer, Boolean> checkeds) {
        CarListAdatper.checkeds = checkeds;
    }

    public CarListAdatper(Context context, List<ListCart.CartBean> mList) {
        this.context = context;
        this.mList = mList;
        checkeds = new HashMap<>();
        for (int i = 0; i < mList.size(); i++) {
            checkeds.put(i, false);
        }
        num = new int[mList.size()];
        initNum();
//        initProgress();
    }

    private void initNum() {
        for (int i = 0; i < mList.size(); i++) {
            num[i] = mList.get(i).getNumber();
        }
    }

//    private void initProgress() {
//        progressDialog = new ProgressDialog(context, R.style.MyProgressDialog);
//        progressDialog.setCancelable(false);
//    }
//
//    public void showProgressDialog() {
//
//        progressDialog.show();
//    }
//
//    public void hiddenProgressDialog() {
//        progressDialog.dismiss();
//    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.car_item_layout, null);
            holder = new ViewHolder();
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.carItemcheckbox);
            holder.img = (ImageView) convertView.findViewById(R.id.carItemImg);
            holder.name = (TextView) convertView.findViewById(R.id.carItemName);
            holder.price = (TextView) convertView.findViewById(R.id.carItemPrice);
            holder.sort = (TextView) convertView.findViewById(R.id.carItemSort);
            holder.itemAmount = (TextView) convertView.findViewById(R.id.carItemNum);
            holder.addBtn = (ImageButton) convertView.findViewById(R.id.addBtn);
            holder.reduceBtn = (ImageButton) convertView.findViewById(R.id.reduceBtn);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(HttpMethods.BASE_URL + mList.get(position).getProtype().getPic()).placeholder(R.drawable.placeholder_128).into(holder.img);
        holder.name.setText(mList.get(position).getProtype().getProduct().getName());
        holder.sort.setText(mList.get(position).getProtype().getName());
        holder.itemAmount.setText(mList.get(position).getNumber() + "");
        holder.price.setText(mList.get(position).getProtype().getProduct().getPrice() + "");
        holder.checkbox.setChecked(getCheckeds().get(position));
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setChecked(true);
                    getCheckeds().put(position, true);
                    mNumbers.add(mList.get(position).getNumber());
                    mPtids.add(mList.get(position).getId());
//                    Log.d("TAG", "onCheckedChanged: " + getCheckeds().get(position));
                } else {
                    buttonView.setChecked(false);
                    getCheckeds().put(position, false);
                    mNumbers.remove(mList.get(position));
                    mPtids.remove(mList.get(position));
//                    Log.d("TAG", "onCheckedChanged: " + getCheckeds().get(position));
                }
            }
        });
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemAmount.setText(++num[position] + "");
                holder.reduceBtn.setClickable(true);
                if (num[position] == 20) {
                    holder.addBtn.setClickable(false);
                }
//                progressDialog.show();
                CartAdapterPostData.updateCartData(num[position], mList.get(position).getId(), new CartAdapterPostData.updateListener() {
                    @Override
                    public void onFailed() {

                    }

                    @Override
                    public void onSuccess() {
//                        progressDialog.dismiss();
                    }
                });
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(holder.addBtn, "ScaleX", 1.0f, 1.4f, 1.0f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(holder.addBtn, "ScaleY", 1.0f, 1.4f, 1.0f);
                AnimatorSet set = new AnimatorSet();
                set.play(animator1).with(animator2);
                set.setDuration(500).start();
            }
        });
        holder.reduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemAmount.setText(--num[position] + "");
                holder.addBtn.setClickable(true);
                if (num[position] == 1) {
                    holder.reduceBtn.setClickable(false);
                }
//                progressDialog.show();
                CartAdapterPostData.updateCartData(num[position], mList.get(position).getId(), new CartAdapterPostData.updateListener() {
                    @Override
                    public void onFailed() {
                    }

                    @Override
                    public void onSuccess() {
//                        progressDialog.dismiss();
                    }
                });
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(holder.reduceBtn, "ScaleX", 1.0f, 1.4f, 1.0f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(holder.reduceBtn, "ScaleY", 1.0f, 1.4f, 1.0f);
                AnimatorSet set = new AnimatorSet();
                set.play(animator1).with(animator2);
                set.setDuration(400).start();
            }
        });
        return convertView;
    }


    class ViewHolder {
        ImageView img;
        ImageButton addBtn, reduceBtn;
        TextView name;
        TextView sort;
        TextView price;
        CheckBox checkbox;
        TextView itemAmount;
    }

    public void deleteItem(int position) {
        Log.d(TAG, "deleteItem:id " + mList.get(position).getId());
        CartAdapterPostData.deleteItem(mList.get(position).getId());
        mList.remove(position);
        notifyDataSetChanged();
    }
}
