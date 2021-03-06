package com.example.weshare.mysharemodule;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weshare.MyApplication;
import com.example.weshare.R;
import com.example.weshare.shoppingcartmodule.LoginActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ShareFragment extends Fragment
{

    @BindView(R.id.myshare_bar_settings_iv)
    ImageView mMyshareBarSettingsIv;
    @BindView(R.id.myshare_bar_scan_iv)
    ImageView mMyshareBarScanIv;
    private Button mMyshareLoginBtn;
    @BindView(R.id.myshare_listview_lv)
    ListView mMyshareListviewLv;
    private Context mContext;

    private List<Item> datas;

    private View.OnClickListener mlistener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.myshare_bar_settings_iv:
                    goToActivity(SettingsActivity.class);
                    break;
                case R.id.myshare_bar_scan_iv:
                    IntentIntegrator.forSupportFragment(ShareFragment.this).initiateScan();
                    break;
                case R.id.myshare_login_btn:
                    goToActivityForResult(LoginActivity.class);
                    break;
                case R.id.myshare_lv_item_allorder_tv:
                    if (MyApplication.sUser == null)
                    {
                        goToActivityForResult(LoginActivity.class);
                        return;
                    }
                    goToMyOrderTab(1);
                    break;
                case R.id.myshare_lv_item_uncompleted_tv:
                    if (MyApplication.sUser == null)
                    {
                        goToActivityForResult(LoginActivity.class);
                        return;
                    }
                    goToMyOrderTab(2);
                    break;
                case R.id.myshare_lv_item_completed_tv:
                    if (MyApplication.sUser == null)
                    {
                        goToActivityForResult(LoginActivity.class);
                        return;
                    }
                    goToMyOrderTab(3);
                    break;
                case R.id.myshare_lv_item_afterservice_tv:
                    if (MyApplication.sUser == null)
                    {
                        goToActivityForResult(LoginActivity.class);
                        return;
                    }
                    Toast.makeText(mContext, "售后不在服务区!", Toast.LENGTH_SHORT).show();
                    // TODO: 2016/9/10
                    break;
                default:
                    int position = (int) view.getTag();
                    switch (position)
                    {
                        case 0:
                            if (MyApplication.sUser == null)
                            {
                                goToActivityForResult(LoginActivity.class);
                                return;
                            }
                            Toast.makeText(mContext, "未查询到订单!", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            if (MyApplication.sUser == null)
                            {
                                goToActivityForResult(LoginActivity.class);
                                return;
                            }
                            Toast.makeText(mContext, "账户为空!", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            if (MyApplication.sUser == null)
                            {
                                goToActivityForResult(LoginActivity.class);
                                return;
                            }
                            Toast.makeText(mContext, "未查询到优惠券!", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            if (MyApplication.sUser == null)
                            {
                                goToActivityForResult(LoginActivity.class);
                                return;
                            }
                            Toast.makeText(mContext, "未查询到积分记录!", Toast.LENGTH_SHORT).show();
                            break;
                        case 5:
                            if (MyApplication.sUser == null)
                            {
                                goToActivityForResult(LoginActivity.class);
                                return;
                            }
                            Toast.makeText(mContext, "未查询到收藏!", Toast.LENGTH_SHORT).show();
                            break;
                        case 6:
                            if (MyApplication.sUser == null)
                            {
                                goToActivityForResult(LoginActivity.class);
                                return;
                            }
                            goToActivity(HelpActivity.class);
                            break;
                        case 7:
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_CALL);
                            TextView phoneView = (TextView) view.findViewById(R.id.myshare_lv_item_phone_tv);
                            intent.setData(Uri.parse("tel:" + phoneView.getText().toString().trim()));
                            startActivity(intent);
                            break;
                        case 8:
                            goToActivity(MapActivity.class);
                            break;
                        case 9:
                            goToActivity(AboutXXActivity.class);
                            break;
                    }
            }
        }
    };
    private MyshareListviewAdapter mMyshareListviewAdapter;
    private TextView tv;

    public static ShareFragment newInstance()
    {
        return new ShareFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        tv.setText(MyApplication.sUser == null ? "您尚未登录" : "欢迎您");
        mMyshareLoginBtn.setText(MyApplication.sUser == null ? "登录/注册" : "已登录(" + MyApplication.sUser.getUsername() + ")");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.share_fragmnet, container, false);
        ButterKnife.bind(this, view);
        mMyshareBarSettingsIv.setOnClickListener(mlistener);
        View headView = LayoutInflater.from(mContext).inflate(R.layout.myshare_head_view, null, false);
        mMyshareLoginBtn = (Button) headView.findViewById(R.id.myshare_login_btn);
        tv = (TextView) headView.findViewById(R.id.myshare_tv);
        tv.setText(MyApplication.sUser == null ? "您尚未登录" : "欢迎您");
        mMyshareLoginBtn.setText(MyApplication.sUser == null ? "登录/注册" : "已登录(" + MyApplication.sUser.getUsername() + ")");
        mMyshareLoginBtn.setOnClickListener(mlistener);
        mMyshareBarScanIv.setOnClickListener(mlistener);
        mMyshareListviewLv.addHeaderView(headView);
        initData();
        mMyshareListviewAdapter = new MyshareListviewAdapter();
        mMyshareListviewLv.setAdapter(mMyshareListviewAdapter);
        return view;
    }

    private void initData()
    {
        datas = new ArrayList<>();
        datas.add(new Item("我的订单", R.drawable.wddd));
        datas.add(new Item());
        datas.add(new Item("我的账户", R.drawable.wdzh));
        datas.add(new Item("我的优惠券", R.drawable.usercoupon));
        datas.add(new Item("积分记录", R.drawable.memberpoints_histroy_icon));
        datas.add(new Item("我的收藏", R.drawable.wdsc));
        datas.add(new Item("帮助与反馈", R.drawable.help_and_feedback_icon));
        datas.add(new Item());
        datas.add(new Item());
        datas.add(new Item("关于享享", R.drawable.wdxx));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (data != null)
        {
            switch (requestCode)
            {
                case 1:
                    reFreShFragment(data);
                    break;
                default:
                    showScanResult(requestCode, resultCode, data);
                    break;
            }
        }
    }

    private void showScanResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null)
        {
            String resultContents = result.getContents();
            if (! TextUtils.isEmpty(resultContents))
            {
                String contents = resultContents.trim();
                if (TextUtils.isEmpty(contents))
                {
                    Toast.makeText(mContext, "扫描被取消!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (contents.startsWith("http://"))
                    {
                        Uri uri = Uri.parse(contents);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(mContext, "扫描结果:\n" + contents, Toast.LENGTH_LONG).show();
                    }
                }
            }
            else
            {
                Toast.makeText(mContext, "未扫描到结果!", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void goToActivityForResult(Class clazz)
    {
        startActivityForResult(new Intent(mContext, clazz), 1);
    }

    private void goToActivity(Class clazz)
    {
        startActivity(new Intent(mContext, clazz));
    }

    private void reFreShFragment(Intent data)
    {
        if (data.getStringExtra("ok").equals("ok"))
        {
            //刷新视图
            mMyshareLoginBtn.setText("已登录(" + MyApplication.sUser.getUsername() + ")");
            tv.setText("已登录");
        }
    }

    private void goToMyOrderTab(int i)
    {
        Intent intent = new Intent(mContext, MyOrderActivity.class);
        intent.putExtra("tab", i);
        startActivity(intent);
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

    class MyshareListviewAdapter extends BaseAdapter
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
            View itemView = null;
            switch (i)
            {
                case 1:
                    itemView = LayoutInflater.from(mContext).inflate(R.layout.myshare_listview_item_order, viewGroup, false);
                    TextView allOrderTv = (TextView) itemView.findViewById(R.id.myshare_lv_item_allorder_tv);
                    TextView unCompletedOrderTv = (TextView) itemView.findViewById(R.id.myshare_lv_item_uncompleted_tv);
                    TextView completedOrderTv = (TextView) itemView.findViewById(R.id.myshare_lv_item_completed_tv);
                    TextView afterServiceOrderTv = (TextView) itemView.findViewById(R.id.myshare_lv_item_afterservice_tv);
                    allOrderTv.setOnClickListener(mlistener);
                    unCompletedOrderTv.setOnClickListener(mlistener);
                    completedOrderTv.setOnClickListener(mlistener);
                    afterServiceOrderTv.setOnClickListener(mlistener);
                    break;
                case 7:
                    itemView = LayoutInflater.from(mContext).inflate(R.layout.myshare_listview_item_phone, viewGroup, false);
                    itemView.setTag(i);
                    itemView.setOnClickListener(mlistener);
                    break;
                case 8:
                    itemView = LayoutInflater.from(mContext).inflate(R.layout.myshare_listview_item_adress, viewGroup, false);
                    itemView.setTag(i);
                    itemView.setOnClickListener(mlistener);
                    break;
                default:
                    itemView = LayoutInflater.from(mContext).inflate(R.layout.myshare_listview_item_normal, viewGroup, false);
                    ImageView iconIv = (ImageView) itemView.findViewById(R.id.myshare_lv_item_icon_iv);
                    TextView titleTv = (TextView) itemView.findViewById(R.id.myshare_lv_item_title_tv);
                    Item item = datas.get(i);
                    iconIv.setImageResource(item.id);
                    titleTv.setText(item.title);
                    itemView.setTag(i);
                    itemView.setOnClickListener(mlistener);
                    break;
            }
            return itemView;
        }
    }
}
