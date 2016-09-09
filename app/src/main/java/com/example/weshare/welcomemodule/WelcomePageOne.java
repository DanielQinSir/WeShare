package com.example.weshare.welcomemodule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.weshare.MainActivity;
import com.example.weshare.R;

public class WelcomePageOne extends AppCompatActivity
{

    private SharedPreferences msp;
    private Handler mhandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            String flag = msp.getString("zoneID", "");
            if (flag.equals(""))
            {
                startActivity(new Intent(WelcomePageOne.this, WelcomePageTwo.class));
                finish();
            }
            else
            {
                startActivity(new Intent(WelcomePageOne.this, MainActivity.class));
                finish();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_one);
        msp = getSharedPreferences("location", MODE_PRIVATE);
        mhandler.sendEmptyMessageDelayed(1, 2000);
    }
}
