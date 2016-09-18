package com.example.weshare.homepagemodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.weshare.databean.HomeListDetailBean;
import com.example.weshare.databean.NutritionRecommendBean;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NutritionRecommendActivity extends AppCompatActivity {


    @BindView(R.id.home_list_detail_back_iv)
    ImageView homeListDetailBackIv;
    @BindView(R.id.home_list_detail_title_tv)
    TextView homeListDetailTitleTv;
    @BindView(R.id.home_list_detail_listview)
    ListView homeListDetailListview;

    private Context mContext;
    public ImageView home_list_detail_head_iv;
    private NutritionListViewAdapter adapter;
    private NutritionRecommendBean detail_bean;
    private List<NutritionRecommendBean.ProductBean> product_list = new ArrayList<>();
    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(detail_bean!=null){
                Glide.with(mContext).load(detail_bean.getDesc()).into(home_list_detail_head_iv);
                //homeListDetailTitleTv.setText(detail_bean.getName());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list_detail);
        ButterKnife.bind(this);
        mContext= this;
        getDatas();
        initHeadView();
        initView();
    }

    private void initView() {
        adapter = new NutritionListViewAdapter();
        homeListDetailListview.setAdapter(adapter);
        homeListDetailTitleTv.setText("营养推荐");
        homeListDetailBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_list_detail_headview,null,false);
        home_list_detail_head_iv = (ImageView) view.findViewById(R.id.home_list_detail_head_iv);
        /*if(detail_bean!=null){
            Glide.with(mContext).load(detail_bean.getDesc()).into(home_list_detail_head_iv);
            homeListDetailTitleTv.setText(detail_bean.getName());
        }*/
        homeListDetailListview.addHeaderView(view);
    }

    private void getDatas() {
        HttpServiceUtil.init().getNutritionRecommendInfo(2,HttpServiceUtil.SID).enqueue(new Callback<NutritionRecommendBean>() {
            @Override
            public void onResponse(Call<NutritionRecommendBean> call, Response<NutritionRecommendBean> response) {
                detail_bean=response.body();
                product_list=detail_bean.getProduct();
                if(product_list!=null){
                    mhandler.sendEmptyMessage(1);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<NutritionRecommendBean> call, Throwable t) {

            }
        });
    }

    class NutritionListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return product_list==null?0:product_list.size();
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
        public View getView(int position, View converview, ViewGroup parent) {
            View view = converview;
            NutritionViewHolder viewholder = null;
            if(converview==null){
                view = LayoutInflater.from(mContext).inflate(R.layout.home_list_detail_listview_item,parent,false);
                viewholder = new NutritionViewHolder(view);
            }else{
                viewholder= (NutritionViewHolder) view.getTag();
            }
            viewholder.item_iv.setImageResource(R.drawable.default_image);
            if(product_list!=null){
                viewholder.product_id=product_list.get(position).getProduct_id();
                viewholder.title_tv.setText(product_list.get(position).getTitle());
                viewholder.content_tv.setText("\t\t"+product_list.get(position).getReason());
                Glide.with(mContext).load(product_list.get(position).getPic()).into(viewholder.item_iv);
            }

            return view;
        }

        class NutritionViewHolder implements View.OnClickListener{

            public String product_id;
            public final TextView title_tv;
            public final ImageView item_iv;
            public final TextView content_tv;

            public NutritionViewHolder(View view){
                view.setTag(this);
                title_tv = (TextView) view.findViewById(R.id.home_list_detail_listview_item_title_tv);
                item_iv = (ImageView) view.findViewById(R.id.home_list_detail_listview_item_iv);
                content_tv = (TextView) view.findViewById(R.id.home_list_detail_listview_item_content_tv);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,HomeListDetailTwo.class);
                i.putExtra("product_id",product_id);
                startActivity(i);
            }
        }
    }
}
