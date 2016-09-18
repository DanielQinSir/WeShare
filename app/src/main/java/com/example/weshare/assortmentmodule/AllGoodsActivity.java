package com.example.weshare.assortmentmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.weshare.MainActivity;
import com.example.weshare.MyApplication;
import com.example.weshare.R;
import com.example.weshare.shoppingcartmodule.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllGoodsActivity extends AppCompatActivity {


    @BindView(R.id.assort_allgoods_back_iv)
    ImageView assortAllgoodsBackIv;
    @BindView(R.id.assort_allgoods_search_btn)
    Button assortAllgoodsSearchBtn;
    @BindView(R.id.assort_allgoods_change_iv)
    ImageView assortAllgoodsChangeIv;
    @BindView(R.id.assort_allgoods_framelayout)
    FrameLayout assortAllgoodsframelayout;
    @BindView(R.id.assort_allgoods_shoppingcart_iv)
    ImageView assortAllgoodsShoppingcartIv;
    @BindView(R.id.assort_allgoods_shoppingcart_num_iv)
    Button assortAllgoodsShoppingcartNumIv;

    private Context mContext;
    private FragmentTransaction fragmentTransaction;
    private int i=1;
    private String catid;
    private Intent intent;
    private FragmentManager mSupportFragmentManager;
    private Fragment mCurrentShowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_goods);
        ButterKnife.bind(this);
        mContext = this;
        getIntentDatas();
        initView();


    }

    private void getIntentDatas() {
        intent = getIntent();
        catid = intent.getStringExtra("catid");
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        if (mCurrentShowFragment != null) {
            fragmentTransaction.hide(mCurrentShowFragment);
        }

        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.assort_allgoods_framelayout,fragment);
            /**
             * 将Fragment添加在回退栈中
             * 参数：用来标识当前Fragment
             */
            /*if (fragment != AllGoodsListFragment.newInstance(catid)) {
                fragmentTransaction.addToBackStack("one");
            }*/

        } else {
            fragmentTransaction.show(fragment);//显示Fragment
        }
        mCurrentShowFragment = fragment;

        fragmentTransaction.commit();
    }

    private void initView() {
        mSupportFragmentManager = getSupportFragmentManager();
        showFragment(AllGoodsListFragment.newInstance(catid));


        assortAllgoodsBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        assortAllgoodsSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
        assortAllgoodsChangeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i%2==1){
                    assortAllgoodsChangeIv.setImageResource(R.drawable.product_list_2);
                    showFragment(AllGoodsGridFragment.newInstance(catid));
                }else{
                    assortAllgoodsChangeIv.setImageResource(R.drawable.product_list_1);
                    showFragment(AllGoodsListFragment.newInstance(catid));
                }
               i++;
            }
        });
        assortAllgoodsShoppingcartIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.sUser == null) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                } else {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("flag", 1);
                    startActivity(intent);
                    finish();
                }
            }
        });
        assortAllgoodsShoppingcartNumIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.sUser == null) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                } else {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("flag", 1);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
