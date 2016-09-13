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
import com.example.weshare.databean.NewGoodsBean;
import com.example.weshare.shoppingcartmodule.LoginActivity;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewGoodsActivity extends AppCompatActivity {

    @BindView(R.id.newgoods_back_iv)
    ImageView newgoodsBackIv;
    @BindView(R.id.newgoods_recyclerview)
    RecyclerView newgoodsRecyclerview;
    @BindView(R.id.newgoods_shoppingcart_iv)
    ImageView newgoodsShoppingcartIv;
    @BindView(R.id.newgoods_shoppingcart_num_iv)
    Button newgoodsShoppingcartNumIv;

    private Context mContext;
    private NewGoodsRecyclerViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private List<NewGoodsBean.NewSaleBean> new_sale_list = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goods);
        ButterKnife.bind(this);
        mContext =this;
        initview();
        loadDatas();
    }

    private void loadDatas() {
        HttpServiceUtil.init().getNewGoodsInfo("new",HttpServiceUtil.SID).enqueue(new Callback<NewGoodsBean>() {
            @Override
            public void onResponse(Call<NewGoodsBean> call, Response<NewGoodsBean> response) {
                new_sale_list = response.body().getNew_sale();
                if(new_sale_list!=null){
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewGoodsBean> call, Throwable t) {

            }
        });
    }

    private void initview() {
        gridLayoutManager = new GridLayoutManager(mContext,2);
        newgoodsRecyclerview.setLayoutManager(gridLayoutManager);
        adapter = new NewGoodsRecyclerViewAdapter();
        newgoodsRecyclerview.setAdapter(adapter);
        newgoodsBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        newgoodsShoppingcartIv.setOnClickListener(new View.OnClickListener() {
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
        newgoodsShoppingcartNumIv.setOnClickListener(new View.OnClickListener() {
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

    class NewGoodsRecyclerViewAdapter extends RecyclerView.Adapter<NewGoodsRecyclerViewAdapter.NewGoodsViewHolder>{


        @Override
        public NewGoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.daily_recommend_recyclerview_item, parent, false);
            NewGoodsViewHolder viewHolder = new NewGoodsViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(NewGoodsViewHolder holder, int position) {
            holder.daily_food_iv.setImageResource(R.drawable.default_image);
            if (new_sale_list != null) {
                holder.id = new_sale_list.get(position).getId();
                holder.stock=new_sale_list.get(position).getStock();
                if(holder.stock.equals("0")){
                    holder.daily_flag_tv.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(new_sale_list.get(position).getPic()).into(holder.daily_food_iv);
                holder.daily_name_tv.setText(new_sale_list.get(position).getPname());
                holder.daily_price_tv.setText("¥" + new_sale_list.get(position).getPrice());
                holder.daily_marketprice_tv.setText("¥" + new_sale_list.get(position).getMarket_price());
                holder.daily_address_tv.setText(new_sale_list.get(position).getMember_name());
            }
        }

        @Override
        public int getItemCount() {
            return new_sale_list==null?0:new_sale_list.size();
        }

        class NewGoodsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private String id;
            private String stock;
            private final TextView daily_flag_tv;
            private final ImageView daily_food_iv;
            private final ImageView daily_shoppingcart_iv;
            private final TextView daily_name_tv;
            private final TextView daily_price_tv;
            private final TextView daily_marketprice_tv;
            private final TextView daily_address_tv;
            public NewGoodsViewHolder(View view) {
                super(view);
                daily_food_iv = (ImageView) view.findViewById(R.id.daily_recommend_recyclerview_item_iv);
                daily_shoppingcart_iv = (ImageView) view.findViewById(R.id.daily_recommend_recyclerview_item_shoppingcart);
                daily_name_tv = (TextView) view.findViewById(R.id.daily_recommend_recyclerview_item_name);
                daily_price_tv = (TextView) view.findViewById(R.id.daily_recommend_recyclerview_item__price);
                daily_marketprice_tv = (TextView) view.findViewById(R.id.daily_recommend_recyclerview_item_marketprice);
                daily_address_tv = (TextView) view.findViewById(R.id.daily_recommend_recyclerview_item_address_tv);
                daily_flag_tv = (TextView) view.findViewById(R.id.daily_recommend_recyclerview_flag_tv);
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
