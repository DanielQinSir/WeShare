package com.example.weshare.shoppingcartmodule;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weshare.BaseFragment;
import com.example.weshare.MyApplication;
import com.example.weshare.R;
import com.example.weshare.databean.ProductBean;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ShoppingCartFragment extends BaseFragment
{

    @BindView(R.id.shoppingcart_logined_edit_cb)
    CheckBox mShoppingcartLoginedEditCb;
    @BindView(R.id.shoppingcart_logined_totalchecked_cb)
    CheckBox mShoppingcartLoginedTotalcheckedCb;
    @BindView(R.id.shoppingcart_logined_totalprice_tv)
    TextView mShoppingcartLoginedTotalpriceTv;
    @BindView(R.id.shoppingcart_logined_pay_btn)
    Button mShoppingcartLoginedPayBtn;
    @BindView(R.id.shoppingcart_logined_totalshow_rl)
    RelativeLayout mShoppingcartLoginedTotalshowRl;
    @BindView(R.id.shoppingcart_logined_emptyview_iv)
    ImageView mShoppingcartLoginedEmptyviewIv;
    @BindView(R.id.shoppingcart_logined_emptyview_tv)
    TextView mShoppingcartLoginedEmptyviewTv;
    private Button mShoppingcartUnloginLoginBtn;
    private Button mShoppingcartUnloginGootherBtn;
    @BindView(R.id.shoppingcart_logined_cart_lv)
    ListView mShoppingcartLoginedCartLv;
    @BindView(R.id.shoppingcart_logined_goother_btn)
    Button mShoppingcartLoginedGootherBtn;
    @BindView(R.id.shoppingcart_logined_emptyview)
    RelativeLayout mShoppingcartLoginedEmptyview;
    private Context mContext;
    private String length = "99";
    private List<ProductBean.CartBean> mData;
    private List<Item> isChecked;
    private static float totalMoney = 0;
    private static int totalKinds = 0;
    private Handler mHandler = new Handler(new Handler.Callback()
    {
        @Override
        public boolean handleMessage(Message message)
        {
            switch (message.what)
            {
                case 1:
                    if (totalMoney == 0)
                    {
                        mShoppingcartLoginedTotalshowRl.setVisibility(View.GONE);
                    }
                    else
                    {
                        mShoppingcartLoginedTotalshowRl.setVisibility(View.VISIBLE);
                        mShoppingcartLoginedTotalpriceTv.setText("  ¥" + totalMoney);
                        mShoppingcartLoginedPayBtn.setText("结算(" + totalKinds + ")");
                    }
                    break;
            }
            return true;
        }
    });

    private View.OnClickListener mlistener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.shoppingcart_unlogin_login_btn:
                    startActivityForResult(new Intent(mContext, LoginActivity.class), 1);
                    break;
                case R.id.shoppingcart_unlogin_goother_btn:
                    mIMyCallBack.goToMainActivityFirstFragment();
                    break;
                case R.id.shoppingcart_logined_goother_btn:
                    mIMyCallBack.goToMainActivityFirstFragment();
                    break;
                case R.id.shoppingcart_logined_pay_btn://支付宝支付
                    new ALiPayHelper(mContext,totalMoney).pay();
                    break;
                case R.id.cart_item_numadd_iv:
                    int position = (int) view.getTag();
                    ProductBean.CartBean cartBean = mData.get(position);
                    int num = Integer.parseInt(cartBean.getNum());
                    cartBean.setNum((num + 1) + "");
                    reFreshAdapter();
                    break;
                case R.id.cart_item_numdec_iv:
                    int position2 = (int) view.getTag();
                    ProductBean.CartBean cartBean2 = mData.get(position2);
                    int num2 = Integer.parseInt(cartBean2.getNum());
                    if (num2 >= 1)
                    {
                        cartBean2.setNum((num2 - 1) + "");
                    }
                    else
                    {
                        Toast.makeText(mContext, "不能再减啦!", Toast.LENGTH_SHORT).show();
                    }
                    reFreshAdapter();
                    break;
                case R.id.cart_item_delete_iv:
                    int position3 = (int) view.getTag();
                    mData.remove(position3);
                    reFreshAdapter();
                    break;

            }
        }
    };
    private boolean isEdit;//是否是编辑状态
    private CartListViewAdapter mCartListViewAdapter;


    private void goToActivity(Class clazz)
    {
        startActivity(new Intent(mContext, clazz));
    }

    public static ShoppingCartFragment newInstance()
    {
        return new ShoppingCartFragment();
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
        View view = null;
        if (MyApplication.sUser == null)//未登录
        {
            view = inflater.inflate(R.layout.shoppingcart_fragmnet_unlogined, container, false);
            mShoppingcartUnloginLoginBtn = (Button) view.findViewById(R.id.shoppingcart_unlogin_login_btn);
            mShoppingcartUnloginGootherBtn = (Button) view.findViewById(R.id.shoppingcart_unlogin_goother_btn);
            mShoppingcartUnloginLoginBtn.setOnClickListener(mlistener);
            mShoppingcartUnloginGootherBtn.setOnClickListener(mlistener);
        }
        else//已登录
        {
            view = inflater.inflate(R.layout.shoppingcart_fragmnet_logined, container, false);
            ButterKnife.bind(this, view);
            loadData();
            mShoppingcartLoginedGootherBtn.setOnClickListener(mlistener);//空视图
            mShoppingcartLoginedCartLv.setEmptyView(mShoppingcartLoginedEmptyview);

            mShoppingcartLoginedEditCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b)
                {
                    isEdit = b;
                    reFreshAdapter();
                }
            });
            mShoppingcartLoginedTotalcheckedCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b)
                {
                    for (int i = 0; i < isChecked.size(); i++)
                    {
                        isChecked.get(i).checked = b;
                    }
                    if (! b)
                    {
                        totalMoney = 0;
                        totalKinds = 0;
                    }
                    reFreshAdapter();
                }
            });
            mShoppingcartLoginedPayBtn.setOnClickListener(mlistener);
            mShoppingcartLoginedCartLv.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                {
                    ProductBean.CartBean cartBean = (ProductBean.CartBean) view.getTag(R.id.item_position);
                    Toast.makeText(mContext, "你点击了" + cartBean.getPname() + "!", Toast.LENGTH_SHORT).show();
                }
            });
            mCartListViewAdapter = new CartListViewAdapter();
            mShoppingcartLoginedCartLv.setAdapter(mCartListViewAdapter);
        }
        return view;
    }

    private void loadData()
    {
        final ProgressDialog dialog = ProgressDialog.show(mContext, "联网中", "正在获取购物车信息...");
        HttpServiceUtil.init().getCartInfo(0, length, HttpServiceUtil.SID).enqueue(new Callback<ProductBean>()
        {

            @Override
            public void onResponse(Call<ProductBean> call, Response<ProductBean> response)
            {
                ProductBean productBean = response.body();
                if (productBean.getSucceed() == 1)
                {
                    length = productBean.getTotal() + "";
                    mData = productBean.getCart();
                    isChecked = new ArrayList<>();
                    for (int i = 0; i < mData.size(); i++)
                    {
                        isChecked.add(new Item(true));
                    }
                    if (productBean.getSucceed() == 1)
                    {
                        reFreshAdapter();
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ProductBean> call, Throwable t)
            {
                dialog.dismiss();
                Toast.makeText(mContext, "获取失败,请检查网络!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getStringExtra("ok").equals("ok"))
        {
            //刷新视图
            mIMyCallBack.reFreshShoppingCartFragment();
        }
    }

    private class Item
    {

        private boolean checked;

        public Item(boolean checked)
        {
            this.checked = checked;
        }
    }

    private class CartListViewAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public Object getItem(int i)
        {
            return mData.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return i;
        }

        @Override
        public void notifyDataSetChanged()
        {
            super.notifyDataSetChanged();
            mHandler.sendEmptyMessage(1);
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup)
        {
            View itemView = view;
            ViewHolder holder = null;
            if (itemView == null)
            {
                itemView = LayoutInflater.from(mContext).inflate(R.layout.shoppingcart_lv_item, viewGroup, false);
                holder = new ViewHolder(itemView);
            }
            else
            {
                holder = (ViewHolder) itemView.getTag(R.id.holder);
            }
            ProductBean.CartBean cartBean = mData.get(i);
            if (isChecked.get(i).checked)
            {
                totalMoney += Float.parseFloat(cartBean.getPrice()) * Float.parseFloat(cartBean.getNum());
                totalKinds += 1;
                holder.mCartItemCheckedCb.setChecked(true);
            }
            itemView.setTag(R.id.item_position, cartBean);
            Glide.with(mContext).load(cartBean.getPic()).into(holder.mCartItemPicIv);
            holder.mCartItemTitleTv.setText(cartBean.getPname());
            holder.mCartItemPriceTv.setText("现价" + cartBean.getPrice());
            holder.mCartItemOldpriceTv.setText("原价" + cartBean.getMarket_price());
            holder.mCartItemNumTv.setText(cartBean.getNum() + "");
            holder.mCartItemShowNumTv.setText("数量 : " + cartBean.getNum());
            holder.mCartItemNumaddIv.setTag(i);
            holder.mCartItemNumdecIv.setTag(i);
            holder.mCartItemDeleteIv.setTag(i);
            holder.mCartItemCheckedCb.setTag(i);
            holder.mCartItemNumaddIv.setOnClickListener(mlistener);
            holder.mCartItemNumdecIv.setOnClickListener(mlistener);
            holder.mCartItemDeleteIv.setOnClickListener(mlistener);
            holder.mCartItemCheckedCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b)
                {
                    int position = (Integer) compoundButton.getTag();
                    isChecked.get(position).checked = b;
                    ProductBean.CartBean bean = mData.get(position);
                    if (b)
                    {
                        totalKinds -= 1;
                        totalMoney -= Float.parseFloat(bean.getPrice()) * Float.parseFloat(bean.getNum());
                    }
                    else
                    {
                        totalKinds += 1;
                        totalMoney += Float.parseFloat(bean.getPrice()) * Float.parseFloat(bean.getNum());
                    }
                    reFreshAdapter();
                }
            });
            if (isEdit)
            {
                holder.mCartItemDeleteIv.setVisibility(View.VISIBLE);
                holder.mCartItemNumchangeRl.setVisibility(View.VISIBLE);
                holder.mCartItemShowNumTv.setVisibility(View.GONE);
                mShoppingcartLoginedEditCb.setText("完成");
            }
            else
            {
                holder.mCartItemDeleteIv.setVisibility(View.GONE);
                holder.mCartItemNumchangeRl.setVisibility(View.GONE);
                holder.mCartItemShowNumTv.setVisibility(View.VISIBLE);
                mShoppingcartLoginedEditCb.setText("编辑");
            }
            return itemView;
        }
    }

    private void reFreshAdapter()
    {
        totalMoney = 0;
        totalKinds = 0;
        mCartListViewAdapter.notifyDataSetChanged();
    }

    class ViewHolder
    {

        @BindView(R.id.cart_item_checked_cb)
        CheckBox mCartItemCheckedCb;
        @BindView(R.id.cart_item_pic_iv)
        ImageView mCartItemPicIv;
        @BindView(R.id.cart_item_title_tv)
        TextView mCartItemTitleTv;
        @BindView(R.id.cart_item_price_tv)
        TextView mCartItemPriceTv;
        @BindView(R.id.cart_item_oldprice_tv)
        TextView mCartItemOldpriceTv;
        @BindView(R.id.cart_item_price_layout)
        RelativeLayout mCartItemPriceLayout;
        @BindView(R.id.cart_item_numadd_iv)
        ImageView mCartItemNumaddIv;
        @BindView(R.id.cart_item_num_tv)
        TextView mCartItemNumTv;
        @BindView(R.id.cart_item_numdec_iv)
        ImageView mCartItemNumdecIv;
        @BindView(R.id.cart_item_numchange_rl)
        RelativeLayout mCartItemNumchangeRl;
        @BindView(R.id.cart_item_show_num_tv)
        TextView mCartItemShowNumTv;
        @BindView(R.id.cart_item_delete_iv)
        ImageView mCartItemDeleteIv;

        ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
            view.setTag(R.id.holder, this);
        }
    }
}
