package com.example.weshare.clubmodule;

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

public class ForumActivity extends AppCompatActivity
{

    @BindView(R.id.club_forum_bar_back_iv)
    ImageView mClubForumBarBackIv;
    @BindView(R.id.club_forum_tablayout_tl)
    TabLayout mClubForumTablayoutTl;
    @BindView(R.id.club_forum_viewpager_vp)
    ViewPager mClubForumViewpagerVp;
    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private MyPagerAdapter mMyPagerAdapter;
    private int mTag;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        ButterKnife.bind(this);
        loadData();
        initView();
    }

    private void initView()
    {
        mClubForumBarBackIv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        initFragments();
        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mClubForumViewpagerVp.setAdapter(mMyPagerAdapter);
        mClubForumViewpagerVp.setOffscreenPageLimit(2);
        mClubForumTablayoutTl.setupWithViewPager(mClubForumViewpagerVp);
        if (mTag == 2)
        {
            mClubForumViewpagerVp.setCurrentItem(1);
        }
    }

    private void loadData()
    {
        Intent intent = getIntent();
        mTag = intent.getIntExtra("tag",-1);
        titles.add("我的帖子");
        titles.add("回复我的");
    }

    private void initFragments()
    {
        fragments.add(ForumFragmen.newInstance());
        fragments.add(ForumFragmen.newInstance());
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
            return fragments.get(position);
        }

        @Override
        public int getCount()
        {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return titles.get(position);
        }
    }
}
