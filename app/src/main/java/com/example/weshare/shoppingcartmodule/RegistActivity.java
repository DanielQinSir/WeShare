package com.example.weshare.shoppingcartmodule;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weshare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistActivity extends AppCompatActivity
{

    @BindView(R.id.regist_bar_back_iv)
    ImageView mRegistBarBackIv;
    @BindView(R.id.regist_step1_tv)
    TextView mRegistStep1Tv;
    @BindView(R.id.regist_step2_tv)
    TextView mRegistStep2Tv;
    @BindView(R.id.regist_step3_tv)
    TextView mRegistStep3Tv;
    @BindView(R.id.regist_input_et)
    EditText mRegistInputEt;
    @BindView(R.id.regist_next_btn)
    Button mRegistNextBtn;
    @BindView(R.id.regist_agreement_tv)
    TextView mRegistAgreementTv;
    @BindView(R.id.regist_again_btn)
    Button mRegistAgainBtn;
    private int step = 1;
    private View.OnClickListener mlistener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.regist_bar_back_iv:
                    finish();
                    break;
                case R.id.regist_agreement_tv:
                    Toast.makeText(RegistActivity.this, "你还想看用户协议吗?\n没什么好看的啊!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    switch (step)
                    {
                        case 1:
                            mRegistStep1Tv.setTextColor(Color.BLACK);
                            mRegistStep2Tv.setTextColor(Color.YELLOW);
                            mRegistNextBtn.setText("下一步");
                            mRegistInputEt.setHint("输入验证码");
                            mRegistAgainBtn.setVisibility(View.VISIBLE);
                            step = 2;
                            break;
                        case 2:
                            mRegistStep1Tv.setTextColor(Color.BLACK);
                            mRegistStep2Tv.setTextColor(Color.BLACK);
                            mRegistStep3Tv.setTextColor(Color.YELLOW);
                            mRegistNextBtn.setText("完成注册");
                            mRegistInputEt.setHint("输入密码");
                            mRegistAgainBtn.setVisibility(View.GONE);
                            step = 3;
                            break;
                        case 3:
                            break;
                    }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView()
    {
        mRegistBarBackIv.setOnClickListener(mlistener);
        mRegistNextBtn.setOnClickListener(mlistener);
        mRegistAgreementTv.setOnClickListener(mlistener);
    }
}
