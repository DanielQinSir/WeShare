package com.example.weshare.shoppingcartmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.weshare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ShoppingCartFragment extends Fragment
{

    @BindView(R.id.shoppingcart_unlogin_login_btn)
    Button mShoppingcartUnloginLoginBtn;
    @BindView(R.id.shoppingcart_unlogin_goother_btn)
    Button mShoppingcartUnloginGootherBtn;
    @BindView(R.id.shoppingcart_logined_edit_tv)
    TextView mShoppingcartLoginedEditTv;
    @BindView(R.id.shoppingcart_logined_cart_lv)
    ListView mShoppingcartLoginedCartLv;
    @BindView(R.id.shoppingcart_logined_goother_btn)
    Button mShoppingcartLoginedGootherBtn;
    @BindView(R.id.shoppingcart_logined_emptyview)
    RelativeLayout mShoppingcartLoginedEmptyview;
    private Context mContext;
    private MyCallBack mMyCallBack;
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
                    mMyCallBack.goToFirstFragment();
                    break;
                case R.id.shoppingcart_logined_goother_btn:
                    mMyCallBack.goToFirstFragment();
                    break;
                case R.id.shoppingcart_logined_edit_tv:
                    // TODO: 2016/9/8  
                    break;
            }
        }
    };

    public void setListener(MyCallBack myCallBack)
    {
        this.mMyCallBack = myCallBack;
    }

    public interface MyCallBack
    {

        void goToFirstFragment();
    }

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
        if (LoginActivity.sUser == null)
        {
            view = inflater.inflate(R.layout.shoppingcart_fragmnet_unlogined, container, false);
            ButterKnife.bind(this, view);
            mShoppingcartUnloginLoginBtn.setOnClickListener(mlistener);
            mShoppingcartUnloginGootherBtn.setOnClickListener(mlistener);
        }
        else
        {
            view = inflater.inflate(R.layout.shoppingcart_fragmnet_logined, container, false);
            mShoppingcartLoginedGootherBtn.setOnClickListener(mlistener);
            mShoppingcartLoginedCartLv.setEmptyView(mShoppingcartLoginedEmptyview);
            mShoppingcartLoginedEditTv.setOnClickListener(mlistener);
        }

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getStringExtra("ok").equals("ok"))
        {
            // TODO: 2016/9/8
        }
    }
}
