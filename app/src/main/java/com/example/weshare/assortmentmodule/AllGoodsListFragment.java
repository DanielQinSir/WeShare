package com.example.weshare.assortmentmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weshare.R;
import com.example.weshare.databean.AssortExpandListAllGoodsBean;
import com.example.weshare.homepagemodule.HomeListDetailTwo;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/13.
 */
public class AllGoodsListFragment extends Fragment{

    private Context mContext;
    private RecyclerView list_recyclerview;
    private ListRecyclerViewAdapter adapter;
    private String catid;
    private Bundle bd;
    private List<AssortExpandListAllGoodsBean.GoodslistBean> goods_list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private TextView assort_emptyview;


    public static AllGoodsListFragment newInstance(String catid) {

        Bundle args = new Bundle();
        args.putString("catid",catid);
        AllGoodsListFragment fragment = new AllGoodsListFragment();
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
        View view = inflater.inflate(R.layout.all_goods_list,container,false);
        list_recyclerview = (RecyclerView) view.findViewById(R.id.assort_allgoods_list);
        assort_emptyview = (TextView) view.findViewById(R.id.assort_allgoods_emptyview);
        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_recyclerview.setLayoutManager(linearLayoutManager);
        adapter = new ListRecyclerViewAdapter();
        list_recyclerview.setAdapter(adapter);
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

    public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.ListViewHolder>{


        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.all_goods_list_item,parent,false);
            ListViewHolder viewholder = new ListViewHolder(view);
            return viewholder;
        }

        @Override
        public void onBindViewHolder(ListViewHolder holder, int position) {
            if(goods_list!=null){
                holder.id = goods_list.get(position).getId();
                Glide.with(mContext).load(goods_list.get(position).getPic()).into(holder.all_goods_iv);
                holder.all_goods_name_tv.setText(goods_list.get(position).getPname());
                holder.all_goods_price_tv.setText("¥"+goods_list.get(position).getPrice());
                holder.all_goods_marketprice_tv.setText("¥"+goods_list.get(position).getMarket_price());
                holder.all_goods_address_tv.setText(goods_list.get(position).getMember_name());
            }

        }

        @Override
        public int getItemCount() {
            return goods_list==null?0:goods_list.size();
        }

        class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private String id;
            private final ImageView all_goods_iv;
            private final TextView all_goods_name_tv;
            private final TextView all_goods_price_tv;
            private final TextView all_goods_marketprice_tv;
            private final TextView all_goods_address_tv;

            public ListViewHolder(View view) {
                super(view);
                all_goods_iv = (ImageView) view.findViewById(R.id.assort_allgoods_recyclerview_item_iv);
                all_goods_name_tv = (TextView) view.findViewById(R.id.assort_allgoods_recyclerview_item_name_tv);
                all_goods_price_tv = (TextView) view.findViewById(R.id.assort_allgoods_recyclerview_item_price_tv);
                all_goods_marketprice_tv = (TextView) view.findViewById(R.id.assort_allgoods_recyclerview_item_marketprice_tv);
                all_goods_address_tv = (TextView) view.findViewById(R.id.assort_allgoods_recyclerview_address_tv);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Intent i =new Intent(mContext, HomeListDetailTwo.class);
                i.putExtra("product_id",id);
                mContext.startActivity(i);
            }
        }
    }



}
