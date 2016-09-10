package com.example.weshare.welcomemodule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.weshare.MainActivity;
import com.example.weshare.R;
import com.example.weshare.databean.StartADBean;
import com.example.weshare.utils.HttpServiceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomePageOne extends AppCompatActivity {
    private SharedPreferences msp;
    private int succeed;
    private  String flag;
    private Handler mhandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    if (flag.equals("")) {
                       /* SharedPreferences.Editor editor = msp.edit();
                        editor.putString("flag","123");
                        editor.commit();*/
                        startActivity(new Intent(WelcomePageOne.this, WelcomePageTwo.class));
                        finish();
                    } else {
                        startActivity(new Intent(WelcomePageOne.this, WelcomePageThree.class));
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
                    /*if(flag.equals("")){
                        *//*SharedPreferences.Editor editor = msp.edit();
                        editor.putString("flag","123");
                        editor.commit();*//*
                        startActivity(new Intent(WelcomePageOne.this,WelcomePageTwo.class));
                        finish();
                    }else{
                        startActivity(new Intent(WelcomePageOne.this,MainActivity.class));
                        finish();
                    }*/
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_one);
        msp = getSharedPreferences("location", MODE_PRIVATE);
        //  msp=getSharedPreferences("welcome",MODE_PRIVATE);
        getDatas();
         flag = msp.getString("zoneID", "");

       // flag = msp.getString("flag","");

    }

    private void getDatas() {
        HttpServiceUtil.init().getSatrtADInfo(HttpServiceUtil.SID).enqueue(new Callback<StartADBean>() {
            @Override
            public void onResponse(Call<StartADBean> call, Response<StartADBean> response) {
               succeed = response.body().getSucceed();
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
       // mhandler.sendEmptyMessageDelayed(1, 2000);
    }
}
