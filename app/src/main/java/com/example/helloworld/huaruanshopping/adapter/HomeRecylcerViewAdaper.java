package com.example.helloworld.huaruanshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
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
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.acitiviy.ProductDescribeActivity;
import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.ListProduct;
import com.example.helloworld.huaruanshopping.bean.Product;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.bean.ProductListBean;
import com.example.helloworld.huaruanshopping.view.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.R.id.list;

/**
 * Created by helloworld on 2017/1/17.
 */

public class HomeRecylcerViewAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private final int BANNER = Integer.MAX_VALUE - 1;
    private final int NORMAL_ONE = Integer.MAX_VALUE - 2;
    private final int NORMAL_TWO = Integer.MAX_VALUE - 3;
    private final int FOOTERVIEW = Integer.MAX_VALUE - 4;
    private List<ProductListBean> productList = new ArrayList<>();
    private List<String> mpicList = new ArrayList<>();
    //    private Integer[] pics = {R.drawable.girl2, R.drawable.girl3, R.drawable.gril4};
    private String TAG = "111";
    private String loadStatus = "加载中...";

    public String getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(String loadStatus) {
        this.loadStatus = loadStatus;
        notifyDataSetChanged();
    }

    public HomeRecylcerViewAdaper(Context context, List<ProductListBean> list) {
        this.mContext = context;
        this.productList = list;
//        initPic();
//        loadBannerPics();
//        Log.d(TAG, "HomeRecylcerViewAdaper: " + productList.size());

    }

    private void initPic() {
//        for (int i = 0; i < pics.length; i++) {
//            mpicList.add(pics[i]);
//        }
    }

    private void loadBannerPics() {
        Observable<ListProduct> observable = HttpMethods.getInstance().getHomeProductApi().getHomeListProduct(1, 1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListProduct>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListProduct listProduct) {
                        Log.d(TAG, "onNext: " + listProduct.getProductList().get(0).getProtypeSet().get(0).getPic());
                        mpicList = transformPic(listProduct);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<String> transformPic(ListProduct listProduct) {
        List<String> listPics = new ArrayList<>();
        List<ProductListBean> productList = listProduct.getProductList();
        for (int i = 0; i < productList.size(); i++) {
            listPics.add(HttpMethods.BASE_URL + productList.get(i).getProtypeSet().get(0).getPic());
        }
        return listPics;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER;
        } else if (position == getItemCount() - 1) {
            return FOOTERVIEW;
        } else if (position > 0 && position % 2 == 0) {
            return NORMAL_TWO;
        } else {
            return NORMAL_ONE;
        }
    }

    public void addMoreData(List<ProductListBean> moreList) {
        productList.addAll(moreList);
        notifyItemInserted(getItemCount() - 2);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View banner_view = LayoutInflater.from(mContext).inflate(R.layout.banner_layout, parent, false);
        View normal_one_view = LayoutInflater.from(mContext).inflate(R.layout.normal_one_view, parent, false);
        View normal_two_view = LayoutInflater.from(mContext).inflate(R.layout.normal_two_view, parent, false);
        View footer_view = LayoutInflater.from(mContext).inflate(R.layout.home_footer_view, parent, false);
        switch (viewType) {
            case BANNER:
                return new BannerViewHolder(banner_view);
            case NORMAL_ONE:
                return new NormalOne(normal_one_view);
            case NORMAL_TWO:
                return new NormalTwo(normal_two_view);
            case FOOTERVIEW:
                return new footerViewHolder(footer_view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NormalOne) {
            Log.d("posision", "onBindViewHolder: " + position);

            Glide.with(mContext).load(HttpMethods.BASE_URL + productList.get(2 * position - 2).getProtypeSet().get(0).getPic()).fitCenter().into(((NormalOne) holder).picUrl1);
            ((NormalOne) holder).goodName1.setText(productList.get(2 * position - 2).getName());
            ((NormalOne) holder).goodPrice1.setText("￥ " + productList.get(2 * position - 2).getPrice() + "");
            ((NormalOne) holder).goodSale1.setText(productList.get(2 * position - 2).getSales());
            ((NormalOne) holder).goodLayout1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = productList.get(2 * position - 2).getId();
                    Intent intent = new Intent(mContext.getApplicationContext(), ProductDescribeActivity.class);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);
                }
            });

            Glide.with(mContext).load(HttpMethods.BASE_URL + productList.get(2 * position - 1).getProtypeSet().get(0).getPic()).fitCenter().into(((NormalOne) holder).picUrl2);
            ((NormalOne) holder).goodName2.setText(productList.get(2 * position - 1).getName());
            ((NormalOne) holder).goodPrice2.setText("￥ " + productList.get(2 * position - 1).getPrice() + "");
            ((NormalOne) holder).goodSale2.setText(productList.get(2 * position - 1).getSales());
            ((NormalOne) holder).goodLayout2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = productList.get(2 * position - 1).getId();
                    Intent intent = new Intent(mContext.getApplicationContext(), ProductDescribeActivity.class);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);
                }
            });

            Glide.with(mContext).load(HttpMethods.BASE_URL + productList.get(2 * position).getProtypeSet().get(0).getPic()).fitCenter().into(((NormalOne) holder).picUrl3);
            ((NormalOne) holder).goodName3.setText(productList.get(2 * position).getName());
            ((NormalOne) holder).goodPrice3.setText("￥ " + productList.get(2 * position).getPrice() + "");
            ((NormalOne) holder).goodSale3.setText(productList.get(2 * position).getSales());
            ((NormalOne) holder).goodLayout3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = productList.get(2 * position).getId();
                    Intent intent = new Intent(mContext.getApplicationContext(), ProductDescribeActivity.class);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);
                }
            });

        } else if (holder instanceof NormalTwo) {
            Glide.with(mContext).load(HttpMethods.BASE_URL + productList.get(position * 2 - 1).getProtypeSet().get(0).getPic()).fitCenter().into(((NormalTwo) holder).picUrl4);
            ((NormalTwo) holder).goodName4.setText(productList.get(2 * position - 1).getName());
            ((NormalTwo) holder).goodPrice4.setText("￥ " + productList.get(2 * position - 1).getPrice() + "");
            ((NormalTwo) holder).goodSale4.setText(productList.get(2 * position - 1).getSales());

        } else if (holder instanceof footerViewHolder) {
            ((footerViewHolder) holder).footerTv.setText(getLoadStatus());

        } else if (holder instanceof BannerViewHolder) {
            loadBannerPics();
            ((BannerViewHolder) holder).banner.setImageLoader(new GlideImageLoader());
            ((BannerViewHolder) holder).banner.setImages(mpicList);
            ((BannerViewHolder) holder).banner.setDelayTime(3500);
            ((BannerViewHolder) holder).banner.start();
        }
    }

    @Override
    public int getItemCount() {
        return (productList.size() / 2) + 2;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        Banner banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);

        }
    }

    class NormalOne extends RecyclerView.ViewHolder {
        private ImageView picUrl1, picUrl2, picUrl3;
        private TextView goodName1, goodName2, goodName3;
        private TextView goodPrice1, goodPrice2, goodPrice3;
        private TextView goodSale1, goodSale2, goodSale3;
        private CardView goodLayout1, goodLayout2, goodLayout3;

        public NormalOne(View itemView) {
            super(itemView);
            picUrl1 = (ImageView) itemView.findViewById(R.id.GoodPicUrl1);
            picUrl2 = (ImageView) itemView.findViewById(R.id.GoodPicUrl2);
            picUrl3 = (ImageView) itemView.findViewById(R.id.GoodPicUrl3);
            goodName1 = (TextView) itemView.findViewById(R.id.GoodName1);
            goodName2 = (TextView) itemView.findViewById(R.id.GoodName2);
            goodName3 = (TextView) itemView.findViewById(R.id.GoodName3);
            goodPrice1 = (TextView) itemView.findViewById(R.id.GoodPrice1);
            goodPrice2 = (TextView) itemView.findViewById(R.id.GoodPrice2);
            goodPrice3 = (TextView) itemView.findViewById(R.id.GoodPrice3);
            goodSale1 = (TextView) itemView.findViewById(R.id.GoodSale1);
            goodSale2 = (TextView) itemView.findViewById(R.id.GoodSale2);
            goodSale3 = (TextView) itemView.findViewById(R.id.GoodSale3);
            goodLayout1 = (CardView) itemView.findViewById(R.id.GoodLayout1);
            goodLayout2 = (CardView) itemView.findViewById(R.id.GoodLayout2);
            goodLayout3 = (CardView) itemView.findViewById(R.id.GoodLayout3);
        }
    }

    class NormalTwo extends RecyclerView.ViewHolder {
        private ImageView picUrl4;
        private TextView goodName4;
        private TextView goodPrice4;
        private TextView goodSale4;

        public NormalTwo(View itemView) {
            super(itemView);
            picUrl4 = (ImageView) itemView.findViewById(R.id.GoodPicUrl4);
            goodName4 = (TextView) itemView.findViewById(R.id.GoodName4);
            goodPrice4 = (TextView) itemView.findViewById(R.id.GoodPrice4);
            goodSale4 = (TextView) itemView.findViewById(R.id.GoodSale4);
        }
    }

    class footerViewHolder extends RecyclerView.ViewHolder {
        private TextView footerTv;

        public footerViewHolder(View itemView) {
            super(itemView);
            footerTv = (TextView) itemView.findViewById(R.id.footerTv);
        }
    }
}
