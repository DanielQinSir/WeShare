package com.example.weshare.welcomemodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.weshare.MainActivity;
import com.example.weshare.R;
import com.example.weshare.databean.StartADBean;
import com.example.weshare.utils.HttpServiceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomePageThree extends AppCompatActivity {

    @BindView(R.id.welcomepagethree_iv)
    ImageView welcomepagethreeIv;

    private String pic_url;
    private Context mContext;

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(WelcomePageThree.this, MainActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_three);
        ButterKnife.bind(this);
        mContext=this;
        getIntentDatas();


    }

    private void getIntentDatas() {
       Intent i= getIntent();
        pic_url = i.getStringExtra("pic");
        Glide.with(mContext).load(pic_url).into(welcomepagethreeIv);
        mhandler.sendEmptyMessageDelayed(1,2000);
    }

   /* private void getDatas() {
        HttpServiceUtil.init().getSatrtADInfo(HttpServiceUtil.SID).enqueue(new Callback<StartADBean>() {
            @Override
            public void onResponse(Call<StartADBean> call, Response<StartADBean> response) {
                pic_url=response.body().getPic();
                if(pic_url!=null){
                    Glide.with(mContext).load(pic_url).into(welcomepagethreeIv);
                }
                mhandler.sendEmptyMessageDelayed(1,2000);
            }

            @Override
            public void onFailure(Call<StartADBean> call, Throwable t) {

            }
        });
    }*/
}
