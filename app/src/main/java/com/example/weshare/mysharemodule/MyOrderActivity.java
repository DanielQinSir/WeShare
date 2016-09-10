package com.example.weshare.mysharemodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.weshare.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends AppCompatActivity
{

    @BindView(R.id.myorder_bar_back_iv)
    ImageView mMyorderBarBackIv;
    @BindView(R.id.myorder_tablayout_tl)
    TabLayout mMyorderTablayoutTl;
    @BindView(R.id.myorder_viewpager_vp)
    ViewPager mMyorderViewpagerVp;
    private List<Fragment> mFragments;
    private List<String> tabs;
    private OrderViewPagerAdapter mOrderViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);

        loadData();
        initView();
    }

    private void loadData()
    {
        mFragments = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            mFragments.add(AllOrderFragment.newInstance());
        }
        tabs = new ArrayList<>();
        tabs.add("全部订单");
        tabs.add("未完成");
        tabs.add("已完成");
    }

    private void initView()
    {
        mMyorderBarBackIv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        mOrderViewPagerAdapter = new OrderViewPagerAdapter(getSupportFragmentManager());
        mMyorderViewpagerVp.setAdapter(mOrderViewPagerAdapter);
        mMyorderViewpagerVp.setOffscreenPageLimit(3);
        mMyorderTablayoutTl.setupWithViewPager(mMyorderViewpagerVp);
        Intent intent = getIntent();
        int tab = intent.getIntExtra("tab", 1);
        mMyorderViewpagerVp.setCurrentItem(tab - 1);
    }

    private class OrderViewPagerAdapter extends FragmentPagerAdapter
    {

        public OrderViewPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            return mFragments.get(position);
        }

        @Override
        public int getCount()
        {
            return mFragments == null ? 0 : mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return tabs.get(position);
        }
    }
}
