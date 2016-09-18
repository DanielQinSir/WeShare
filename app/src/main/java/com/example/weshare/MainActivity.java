package com.example.weshare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.weshare.assortmentmodule.AssortFragment;
import com.example.weshare.clubmodule.ClubFragment;
import com.example.weshare.homepagemodule.HomeFragment;
import com.example.weshare.mysharemodule.ShareFragment;
import com.example.weshare.shoppingcartmodule.ShoppingCartFragment;
import com.example.weshare.utils.UpdateUtil;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity implements IMyCallBack
{

    private RadioGroup my_radiogroup;
    private ViewPager my_viewpager;
    private MyPagerAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();
    private long exitTime;
    private Intent intent;
    private int flag;
    private BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String content = intent.getStringExtra("content");
            if (! TextUtils.isEmpty(content))
            {
                new AlertDialog.Builder(MainActivity.this).setTitle("服务器消息").setMessage(content).setPositiveButton("我知道了", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialogInterface.dismiss();
                    }
                }).show();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initView();
        intent = getIntent();
        flag = intent.getIntExtra("flag",0);
        if(flag==1){
            my_viewpager.setCurrentItem(3);
        }else{
            UpdateUtil.checkForUpdateBean(this);
            checkPushStatue();
        }
        checkPushStatue();
        //注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(MyJPushReceiver.ACTION);
        registerReceiver(mReceiver, filter);
    }

    private void checkPushStatue()
    {
        if (MyApplication.acceptMessage == 0)
        {
            MyApplication.acceptMessage = 2;
            showPushMessageDialog();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //注销接收器
        unregisterReceiver(mReceiver);
    }

    private void initView()
    {
        my_radiogroup = (RadioGroup) findViewById(R.id.main_RG);
        my_viewpager = (ViewPager) findViewById(R.id.main_viewpager);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        my_viewpager.setAdapter(adapter);
        my_viewpager.setCurrentItem(0);

        my_radiogroup.check(R.id.main_home_rb);

        my_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                switch (checkedId)
                {
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

        my_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                switch (position)
                {
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
            public void onPageScrollStateChanged(int state)
            {

            }
        });

        my_viewpager.setOffscreenPageLimit(5);
    }

    private void initDatas()
    {
        fragments.add(HomeFragment.newInstance());
        fragments.add(AssortFragment.newInstance());
        fragments.add(ClubFragment.newInstance());
        ShoppingCartFragment shoppingCartFragment = ShoppingCartFragment.newInstance();
        shoppingCartFragment.setListener(this);
        fragments.add(shoppingCartFragment);
        fragments.add(ShareFragment.newInstance());
    }

    private void showPushMessageDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("是否接受推送消息").setMessage("开启后能在第一时间收到最新的商品信息哦!");
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText("接受推送");
        checkBox.setChecked(true);
        checkBox.setPadding(50, 0, 0, 0);
        checkBox.setTextSize(22);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b)
                {
                    MyApplication.acceptMessage = 1;
                    if (JPushInterface.isPushStopped(MainActivity.this))
                    {
                        JPushInterface.resumePush(MainActivity.this);//接受推送
                    }
                }
                else
                {
                    MyApplication.acceptMessage = 2;
                    JPushInterface.stopPush(MainActivity.this);//停止推送
                }
            }
        });
        builder.setView(checkBox);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
                SharedPreferences sharedPreferences = getSharedPreferences("push", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("accept", MyApplication.acceptMessage);
                editor.commit();
            }
        });
        builder.show();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                Toast.makeText(MainActivity.this, "再按一次退出程序!", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
            else
            {
                finish();
//                onDestroy();
//                System.exit(0);
//                android.os.Process.killProcess(android.os.Process.myPid());
//                ActivityManager activityMgr=(ActivityManager)MainActivity.this.getSystemService(ACTIVITY_SERVICE);
//                activityMgr.restartPackage(getPackageName());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void goToMainActivityFirstFragment()
    {
        my_viewpager.setCurrentItem(0);
    }

    @Override
    public void reFreshShoppingCartFragment()
    {
        adapter.notifyDataSetChanged();
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
        public int getItemPosition(Object object)
        {
            if (object instanceof ShoppingCartFragment || object instanceof ShareFragment)
            {
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }
    }
}
