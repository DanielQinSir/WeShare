package com.example.weshare;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.weshare.assortment_module.AssortFragment;
import com.example.weshare.club_module.ClubFragment;
import com.example.weshare.homepage_module.HomeFragment;
import com.example.weshare.myshare_module.ShareFragment;
import com.example.weshare.shoppingcart_module.ShoppingCartFragment;
import com.example.weshare.utils.UpdateUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup my_radiogroup;
    private ViewPager my_viewpager;
    private MyPagerAdapter adapter;
    private List<Fragment> fragments =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UpdateUtil.checkForUpdateBean(this);
        initDatas();
        initView();
    }

    private void initView() {
        my_radiogroup = (RadioGroup) findViewById(R.id.main_RG);
        my_viewpager = (ViewPager) findViewById(R.id.main_viewpager);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        my_viewpager.setAdapter(adapter);
        my_viewpager.setCurrentItem(0);

        my_radiogroup.check(R.id.main_home_rb);

        my_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.main_home_rb:
                        my_viewpager.setCurrentItem(0);
                        break;
                    case R.id.main_assort_rb:
                        my_viewpager.setCurrentItem(1);
                        break;
                    case R.id.main_club_rb:
                        my_viewpager.setCurrentItem(2);
                        break;
                    case R.id.main_shoppingcart_rb:
                        my_viewpager.setCurrentItem(3);
                        break;
                    case R.id.main_myshare_rb:
                        my_viewpager.setCurrentItem(4);
                }
            }
        });



        my_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        my_radiogroup.check(R.id.main_home_rb);
                        break;
                    case 1:
                        my_radiogroup.check(R.id.main_assort_rb);
                        break;
                    case 2:
                        my_radiogroup.check(R.id.main_club_rb);
                        break;
                    case 3:
                        my_radiogroup.check(R.id.main_shoppingcart_rb);
                        break;
                    case 4:
                        my_radiogroup.check(R.id.main_myshare_rb);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        my_viewpager.setOffscreenPageLimit(5);
    }

    private void initDatas() {
        fragments.add(HomeFragment.newInstance());
        fragments.add(AssortFragment.newInstance());
        fragments.add(ClubFragment.newInstance());
        fragments.add(ShoppingCartFragment.newInstance());
        fragments.add(ShareFragment.newInstance());

    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
