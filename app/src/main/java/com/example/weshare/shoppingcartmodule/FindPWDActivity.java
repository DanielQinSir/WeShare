package com.example.weshare.shoppingcartmodule;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weshare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindPWDActivity extends AppCompatActivity
{

    @BindView(R.id.findpwd_bar_back_iv)
    ImageView mFindpwdBarBackIv;
    @BindView(R.id.findpwd_phone_et)
    EditText mFindpwdPhoneEt;
    @BindView(R.id.findpwd_getcheckword_btn)
    Button mFindpwdGetcheckwordBtn;
    @BindView(R.id.findpwd_checkword_et)
    EditText mFindpwdCheckwordEt;
    @BindView(R.id.findpwd_next_btn)
    Button mFindpwdNextBtn;
    private int countdown = 59;
    private Handler mHandler = new Handler(new Handler.Callback()
    {
        @Override
        public boolean handleMessage(Message message)
        {
            if (countdown > 0)
            {
                mFindpwdGetcheckwordBtn.setText(countdown-- + "秒后可重新获取");
                mHandler.sendEmptyMessageDelayed(1, 1000);
            }
            else
            {
                mFindpwdGetcheckwordBtn.setText("重新获取验证码");
                mFindpwdGetcheckwordBtn.setEnabled(true);
                mFindpwdGetcheckwordBtn.setBackgroundColor(Color.YELLOW);
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
                case R.id.findpwd_bar_back_iv:
                    finish();
                    break;
                case R.id.findpwd_getcheckword_btn:
                    String phone = mFindpwdPhoneEt.getText().toString().trim();
                    if (TextUtils.isEmpty(phone) || phone.length() != 11)
                    {
                        Toast.makeText(FindPWDActivity.this, "请输入正确的手机号码!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mFindpwdGetcheckwordBtn.setBackgroundColor(Color.GRAY);
                    mFindpwdGetcheckwordBtn.setEnabled(false);
                    mHandler.sendEmptyMessage(1);
                    break;
                case R.id.findpwd_next_btn:
                    String checkword = mFindpwdCheckwordEt.getText().toString().trim();
                    if (TextUtils.isEmpty(checkword) || checkword.length() != 6)
                    {
                        Toast.makeText(FindPWDActivity.this, "验证失败!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(FindPWDActivity.this, "验证通过!密码已发送至您的手机!", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pwd);
        ButterKnife.bind(this);

        initView();
    }

    private void initView()
    {
        mFindpwdBarBackIv.setOnClickListener(mlistener);
        mFindpwdGetcheckwordBtn.setOnClickListener(mlistener);
        mFindpwdNextBtn.setOnClickListener(mlistener);
    }
}
