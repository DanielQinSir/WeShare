package com.example.weshare.mysharemodule;

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

public class HelpActivity extends AppCompatActivity
{

    @BindView(R.id.help_bar_back_iv)
    ImageView mHelpBarBackIv;
    @BindView(R.id.help_activity_tl)
    TabLayout mHelpActivityTl;
    @BindView(R.id.help_activity_vp)
    ViewPager mHelpActivityVp;
    private List<Fragment> mFragments;
    private List<String> tabs;
    private MyPagerAdapter mMyPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        loadData();
        initView();
    }

    private void loadData()
    {
        mFragments = new ArrayList<>();
        mFragments.add(TabHelpFragment.newInstance());
        mFragments.add(TabSuggestFragment.newInstance());
        tabs = new ArrayList<>();
        tabs.add("帮助");
        tabs.add("反馈");
    }

    private void initView()
    {
        mHelpBarBackIv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mHelpActivityVp.setAdapter(mMyPagerAdapter);
        mHelpActivityTl.setupWithViewPager(mHelpActivityVp);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter
    {

        public MyPagerAdapter(FragmentManager fm)
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
