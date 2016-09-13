package com.example.weshare.assortmentmodule;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.weshare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllGoodsActivity extends AppCompatActivity {


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_goods);
        ButterKnife.bind(this);
        mContext = this;
        initView();

    }



    private void initView() {
    }
}
