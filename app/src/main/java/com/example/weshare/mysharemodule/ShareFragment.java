package com.example.weshare.mysharemodule;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weshare.R;
import com.example.weshare.shoppingcartmodule.LoginActivity;

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
                case R.id.myshare_login_btn:
                    goToActivity(LoginActivity.class);
                    break;
                case R.id.myshare_lv_item_allorder_tv:
                    goToMyOrderTab(1);
                    break;
                case R.id.myshare_lv_item_uncompleted_tv:
                    goToMyOrderTab(2);
                    break;
                case R.id.myshare_lv_item_completed_tv:
                    goToMyOrderTab(3);
                    break;
                case R.id.myshare_lv_item_afterservice_tv:
                    Toast.makeText(mContext, "售后不在服务区!", Toast.LENGTH_SHORT).show();
                    // TODO: 2016/9/10
                    break;
                default:
                    int position = (int) view.getTag();
                    switch (position)
                    {
                        case 0:
                            Toast.makeText(mContext, "我的订单", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(mContext, "我的账户", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(mContext, "我的优惠券", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(mContext, "积分记录", Toast.LENGTH_SHORT).show();
                            break;
                        case 5:
                            Toast.makeText(mContext, "我的收藏", Toast.LENGTH_SHORT).show();
                            break;
                        case 6:
                            Toast.makeText(mContext, "帮助与反馈", Toast.LENGTH_SHORT).show();
                            break;
                        case 7:
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_CALL);
                            TextView phoneView = (TextView) view.findViewById(R.id.myshare_lv_item_phone_tv);
                            intent.setData(Uri.parse("tel:" + phoneView.getText().toString().trim()));
                            startActivity(intent);
                            break;
                        case 9:
                            goToActivity(AboutXXActivity.class);
                            break;
                    }
            }
        }
    };
    private MyshareListviewAdapter mMyshareListviewAdapter;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.share_fragmnet, container, false);
        ButterKnife.bind(this, view);
        mMyshareBarSettingsIv.setOnClickListener(mlistener);
        View headView = LayoutInflater.from(mContext).inflate(R.layout.myshare_head_view, null, false);
        mMyshareLoginBtn = (Button) headView.findViewById(R.id.myshare_login_btn);
        mMyshareLoginBtn.setOnClickListener(mlistener);
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

    private void goToActivity(Class clazz)
    {
        startActivity(new Intent(mContext,clazz));
    }

    private void goToMyOrderTab(int i)
    {
        Intent intent = new Intent(mContext, MyOrderActivity.class);
        intent.putExtra("tab",i);
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
