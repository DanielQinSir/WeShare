package com.example.weshare.mysharemodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weshare.R;
import com.example.weshare.settingmodule.MessagePushActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity
{

    @BindView(R.id.setting_bar_back_iv)
    ImageView mSettingBarBackIv;
    @BindView(R.id.setting_lv)
    ListView mSettingLv;
    private List<Item> datas;

    private View.OnClickListener mlistener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.setting_bar_back_iv:
                    finish();
                    break;
                default:
                    int position = (int) view.getTag();
                    switch (position)
                    {
                        case 0:
                            Toast.makeText(SettingsActivity.this, "修改用户名", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(SettingsActivity.this, "收货地址管理", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(SettingsActivity.this, "更换已绑定的手机号", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(SettingsActivity.this, "登陆密码", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(SettingsActivity.this, "享享币支付密码", Toast.LENGTH_SHORT).show();
                            break;
                        case 5:
                            Toast.makeText(SettingsActivity.this, "安置框服务", Toast.LENGTH_SHORT).show();
                            break;
                        case 6:
                            goToActivity(MessagePushActivity.class);
                            break;
                    }
            }
        }
    };
    private SettingListviewAdapter mSettingListviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView()
    {
        mSettingBarBackIv.setOnClickListener(mlistener);
        mSettingListviewAdapter = new SettingListviewAdapter();
        mSettingLv.setAdapter(mSettingListviewAdapter);
    }

    private void initData()
    {
        datas = new ArrayList<>();
        datas.add(new Item("修改用户名", R.drawable.update_login_username));
        datas.add(new Item("收货地址管理", R.drawable.shdz));
        datas.add(new Item("更换已绑定的手机号", R.drawable.update_phonenumber));
        datas.add(new Item("登录密码", R.drawable.update_login_psd));
        datas.add(new Item("享享币支付密码", R.drawable.update_pay_psd));
        datas.add(new Item("安置框服务", R.drawable.user_basket));
        datas.add(new Item("信息推送", R.drawable.message));
    }

    private void goToActivity(Class clazz)
    {
        startActivity(new Intent(SettingsActivity.this, clazz));
    }

    private class Item
    {

        private String title;
        private int id;

        public Item(String title, int id)
        {
            this.title = title;
            this.id = id;
        }

        public Item()
        {
        }
    }

    private class SettingListviewAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public Object getItem(int i)
        {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View itemView = view;
            if (itemView == null)
            {
                itemView = LayoutInflater.from(SettingsActivity.this).inflate(R.layout.setting_listview_item, viewGroup, false);
            }
            ImageView iconIv = (ImageView) itemView.findViewById(R.id.setting_lv_item_icon_iv);
            TextView titleTv = (TextView) itemView.findViewById(R.id.setting_lv_item_title_tv);
            Item item = datas.get(i);
            iconIv.setImageResource(item.id);
            titleTv.setText(item.title);
            itemView.setTag(i);
            itemView.setOnClickListener(mlistener);
            if (i == 6)
            {
                TextView switchView = (TextView) itemView.findViewById(R.id.setting_lv_item_switch_tv);
                switchView.setText(MessagePushActivity.acccptedPush == 1 ? "已开启" : "已关闭");
            }
            return itemView;
        }
    }
}
