package com.example.weshare.welcome_module;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weshare.MainActivity;
import com.example.weshare.R;

import java.util.ArrayList;
import java.util.List;

public class WelcomePageTwo extends AppCompatActivity {
    private Context mContext;
    List<Integer> images = new ArrayList<>();
    private ViewPager my_viewpager;
    private LinearLayout my_linearlayout;
    private WelcomePagerAdapter adapter;
    private int childcount;
    private TextView welcome_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_two);
        mContext = this;
        initView();

    }

    private void initView() {
        loadDatas();
        my_viewpager = (ViewPager) findViewById(R.id.welcomepagetwo_viewpager);
        my_linearlayout = (LinearLayout) findViewById(R.id.welcomepagetwo_layout);
        welcome_txt = (TextView) findViewById(R.id.txt);

        adapter = new WelcomePagerAdapter();
        my_viewpager.setAdapter(adapter);

        childcount = my_linearlayout.getChildCount();
        controlIndicator(0);

        my_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                controlIndicator(position);
                if(position==4){
                    welcome_txt.setVisibility(welcome_txt.VISIBLE);
                    welcome_txt.setClickable(true);
                    welcome_txt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(mContext, MainActivity.class));
                            finish();
                        }
                    });
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void controlIndicator(int index) {
        ImageView view  = (ImageView) my_linearlayout.getChildAt(index);
        for (int i = 0; i <childcount ; i++) {
            ImageView childview = (ImageView) my_linearlayout.getChildAt(i);
            childview.setEnabled(false);
        }
        view.setEnabled(true);

    }

    private void loadDatas() {
        images.add(R.drawable.image1);
        images.add(R.drawable.image2);
        images.add(R.drawable.image3);
        images.add(R.drawable.image4);
        images.add(R.drawable.image5);
    }

    class WelcomePagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return images==null?0:images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView img = new ImageView(mContext);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setImageResource(images.get(position));
            img.setTag(position);
            container.addView(img);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
