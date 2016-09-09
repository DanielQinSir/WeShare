package com.example.weshare.welcomemodule;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.weshare.MainActivity;
import com.example.weshare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomePageThree extends AppCompatActivity {

    @BindView(R.id.welcomepagethree_iv)
    ImageView welcomepagethreeIv;

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

        mhandler.sendEmptyMessageDelayed(1,2000);
    }
}
