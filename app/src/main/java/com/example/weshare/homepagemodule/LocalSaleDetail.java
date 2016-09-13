package com.example.weshare.homepagemodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

public class LocalSaleDetail extends AppCompatActivity {

    @BindView(R.id.local_sale_detail_back_iv)
    ImageView localSaleDetailBackIv;
    @BindView(R.id.local_sale_detail_title_tv)
    TextView localSaleDetailTitleTv;
    @BindView(R.id.local_sale_detail_introduce_tv)
    TextView localSaleDetailIntroduceTv;
    @BindView(R.id.local_sale_detail_listview)
    ListView localSaleDetailListview;
    @BindView(R.id.local_sale_detail_introduce_iv)
    ImageView localSaleDetailIntroduceIv;
    private Intent intent;
    private int position;
    private Context mContext;
    private LocalListViewAdapter adapter;
    private List<HomeRecyclerViewLogoDetailBean.ListBean> local_sale_list = new ArrayList<>();
    private List<HomeRecyclerViewLogoDetailBean.ListBean.ProductBean> productBeen_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_sale_detail);
        ButterKnife.bind(this);
        mContext = this;
        intent = getIntent();
        position = intent.getIntExtra("position", 520);
        initview();
        loadDatas();

    }

    private void loadDatas() {
        HttpServiceUtil.init().getLogoDetailInfo("local_sale", HttpServiceUtil.SID).enqueue(new Callback<HomeRecyclerViewLogoDetailBean>() {
            @Override
            public void onResponse(Call<HomeRecyclerViewLogoDetailBean> call, Response<HomeRecyclerViewLogoDetailBean> response) {
                local_sale_list = response.body().getList();
                productBeen_list = local_sale_list.get(position).getProduct();
                localSaleDetailIntroduceIv.setImageResource(R.drawable.default_image);
                if (local_sale_list != null) {
                    localSaleDetailTitleTv.setText(local_sale_list.get(position).getName());
                    localSaleDetailIntroduceTv.setText(local_sale_list.get(position).getSummary());
                    Glide.with(mContext).load(local_sale_list.get(position).getLogo()).into(localSaleDetailIntroduceIv);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HomeRecyclerViewLogoDetailBean> call, Throwable t) {

            }
        });
    }

    private void initview() {
        adapter = new LocalListViewAdapter();
        localSaleDetailListview.setAdapter(adapter);
        localSaleDetailBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    class LocalListViewAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return productBeen_list == null ? 0 : productBeen_list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup parent) {
            View view = convertview;
            LocalSaleDetailViewHolder viewHolder = null;
            if (convertview == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.local_sale_detail_listview_item, parent, false);
                viewHolder = new LocalSaleDetailViewHolder(view);
            } else {
                viewHolder = (LocalSaleDetailViewHolder) view.getTag();
            }
            viewHolder.local_item_iv.setImageResource(R.drawable.default_image);
            if(productBeen_list!=null){
                viewHolder.id =productBeen_list.get(position).getId();
                viewHolder.local_item_name_tv.setText(productBeen_list.get(position).getPname());
                viewHolder.local_item_price_tv.setText("¥"+productBeen_list.get(position).getPrice());
                viewHolder.local_item_marketprice_tv.setText("¥"+productBeen_list.get(position).getMarket_price());
                Glide.with(mContext).load(productBeen_list.get(position).getPic()).into(viewHolder.local_item_iv);
            }

            return view;
        }

        class LocalSaleDetailViewHolder implements View.OnClickListener{

            private String id;
            private final ImageView local_item_iv;
            private final TextView local_item_name_tv;
            private final TextView local_item_price_tv;
            private final TextView local_item_marketprice_tv;

            public LocalSaleDetailViewHolder(View view) {
                view.setTag(this);
                local_item_iv = (ImageView) view.findViewById(R.id.local_sale_detail_listview_item_iv);
                local_item_name_tv = (TextView) view.findViewById(R.id.local_sale_detail_listview_item_name_tv);
                local_item_price_tv = (TextView) view.findViewById(R.id.local_sale_detail_listview_item_price_tv);
                local_item_marketprice_tv = (TextView) view.findViewById(R.id.local_sale_detail_listview_item_marketprice_tv);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Intent i =new Intent(mContext,HomeListDetailTwo.class);
                i.putExtra("product_id",id);
                startActivity(i);
            }
        }
    }
}
