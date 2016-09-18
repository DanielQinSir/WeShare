package com.example.weshare.homepagemodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weshare.MainActivity;
import com.example.weshare.MyApplication;
import com.example.weshare.R;
import com.example.weshare.databean.HomeRecyclerViewSecondADBean;
import com.example.weshare.shoppingcartmodule.LoginActivity;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyRecommendActivity extends AppCompatActivity {

    @BindView(R.id.daily_recommend_back_iv)
    ImageView dailyRecommendBackIv;
    @BindView(R.id.daily_recommend_recyclerview)
    RecyclerView dailyRecommendRecyclerview;
    @BindView(R.id.daily_recommend_shoppingcart_iv)
    ImageView dailyRecommendShoppingcartIv;
    @BindView(R.id.daily_recommend_shoppingcart_num_iv)
    Button dailyRecommendShoppingcartNumIv;

    private Context mContext;
    private GridLayoutManager gridLayoutManager;
    private List<HomeRecyclerViewSecondADBean.LocalSaleBean> daily_list = new ArrayList<>();
    private DailyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_recommend);
        ButterKnife.bind(this);
        mContext = this;
        initView();
        loadDatas();
    }

    private void loadDatas() {
        HttpServiceUtil.init().getDailyRecommendInfo("local", HttpServiceUtil.SID).enqueue(new Callback<HomeRecyclerViewSecondADBean>() {
            @Override
            public void onResponse(Call<HomeRecyclerViewSecondADBean> call, Response<HomeRecyclerViewSecondADBean> response) {
                daily_list = response.body().getLocal_sale();
                if(daily_list!=null){
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<HomeRecyclerViewSecondADBean> call, Throwable t) {

            }
        });
    }

    private void initView() {
        gridLayoutManager = new GridLayoutManager(mContext, 2);
        dailyRecommendRecyclerview.setLayoutManager(gridLayoutManager);

        adapter = new DailyRecyclerViewAdapter();
        dailyRecommendRecyclerview.setAdapter(adapter);

        dailyRecommendBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dailyRecommendShoppingcartIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.sUser == null) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                } else {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("flag", 1);
                    startActivity(intent);
                    finish();
                }
            }
        });
        dailyRecommendShoppingcartNumIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.sUser == null) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                } else {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("flag", 1);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    class DailyRecyclerViewAdapter extends RecyclerView.Adapter<DailyRecyclerViewAdapter.DailyViewHolder> {


        @Override
        public DailyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.daily_recommend_recyclerview_item, parent, false);
            DailyViewHolder viewHolder = new DailyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(DailyViewHolder holder, int position) {

            holder.daily_food_iv.setImageResource(R.drawable.default_image);
            if (daily_list != null) {
                holder.id = daily_list.get(position).getId();
                Glide.with(mContext).load(daily_list.get(position).getPic()).into(holder.daily_food_iv);
                holder.daily_name_tv.setText(daily_list.get(position).getPname());
                holder.daily_price_tv.setText("¥" + daily_list.get(position).getPrice());
                holder.daily_marketprice_tv.setText("¥" + daily_list.get(position).getMarket_price());
                holder.daily_address_tv.setText(daily_list.get(position).getMember_name());
            }
        }

        @Override
        public int getItemCount() {
            return daily_list == null ? 0 : daily_list.size();
        }

        class DailyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private String id;
            private final ImageView daily_food_iv;
            private final ImageView daily_shoppingcart_iv;
            private final TextView daily_name_tv;
            private final TextView daily_price_tv;
            private final TextView daily_marketprice_tv;
            private final TextView daily_address_tv;

            public DailyViewHolder(View view) {
                super(view);
                daily_food_iv = (ImageView) view.findViewById(R.id.daily_recommend_recyclerview_item_iv);
                daily_shoppingcart_iv = (ImageView) view.findViewById(R.id.daily_recommend_recyclerview_item_shoppingcart);
                daily_name_tv = (TextView) view.findViewById(R.id.daily_recommend_recyclerview_item_name);
                daily_price_tv = (TextView) view.findViewById(R.id.daily_recommend_recyclerview_item__price);
                daily_marketprice_tv = (TextView) view.findViewById(R.id.daily_recommend_recyclerview_item_marketprice);
                daily_address_tv = (TextView) view.findViewById(R.id.daily_recommend_recyclerview_item_address_tv);
                view.setOnClickListener(this);
                daily_shoppingcart_iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (MyApplication.sUser == null) {
                            startActivity(new Intent(mContext, LoginActivity.class));
                        } else {
                            Toast.makeText(mContext, "商品已添加到购物车~", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, HomeListDetailTwo.class);
                i.putExtra("product_id", id);
                startActivity(i);
            }
        }
    }
}
