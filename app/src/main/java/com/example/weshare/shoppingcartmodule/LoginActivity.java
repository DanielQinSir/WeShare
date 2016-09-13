package com.example.weshare.shoppingcartmodule;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weshare.MainActivity;
import com.example.weshare.MyApplication;
import com.example.weshare.R;
import com.example.weshare.databean.UserBean;
import com.example.weshare.utils.HttpServiceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity
{

    @BindView(R.id.login_username_et)
    EditText mLoginUsernameEt;
    @BindView(R.id.login_pwd_et)
    EditText mLoginPwdEt;
    @BindView(R.id.login_lonin_btn)
    Button mLoginLoninBtn;
    @BindView(R.id.login_gotoregist_tv)
    TextView mLoginGotoregistTv;
    @BindView(R.id.login_findpwd_tv)
    TextView mLoginFindpwdTv;

    private View.OnClickListener mlistener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.login_gotoregist_tv:
                    gotoActivity(RegistActivity.class);
                    break;
                case R.id.login_lonin_btn:
                    String username = mLoginUsernameEt.getText().toString().trim();
                    String pwd = mLoginPwdEt.getText().toString().trim();
                    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd))
                    {
                        Toast.makeText(LoginActivity.this, "请输入账号和密码后再登录!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "登录中", "正在登录,请稍后...");

                    HttpServiceUtil.init().login(username, pwd, "").enqueue(new Callback<UserBean>()
                    {

                        @Override
                        public void onResponse(Call<UserBean> call, Response<UserBean> response)
                        {
                            MyApplication.sUser = response.body();
                            dialog.dismiss();
                            if (MyApplication.sUser.getSucceed() == 0)
                            {
                                Toast.makeText(LoginActivity.this, "登录失败,请检查账号和密码后再试!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "登陆成功!\n欢迎您" + MyApplication.sUser.getUsername() + "!", Toast.LENGTH_SHORT).show();
                                if (MyApplication.acceptMessage == 0)
                                {
                                    gotoActivity(MainActivity.class);
                                }
                                else
                                {
                                    Intent intent = new Intent();
                                    intent.putExtra("ok", "ok");
                                    setResult(RESULT_OK, intent);
                                }
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserBean> call, Throwable t)
                        {
                            dialog.dismiss();
                            Toast.makeText(LoginActivity.this, "网络连接失败,请重试!", Toast.LENGTH_SHORT).show();

                        }
                    });
                    break;
                case R.id.login_findpwd_tv:
                    gotoActivity(FindPWDActivity.class);
                    break;
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
        mLoginGotoregistTv.setOnClickListener(mlistener);
        mLoginLoninBtn.setOnClickListener(mlistener);
        mLoginFindpwdTv.setOnClickListener(mlistener);
    }

    private void gotoActivity(Class clazz)
    {
        startActivity(new Intent(LoginActivity.this, clazz));
    }
}
