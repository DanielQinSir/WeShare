package com.example.weshare.welcomemodule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.weshare.MainActivity;
import com.example.weshare.R;
import com.example.weshare.databean.StartADBean;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomePageOne extends AppCompatActivity {
    private SharedPreferences msp;
    private int succeed;
    private  String flag;
    private String pic_url;
    private Context mContext;
    private Bitmap bm;
    private Handler mhandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    if (flag.equals("")) {

                       Intent i = new Intent(WelcomePageOne.this, WelcomePageTwo.class);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(WelcomePageOne.this, WelcomePageThree.class);
                        if(pic_url!=null){
                            i.putExtra("pic",pic_url);
                        }
                        startActivity(i);
                        finish();
                    }
                    break;
                case 2:
                    if (flag.equals("")) {
                        startActivity(new Intent(WelcomePageOne.this, WelcomePageTwo.class));
                        finish();
                    } else {
                        startActivity(new Intent(WelcomePageOne.this, MainActivity.class));
                        finish();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_one);
        mContext= this;
        msp = getSharedPreferences("location", MODE_PRIVATE);
        flag = msp.getString("zoneID", "");
        getDatas();
    }

    private void getDatas() {
        HttpServiceUtil.init().getSatrtADInfo(HttpServiceUtil.SID).enqueue(new Callback<StartADBean>() {
            @Override
            public void onResponse(Call<StartADBean> call, Response<StartADBean> response) {
               succeed = response.body().getSucceed();
                pic_url=response.body().getPic();

                      /*  try {
                            bm = Glide.with(mContext).load(pic_url).asBitmap().into(500,500).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }*/
                        if(succeed==1){
                            mhandler.sendEmptyMessageDelayed(1,2000);
                        }
                        if(succeed==0){
                            mhandler.sendEmptyMessageDelayed(2,2000);
                        }
            }

            @Override
            public void onFailure(Call<StartADBean> call, Throwable t) {
                startActivity(new Intent(WelcomePageOne.this,MainActivity.class));
            }
        });

    }
}
