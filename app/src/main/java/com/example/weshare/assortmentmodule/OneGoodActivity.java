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

public class OneGoodActivity extends AppCompatActivity {

    @BindView(R.id.assort_onegoods_back_iv)
    ImageView assortOnegoodsBackIv;
    @BindView(R.id.assort_onegoods_search_btn)
    Button assortOnegoodsSearchBtn;
    @BindView(R.id.assort_onegoods_change_iv)
    ImageView assortOnegoodsChangeIv;
    @BindView(R.id.assort_onegoods_framelayout)
    FrameLayout assortOnegoodsFramelayout;
    @BindView(R.id.assort_onegoods_shoppingcart_iv)
    ImageView assortOnegoodsShoppingcartIv;
    @BindView(R.id.assort_onegoods_shoppingcart_num_iv)
    Button assortOnegoodsShoppingcartNumIv;
    
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
        setContentView(R.layout.activity_one_good);
        ButterKnife.bind(this);
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


            fragmentTransaction.add(R.id.assort_onegoods_framelayout,fragment);
            /**
             * 将Fragment添加在回退栈中
             * 参数：用来标识当前Fragment
             */
           /* if (fragment != OneGoodListFragment.newInstance(catid)) {
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
        showFragment(OneGoodListFragment.newInstance(catid));


        assortOnegoodsBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        assortOnegoodsSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
        assortOnegoodsChangeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i%2==1){
                    assortOnegoodsChangeIv.setImageResource(R.drawable.product_list_2);
                    showFragment(OneGoodGridFragment.newInstance(catid));
                }else{
                    assortOnegoodsChangeIv.setImageResource(R.drawable.product_list_1);
                    showFragment(OneGoodListFragment.newInstance(catid));
                }
                i++;
            }
        });
        assortOnegoodsShoppingcartIv.setOnClickListener(new View.OnClickListener() {
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
        assortOnegoodsShoppingcartNumIv.setOnClickListener(new View.OnClickListener() {
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
