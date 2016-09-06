package com.example.weshare.club_module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.weshare.R;
import com.example.weshare.databean.ADbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by: Daniel Qin
 * Date: 2016-08-05
 * Time: 18:53
 * For:
 */

public class ViewPagerADFragment extends Fragment
{

    private Context mContext;
    private Myadater mMyadapter;
    private LinearLayout mLinearLayout;
    private ViewPager viewpager;
    private int time;
    private boolean isAutoScroll = true;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {

            viewpager.setCurrentItem(time % 4);
            time++;
            if (isAutoScroll)
            {
                sendEmptyMessageDelayed(1, 3333);
            }
        }
    };
    private List<ADbean> mDatas;

    public static ViewPagerADFragment newInstance(List<ADbean> ads)
    {
        Bundle args = new Bundle();
        args.putSerializable("ad", (Serializable) ads);
        ViewPagerADFragment fragment = new ViewPagerADFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        Bundle arguments = getArguments();
        mDatas = (List<ADbean>) arguments.getSerializable("ad");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.viewpager_ad_fragment, null, false);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager_ad_vp);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.point_ll);
        mMyadapter = new Myadater();
        viewpager.setAdapter(mMyadapter);
        setItemSelected(0);

        viewpager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        isAutoScroll = false;
                        //移除所有的处于等待状态的Message
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        isAutoScroll = true;
                        handler.sendEmptyMessageDelayed(1, 3333);
                        break;
                }
                return false;
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
            }

            @Override
            public void onPageSelected(int position)
            {
                setItemSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
        handler.sendEmptyMessageDelayed(1, 3333);
        return view;
    }

    private void setItemSelected(int position)
    {
        for (int i = 0; i < mLinearLayout.getChildCount(); i++)
        {
            ImageView view = (ImageView) mLinearLayout.getChildAt(i);
            view.setEnabled(false);
        }
        mLinearLayout.getChildAt(position).setEnabled(true);
    }

    private class Myadater extends PagerAdapter
    {

        @Override
        public int getCount()
        {
            return mDatas == null ? 0 : mDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(mDatas.get(position)).into(imageView);
            imageView.setTag(position);
            imageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    String id = mDatas.get((Integer) view.getTag()).getId();
                    goToDetailActivity(id);
                }
            });
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
        }
    }

    private void goToDetailActivity(String id)
    {
        Intent intent = new Intent(mContext, ClubDetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

}
