package com.example.weshare.shoppingcartmodule;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weshare.R;
import com.example.weshare.utils.HttpServiceUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private int countdown = 59;
    private String phone;
    private Handler mHandler = new Handler(new Handler.Callback()
    {
        @Override
        public boolean handleMessage(Message message)
        {
            if (countdown > 0)
            {
                mRegistAgainBtn.setText(countdown-- + "秒后重试");
                mHandler.sendEmptyMessageDelayed(1, 1000);
            }
            else
            {
                mRegistAgainBtn.setText("重新获取验证码");
                mRegistAgainBtn.setBackgroundColor(Color.YELLOW);
                mRegistAgainBtn.setEnabled(true);
                countdown = 59;
                mRegistAgainBtn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        final ProgressDialog dialog = ProgressDialog.show(RegistActivity.this, "联网中", "正在重新请求验证码...");
                        HttpServiceUtil.init().getCheckWordAgain(phone, HttpServiceUtil.SID).enqueue(new Callback<ResponseBody>()
                        {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                            {
                                try
                                {
                                    if (response.body().string().contains("1"))
                                    {
                                        Toast.makeText(RegistActivity.this, "验证码已下发!", Toast.LENGTH_SHORT).show();
                                        mRegistAgainBtn.setBackgroundColor(Color.GRAY);
                                        mRegistAgainBtn.setEnabled(false);
                                        mHandler.sendEmptyMessage(1);
                                    }
                                }
                                catch (IOException e)
                                {
                                    Toast.makeText(RegistActivity.this, "请求失败!", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t)
                            {
                                Toast.makeText(RegistActivity.this, "网络错误!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }
                });
            }
            return true;
        }
    });
    protected View.OnClickListener mlistener = new View.OnClickListener()
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
                            phone = mRegistInputEt.getText().toString().trim();
                            if (TextUtils.isEmpty(phone) || phone.length() != 11 || ! phone.matches("1[3|5|7|8|][0-9]{9}"))
                            {
                                Toast.makeText(RegistActivity.this, "请输入正确的手机号码!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            final ProgressDialog dialog = ProgressDialog.show(RegistActivity.this, "联网中", "正在请求验证码...");
                            HttpServiceUtil.init().getCheckWord(HttpServiceUtil.SID, phone).enqueue(new Callback<ResponseBody>()
                            {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                                {
                                    try
                                    {
                                        if (response.body().string().contains("1"))
                                        {
                                            Toast.makeText(RegistActivity.this, "验证码已下发!", Toast.LENGTH_SHORT).show();
                                            mRegistStep1Tv.setTextColor(Color.BLACK);
                                            mRegistStep2Tv.setTextColor(Color.YELLOW);
                                            mRegistNextBtn.setText("下一步");
                                            mRegistInputEt.setText(null);
                                            mRegistInputEt.setHint("输入验证码");
                                            mRegistAgainBtn.setVisibility(View.VISIBLE);
                                            mRegistAgainBtn.setBackgroundColor(Color.GRAY);
                                            mRegistAgainBtn.setEnabled(false);
                                            mHandler.sendEmptyMessage(1);
                                            step = 2;
                                        }
                                    }
                                    catch (IOException e)
                                    {
                                        Toast.makeText(RegistActivity.this, "请求失败!", Toast.LENGTH_SHORT).show();
                                    }
                                    dialog.dismiss();
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t)
                                {
                                    Toast.makeText(RegistActivity.this, "网络错误!", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            });
                            break;
                        case 2:
                            String checkword = mRegistInputEt.getText().toString().trim();
                            if (TextUtils.isEmpty(checkword) || checkword.length() != 6)
                            {
                                Toast.makeText(RegistActivity.this, "请输入正确的验证码!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            final ProgressDialog checkdialog = ProgressDialog.show(RegistActivity.this, "联网中", "正在验证验证码...");
                            HttpServiceUtil.init().verifyCheckword(HttpServiceUtil.SID, checkword).enqueue(new Callback<ResponseBody>()
                            {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                                {
                                    try
                                    {
                                        if (response.body().string().contains("error"))
                                        {
                                            Toast.makeText(RegistActivity.this, "验证码错误,请重试!", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        Toast.makeText(RegistActivity.this, "验证通过!", Toast.LENGTH_SHORT).show();
                                        mRegistStep1Tv.setTextColor(Color.BLACK);
                                        mRegistStep2Tv.setTextColor(Color.BLACK);
                                        mRegistStep3Tv.setTextColor(Color.YELLOW);
                                        mRegistNextBtn.setText("完成注册");
                                        mRegistInputEt.setText(null);
                                        mRegistInputEt.setHint("输入密码");
                                        mRegistAgainBtn.setVisibility(View.GONE);
                                        step = 3;
                                    }
                                    catch (IOException e)
                                    {
                                        e.printStackTrace();
                                    }
                                    checkdialog.dismiss();
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t)
                                {
                                    Toast.makeText(RegistActivity.this, "网络错误,请重试!", Toast.LENGTH_SHORT).show();
                                    checkdialog.dismiss();
                                    return;
                                }
                            });
                            break;
                        case 3:
                            final String pwd = mRegistInputEt.getText().toString().trim();
                            if (TextUtils.isEmpty(pwd))
                            {
                                Toast.makeText(RegistActivity.this, "密码不能为空!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            final ProgressDialog pwddialog = ProgressDialog.show(RegistActivity.this, "联网中", "正在生成账户...");
                            HttpServiceUtil.init().setPWD(pwd, "", HttpServiceUtil.SID).enqueue(new Callback<ResponseBody>()
                            {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                                {
                                    try
                                    {
                                        if (response.body().string().contains("会员"))
                                        {
                                            Toast.makeText(RegistActivity.this, "注册成功!", Toast.LENGTH_SHORT).show();
                                            HttpServiceUtil.init().newAccount(HttpServiceUtil.SID).enqueue(new Callback<ResponseBody>()
                                            {
                                                @Override
                                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                                                {
                                                    try
                                                    {
                                                        String result = response.body().string();
                                                        Toast.makeText(RegistActivity.this, result, Toast.LENGTH_SHORT).show();
                                                        Log.d("url", "onResponse: " + result);
                                                        finish();
                                                    }
                                                    catch (IOException e)
                                                    {
                                                        e.printStackTrace();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ResponseBody> call, Throwable t)
                                                {

                                                }
                                            });
                                        }
                                        pwddialog.dismiss();
                                    }
                                    catch (IOException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t)
                                {
                                    Toast.makeText(RegistActivity.this, "网络错误,请重试!", Toast.LENGTH_SHORT).show();
                                    pwddialog.dismiss();
                                    return;
                                }
                            });
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
