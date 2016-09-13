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
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.weshare.R;
import com.example.weshare.databean.HomeRecyclerViewLogoDetailBean;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalSale extends AppCompatActivity {
    @BindView(R.id.local_sale_recyclerview)
    RecyclerView localsale_recyclerview;
    @BindView(R.id.local_sale_back_iv)
    ImageView localSaleBackIv;
    private Context mContext;
    private LocalSaleAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private List<HomeRecyclerViewLogoDetailBean.ListBean> detail_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_sale);
        ButterKnife.bind(this);
        mContext = this;
        initview();
        loadDatas();

    }

    private void loadDatas() {
        HttpServiceUtil.init().getLogoDetailInfo("local_sale", HttpServiceUtil.SID).enqueue(new Callback<HomeRecyclerViewLogoDetailBean>() {
            @Override
            public void onResponse(Call<HomeRecyclerViewLogoDetailBean> call, Response<HomeRecyclerViewLogoDetailBean> response) {
                detail_list = response.body().getList();
                if (detail_list != null) {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<HomeRecyclerViewLogoDetailBean> call, Throwable t) {

            }
        });
    }

    private void initview() {
        gridLayoutManager = new GridLayoutManager(mContext, 2);
        localsale_recyclerview.setLayoutManager(gridLayoutManager);
        adapter = new LocalSaleAdapter();
        localsale_recyclerview.setAdapter(adapter);
        localSaleBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    class LocalSaleAdapter extends RecyclerView.Adapter<LocalSaleAdapter.LocalViewHolder> {


        @Override
        public LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.local_sale_recyclerview_item, parent, false);
            LocalViewHolder viewHolder = new LocalViewHolder(view);
            return viewHolder;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(LocalViewHolder holder, int position) {
            holder.item_position = position;
            holder.local_product_iv.setImageResource(R.drawable.default_image);
            if (detail_list != null) {
                Glide.with(mContext).load(detail_list.get(position).getLogo2_7_8()).into(holder.local_product_iv);
            }
        }

        @Override
        public int getItemCount() {
            return detail_list == null ? 0 : detail_list.size();
        }

        class LocalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private int item_position;
            private final ImageView local_product_iv;

            public LocalViewHolder(View view) {
                super(view);
                local_product_iv = (ImageView) view.findViewById(R.id.local_sale_recyclerview_item_iv);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LocalSaleDetail.class);
                intent.putExtra("position", item_position);
                startActivity(intent);
            }
        }
    }
}
