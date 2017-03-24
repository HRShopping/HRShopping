package com.example.helloworld.huaruanshopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.bean.Product;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by helloworld on 2017/2/20.
 */

public class ConfirmationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NOORDER = Integer.MAX_VALUE - 1;
    private static final int SHOPPINGTO = Integer.MAX_VALUE - 2;
    private static final int ORDER = Integer.MAX_VALUE - 3;
    private static final int COUNT = Integer.MAX_VALUE - 4;
    private List<Product> mList = new ArrayList<>();
    private Context mContext;

    public ConfirmationAdapter(List<Product> List, Context Context) {
        this.mList = List;
        this.mContext = Context;
        Log.d("11", "ConfirmationAdapter: " + mList.size());
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewNoOrder = LayoutInflater.from(mContext).inflate(R.layout.confirm_no_order, parent, false);
        View viewShoppingTo = LayoutInflater.from(mContext).inflate(R.layout.confirm_shopping_to, parent, false);
        View viewOrderItem = LayoutInflater.from(mContext).inflate(R.layout.confirm_order_item, parent, false);
        View viewCount = LayoutInflater.from(mContext).inflate(R.layout.confirm_count, parent, false);
        Log.d("adapter", "onCreateViewHolder: " + getItemCount());
        switch (viewType) {
            case NOORDER:
                return new NoOrderViewHolder(viewNoOrder);
            case SHOPPINGTO:
                return new ShoppingToViewHolder(viewShoppingTo);
            case ORDER:
                return new OrderItemViewHolder(viewOrderItem);
            case COUNT:
                return new CountViewHolder(viewCount);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ShoppingToViewHolder) {
            ((ShoppingToViewHolder) holder).name.setText("张国荣");
            ((ShoppingToViewHolder) holder).address.setText("广州大学华软软件学软G403");
            ((ShoppingToViewHolder) holder).tel.setText("13995384759");
        } else if (holder instanceof OrderItemViewHolder) {
            ((OrderItemViewHolder) holder).orderItemName.setText("1asd");
            ((OrderItemViewHolder) holder).orderItemSort.setText("分类1");
            ((OrderItemViewHolder) holder).orderItemTotal.setText("333");
            ((OrderItemViewHolder) holder).orderItemCount.setText("5");
        } else if (holder instanceof CountViewHolder) {
            ((CountViewHolder) holder).confirmTotal.setText("2300");
            ((CountViewHolder) holder).confirmPayment.setText("在线支付");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() == 0 ? 1 : mList.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() > 1) {
            if (position == 0) {
                return SHOPPINGTO;
            } else if (position == getItemCount() - 1) {
                return COUNT;
            } else {
                return ORDER;
            }
        } else {
            return NOORDER;
        }
    }


    class NoOrderViewHolder extends RecyclerView.ViewHolder {

        public NoOrderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ShoppingToViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView address;
        private TextView tel;

        public ShoppingToViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.ConfirmName);
            address = (TextView) itemView.findViewById(R.id.ConfirmAddress);
            tel = (TextView) itemView.findViewById(R.id.ConfirmTel);
        }
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder {
        private TextView orderItemName;
        private TextView orderItemSort;
        private TextView orderItemCount;
        private TextView orderItemTotal;
        private RoundedImageView orderItemPic;

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            orderItemPic = (RoundedImageView) itemView.findViewById(R.id.carItemImg);
            orderItemName = (TextView) itemView.findViewById(R.id.ConfirmItemName);
            orderItemSort = (TextView) itemView.findViewById(R.id.ConfirmItemSort);
            orderItemCount = (TextView) itemView.findViewById(R.id.ConfirmItemCount);
            orderItemTotal = (TextView) itemView.findViewById(R.id.ConfirmItemPrice);
        }
    }

    class CountViewHolder extends RecyclerView.ViewHolder {
        TextView confirmPayment;
        TextView confirmTotal;

        public CountViewHolder(View itemView) {
            super(itemView);
            confirmPayment = (TextView) itemView.findViewById(R.id.ConfirmOrderPayment);
            confirmTotal = (TextView) itemView.findViewById(R.id.ConfirmOrderTotal);
        }
    }

}
