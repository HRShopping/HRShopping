package com.example.helloworld.huaruanshopping.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.orderList;
import com.example.helloworld.huaruanshopping.bean.orderList.OrderListBean.SorderSetBean;
import com.example.helloworld.huaruanshopping.presenter.ActivityFindAllOrderPresenter;

import java.util.List;

/**
 * Created by helloworld on 2017/3/18.
 */

public class FindAllOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<orderList.OrderListBean> orderList;
    private final static String TAG = "111";
    ActivityFindAllOrderPresenter presenter = new ActivityFindAllOrderPresenter();
    public cancelItemListen listener;

    public FindAllOrderAdapter(Context context, List<orderList.OrderListBean> orderList) {
        mContext = context;
        this.orderList = orderList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.find_all_order_item, parent, false);
        return new OrderItem(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OrderItem) {

            ((OrderItem) holder).status.setText(orderList.get(position).getStatus().getStatus());
            ((OrderItem) holder).total.setText("￥ " + orderList.get(position).getTotal());
            ((OrderItem) holder).NO.setText(orderList.get(position).getId());
            Log.d(TAG, "onBindViewHolder: id" + orderList.get(position).getStatus().getId() + "  " + position);
            if (orderList.get(position).getStatus().getId() == 2 ||orderList.get(position).getStatus().getId() == 4) {
                ((OrderItem) holder).deleteOrder.setVisibility(View.VISIBLE);
                ((OrderItem) holder).cancelOrder.setVisibility(View.GONE);
                ((OrderItem) holder).orderImmediately.setVisibility(View.GONE);
            } else if (orderList.get(position).getStatus().getId() == 1) {
                ((OrderItem) holder).deleteOrder.setVisibility(View.GONE);
                ((OrderItem) holder).cancelOrder.setVisibility(View.VISIBLE);
                ((OrderItem) holder).orderImmediately.setVisibility(View.VISIBLE);
            }
            ((OrderItem) holder).deleteOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(v, position);
                }
            });
            ((OrderItem) holder).cancelOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelItem(position);
                }
            });
            SorderSetBean Bean;
            for (int i = 0; i < orderList.get(position).getSorderSet().size(); i++) {
                Bean = orderList.get(position).getSorderSet().get(i);
//                Log.d(TAG, "onBindViewHolder:position " + position + " i " + i);
//                Log.d(TAG, "onBindViewHolder: " + Bean.getProtype().getName() + Bean.getProtype().getPic() + Bean.getPrice());
                //半段是否为空，空则添加子View 否则不需要添加
                if (((OrderItem) holder).contentLayout.getChildCount() < orderList.get(position).getSorderSet().size())
                    ((OrderItem) holder).contentLayout.addView(setItemView(((OrderItem) holder).contentLayout, Bean.getProtype().getPic(), Bean.getProtype().getProduct().getName(), Bean.getNumber(), Bean.getPrice(), Bean.getProtype().getName()));
            }
        }
    }

    public View setItemView(ViewGroup contentLayout, String imgUrl, String Name, int Num, double Total, String sort) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.order_item_layout, contentLayout, false);
        ImageView img = (ImageView) itemView.findViewById(R.id.orderItemImg);
        TextView itemName = (TextView) itemView.findViewById(R.id.orderItemName);
        TextView itemNum = (TextView) itemView.findViewById(R.id.orderItemNum);
        TextView itemTotal = (TextView) itemView.findViewById(R.id.orderItemTotal);
        TextView itemSort = (TextView) itemView.findViewById(R.id.orderItemSort);
//        Log.d(TAG, "setItemView: " + HttpMethods.BASE_URL + imgUrl);
        Glide.with(mContext).load(HttpMethods.BASE_URL + imgUrl).into(img);
        itemName.setText(Name);
        itemNum.setText("x " + Num);
        itemTotal.setText("￥ " + Total);
        itemSort.setText(sort);
        return itemView;
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class OrderItem extends RecyclerView.ViewHolder {
        TextView status;
        TextView total;
        LinearLayout contentLayout;
        TextView NO;
        TextView deleteOrder;
        TextView cancelOrder;
        TextView orderImmediately;
        public OrderItem(View itemView) {
            super(itemView);
            status = (TextView) itemView.findViewById(R.id.orderStatus);
            total = (TextView) itemView.findViewById(R.id.orderTotal);
            NO = (TextView) itemView.findViewById(R.id.orderNO);
            contentLayout = (LinearLayout) itemView.findViewById(R.id.orderContent);
            deleteOrder = (TextView) itemView.findViewById(R.id.deleteOrder);
            cancelOrder = (TextView) itemView.findViewById(R.id.cancelOrder);
            orderImmediately = (TextView) itemView.findViewById(R.id.orderImmediately);
        }
    }

    public void deleteItem(View view, int position) {
        presenter.deleteOrder(orderList.get(position).getId());
        orderList.remove(position);
        notifyDataSetChanged();
        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
    }

    public void cancelItem(int position) {
        presenter.cancelOrder(orderList.get(position).getId());
        listener.afterCancelListen();
        notifyDataSetChanged();
    }

    public void setOnCancelItemListener(cancelItemListen listener) {
        this.listener = listener;
    }

    public interface cancelItemListen {
        void afterCancelListen();
    }
}
