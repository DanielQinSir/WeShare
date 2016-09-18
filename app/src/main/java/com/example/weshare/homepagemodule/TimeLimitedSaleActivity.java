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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weshare.R;
import com.example.weshare.databean.HomeRecyclerViewFirstADBean;
import com.example.weshare.utils.HttpServiceUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeLimitedSaleActivity extends AppCompatActivity {

    @BindView(R.id.timelimited_back_iv)
    ImageView timelimitedBackIv;
    @BindView(R.id.timelimited_listview)
    ListView timelimitedListview;

    private Context mContext;
    private List<HomeRecyclerViewFirstADBean.ProductBean> product_list = new ArrayList<>();
    private TimeLimitedAdapter adapter;
    public String rtime;
    private  TimeLimitedAdapter.TimeLimitedViewHolder viewHolder = null;
    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewHolder.time_resttime_tv.setText("还剩"+rtime);
            mhandler.sendEmptyMessageDelayed(1,1000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_limited_sale);
        mContext = this;
        ButterKnife.bind(this);
        initView();
        loadDatas();
    }

    private void loadDatas() {
        HttpServiceUtil.init().getOnTimeInfo("iscurrent","promote27",HttpServiceUtil.SID).enqueue(new Callback<HomeRecyclerViewFirstADBean>() {
            @Override
            public void onResponse(Call<HomeRecyclerViewFirstADBean> call, Response<HomeRecyclerViewFirstADBean> response) {
                product_list = response.body().getProduct();
                if(product_list!=null){
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<HomeRecyclerViewFirstADBean> call, Throwable t) {

            }
        });
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.timelimited_head_view,null,false);
        timelimitedListview.addHeaderView(view);
        adapter = new TimeLimitedAdapter();
        timelimitedListview.setAdapter(adapter);

        timelimitedBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static long getEndTime(String etime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long endtime=0;
        try {
            Date date = sdf.parse(etime);
            endtime = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  endtime;
    }

    public static long getCurrentTime(){
        Date date = new Date();
        String currenttime = date.toLocaleString();
        long currenttime2 = getEndTime(currenttime);
        return currenttime2;
    }

    public static String getRestTime(long starttime,long endtime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long resttime = endtime-starttime;
        String resttime2 = sdf.format(resttime).substring(11,19);
        return resttime2;
    }


    class TimeLimitedAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return product_list==null?0:product_list.size();
        }

        @Override
        public Object getItem(int i) {
            return product_list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup parent) {
            View view = convertview;

            if(convertview==null){
                   view = LayoutInflater.from(mContext).inflate(R.layout.timelimited_listview_item,parent,false);
                   viewHolder= new TimeLimitedViewHolder(view);
            }else{
                    viewHolder = (TimeLimitedViewHolder) view.getTag();
            }
            if(product_list!=null){
                viewHolder.id=product_list.get(position).getId();
                Glide.with(mContext).load(product_list.get(position).getPic()).into(viewHolder.time_main_iv);
                viewHolder.time_name_tv.setText(product_list.get(position).getPname());
                viewHolder.time_price_tv.setText("¥"+product_list.get(position).getPromote_price());
                viewHolder.time_marketprice_tv.setText("¥"+product_list.get(position).getPrice());
                int discount = Math.round(Float.parseFloat(product_list.get(position).getPromote_price())*100/Float.parseFloat(product_list.get(position).getPrice()));
                float discount2 = (float) discount/10;
                viewHolder.time_discount_btn.setText(discount2+"折");
                viewHolder.time_limited_tv.setText("限购"+product_list.get(position).getLimit_amount()+"份");
                int percentage = Integer.parseInt(product_list.get(position).getSales())*100/Integer.parseInt(product_list.get(position).getAmount());
                viewHolder.time_havesold_tv.setText("已抢购"+percentage+"%");
                if(product_list.get(position).getSales().equals(product_list.get(position).getAmount())){
                    viewHolder.time_havesold_tv.setText("已抢购100%");
                    viewHolder.time_havesold_tv.setBackgroundResource(R.drawable.promotion_over);
                    viewHolder.time_resttime_tv.setVisibility(View.GONE);
                }else{
                    viewHolder.time_havesold_tv.setText("已抢购"+percentage+"%");
                    viewHolder.time_havesold_tv.setBackgroundResource(R.drawable.immediately_buy_new);
                    viewHolder.time_resttime_tv.setVisibility(View.VISIBLE);
                }

                String endtime = product_list.get(position).getLimit_etime();
                long etime = getEndTime(endtime);
                long ctime = getCurrentTime();
                rtime = getRestTime(ctime,etime);
                mhandler.sendEmptyMessage(1);

            }

            return view;
        }

        class TimeLimitedViewHolder implements View.OnClickListener{
            private String id;
            public final ImageView time_main_iv;
            public final TextView time_resttime_tv;
            public final TextView time_name_tv;
            public final TextView time_price_tv;
            public final TextView time_marketprice_tv;
            public final Button time_discount_btn;
            public final TextView time_limited_tv;
            public final TextView time_havesold_tv;

            public TimeLimitedViewHolder (View view){
                view.setTag(this);
                time_main_iv = (ImageView) view.findViewById(R.id.timelimited_listview_item_iv);
                time_resttime_tv = (TextView) view.findViewById(R.id.timelimited_listview_item_time_tv);
                time_name_tv = (TextView) view.findViewById(R.id.timelimited_listview_item_name_tv);
                time_price_tv = (TextView) view.findViewById(R.id.timelimited_listview_item_price_tv);
                time_marketprice_tv = (TextView) view.findViewById(R.id.timelimited_listview_item_marketprice_tv);
                time_discount_btn = (Button) view.findViewById(R.id.timelimited_listview_discount_btn);
                time_limited_tv = (TextView) view.findViewById(R.id.timelimited_listview_limited_tv);
                time_havesold_tv = (TextView) view.findViewById(R.id.timelimited_listview_havesold_tv);
                view.setOnClickListener(this);
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
