package com.example.weshare.homepagemodule;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weshare.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity
{

    @BindView(R.id.signin_activity_calendar_cv)
    CalendarView mSigninActivityCalendarCv;
    @BindView(R.id.signin_activity_signin_btn)
    Button mSigninActivitySigninBtn;
    @BindView(R.id.signin_bar_back_iv)
    ImageView mSigninBarBackIv;
    private View.OnClickListener mListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.signin_bar_back_iv:
                    finish();
                    break;
                case R.id.signin_activity_signin_btn:
                    Toast.makeText(SignInActivity.this, "签到成功!", Toast.LENGTH_SHORT).show();
                    mSigninActivitySigninBtn.setBackgroundColor(Color.GRAY);
                    mSigninActivitySigninBtn.setEnabled(false);
                    break;
            }
        }
    };
    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        mCalendar = Calendar.getInstance();
        initView();
    }

    private void initView()
    {
        mSigninBarBackIv.setOnClickListener(mListener);
        mSigninActivitySigninBtn.setOnClickListener(mListener);
        mSigninActivityCalendarCv.setDate(System.currentTimeMillis());
        mSigninActivityCalendarCv.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                if (year != mCalendar.get(Calendar.YEAR) || month != mCalendar.get(Calendar.MONTH) || dayOfMonth != mCalendar.get(Calendar.DATE))
                {
                    String date = year + "年" + (month + 1) + "月" + dayOfMonth + "日";
                    Toast.makeText(SignInActivity.this, "您选择了" + date + ",但是只能签到今天哦!", Toast.LENGTH_LONG).show();
                    mSigninActivitySigninBtn.setBackgroundColor(Color.GRAY);
                    mSigninActivitySigninBtn.setEnabled(false);
                }
                else
                {
                    mSigninActivitySigninBtn.setBackgroundColor(Color.YELLOW);
                    mSigninActivitySigninBtn.setEnabled(true);
                }
            }
        });
    }
}
