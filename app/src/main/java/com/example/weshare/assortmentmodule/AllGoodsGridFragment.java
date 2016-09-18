package com.example.weshare.assortmentmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weshare.MyApplication;
import com.example.weshare.R;
import com.example.weshare.databean.AssortExpandListAllGoodsBean;
import com.example.weshare.homepagemodule.HomeListDetailTwo;
import com.example.weshare.shoppingcartmodule.LoginActivity;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/13.
 */
public class AllGoodsGridFragment extends Fragment{

    private Context mContext;
    private String catid;
    private Bundle bd;
    private RecyclerView grid_recyclerview;
    private GridRecyclerViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private List<AssortExpandListAllGoodsBean.GoodslistBean> goods_list = new ArrayList<>();
    private TextView assort_emptyview;


    public static AllGoodsGridFragment newInstance(String catid) {

        Bundle args = new Bundle();
        args.putString("catid",catid);
        AllGoodsGridFragment fragment = new AllGoodsGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        bd = getArguments();
        catid = bd.getString("catid");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.all_goods_list,container,false);
        grid_recyclerview = (RecyclerView) view.findViewById(R.id.assort_allgoods_list);
        assort_emptyview = (TextView) view.findViewById(R.id.assort_allgoods_emptyview);
        gridLayoutManager = new GridLayoutManager(mContext,2);
        grid_recyclerview.setLayoutManager(gridLayoutManager);
        adapter = new GridRecyclerViewAdapter();
        grid_recyclerview.setAdapter(adapter);
        loadDatas();
        return view;
    }

    private void loadDatas() {
        HttpServiceUtil.init().getAssortAllGoodsBean(catid,HttpServiceUtil.SID).enqueue(new Callback<AssortExpandListAllGoodsBean>() {
            @Override
            public void onResponse(Call<AssortExpandListAllGoodsBean> call, Response<AssortExpandListAllGoodsBean> response) {
                goods_list = response.body().getGoodslist();
                if(goods_list!=null){
                    adapter.notifyDataSetChanged();
                }
                if(goods_list==null){
                    assort_emptyview.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<AssortExpandListAllGoodsBean> call, Throwable t) {

            }
        });
    }


    class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.GridViewHolder>{


        @Override
        public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.daily_recommend_recyclerview_item,parent,false);
            GridViewHolder viewHolder = new GridViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(GridViewHolder holder, int position) {
            holder.daily_food_iv.setImageResource(R.drawable.default_image);
            if (goods_list != null) {
                holder.id = goods_list.get(position).getId();
                Glide.with(mContext).load(goods_list.get(position).getPic()).into(holder.daily_food_iv);
                holder.daily_name_tv.setText(goods_list.get(position).getPname());
                holder.daily_price_tv.setText("¥" + goods_list.get(position).getPrice());
                holder.daily_marketprice_tv.setText("¥" + goods_list.get(position).getMarket_price());
                holder.daily_address_tv.setText(goods_list.get(position).getMember_name());
            }
        }

        @Override
        public int getItemCount() {
            return goods_list==null?0:goods_list.size();
        }

        class GridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private String id;
            private final ImageView daily_food_iv;
            private final ImageView daily_shoppingcart_iv;
            private final TextView daily_name_tv;
            private final TextView daily_price_tv;
            private final TextView daily_marketprice_tv;
            private final TextView daily_address_tv;
            public GridViewHolder(View view) {
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
