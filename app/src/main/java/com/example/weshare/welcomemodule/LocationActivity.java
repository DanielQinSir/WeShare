package com.example.weshare.welcomemodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.weshare.MainActivity;
import com.example.weshare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends AppCompatActivity {

    @BindView(R.id.location_next_tv)
    TextView locationNextTv;
    @BindView(R.id.location_depart_tv)
    TextView locationDepartTv;
    @BindView(R.id.location_chooseaddress_RL)
    RelativeLayout locationChooseaddressRL;
    @BindView(R.id.location_register_btn)
    Button locationRegisterBtn;
    @BindView(R.id.location_street_tv)
    TextView locationStreetTv;
    @BindView(R.id.location_address_tv)
    TextView locationAddressTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        locationNextTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LocationActivity.this, MainActivity.class));
            }
        });
        locationRegisterBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LocationActivity.this, LoginActivity.class));
            }
        });
    }


}
