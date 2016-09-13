package com.example.weshare.homepagemodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.weshare.R;
import com.example.weshare.shoppingcartmodule.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeListDetailTwo extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.home_list_detailtwo_back_iv)
    ImageView homeListDetailtwoBackIv;
    @BindView(R.id.home_list_detailtwo_tablayout)
    TabLayout homeListDetailtwoTablayout;
    @BindView(R.id.home_list_detailtwo_viewpager)
    ViewPager homeListDetailtwoViewpager;
    @BindView(R.id.home_list_detailtwo_reduce_iv)
    ImageView homeListDetailtwoReduceIv;
    @BindView(R.id.home_list_detailtwo_num_tv)
    TextView homeListDetailtwoNumTv;
    @BindView(R.id.home_list_detailtwo_add_iv)
    ImageView homeListDetailtwoAddIv;
    @BindView(R.id.home_list_detailtwo_shopping_btn)
    Button homeListDetailtwoShoppingBtn;
    @BindView(R.id.home_list_detailtwo_shoppingcart_iv)
    ImageView homeListDetailtwoShoppingcartIv;
    @BindView(R.id.home_list_detailtwo_shoppingcart_num_iv)
    Button homeListDetailtwoShoppingcartNumIv;
    @BindView(R.id.home_list_detailtwo_RL)
    RelativeLayout homeListDetailtwoRL;

    private Context mContext;
    private int i=1;
    private String pid;
    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments =new ArrayList<>();
    private DetailPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list_detail_two);
        ButterKnife.bind(this);
        mContext = this;
        getIntentData();
        initView();
    }

    private void getIntentData() {
        Intent intent =getIntent();
        pid = intent.getStringExtra("product_id");
    }

    private void initView() {
        homeListDetailtwoBackIv.setOnClickListener(this);
        homeListDetailtwoReduceIv.setOnClickListener(this);
        homeListDetailtwoAddIv.setOnClickListener(this);
        homeListDetailtwoShoppingBtn.setOnClickListener(this);
        homeListDetailtwoTablayout.setTabMode(TabLayout.MODE_FIXED);
        initDatas();
        adapter =new DetailPagerAdapter(getSupportFragmentManager());
        homeListDetailtwoViewpager.setAdapter(adapter);
        homeListDetailtwoTablayout.setupWithViewPager(homeListDetailtwoViewpager);
    }

    private void initDatas() {
        titles.add("商品简介");
        titles.add("图文详情");
        titles.add("用户评价");
        fragments.add(ProductIntroduceFragment.newInstance(pid));
        fragments.add(PictureDetailFragment.newInstance(pid));
        fragments.add(UserCommentFragment.newInstance(pid));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_list_detailtwo_back_iv:
                finish();
                break;
            case R.id.home_list_detailtwo_reduce_iv:
                if(i>1){
                    i=i-1;
                    homeListDetailtwoNumTv.setText(i+"");
                }
                break;
            case R.id.home_list_detailtwo_add_iv:
                if(i<100){
                    i=i+1;
                    homeListDetailtwoNumTv.setText(i+"");
                }
                break;
            case R.id.home_list_detailtwo_shoppingcart_iv:
                Intent intent =new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    class DetailPagerAdapter extends FragmentStatePagerAdapter{
        public DetailPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments==null?0:fragments.size();
        }

        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

    }
}
