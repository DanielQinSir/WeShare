package com.example.weshare.settingmodule;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weshare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagePushActivity extends AppCompatActivity
{

    @BindView(R.id.message_push_content_tv)
    TextView mMessagePushContentTv;
    @BindView(R.id.message_push_switch_sw)
    Switch mMessagePushSwitchSw;
    @BindView(R.id.message_push_bar_back_iv)
    ImageView mMessagePushBarBackIv;
    public static boolean acccptedPush;
    private SharedPreferences mSpf;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_push);
        ButterKnife.bind(this);

        mSpf = getSharedPreferences("push", MODE_PRIVATE);
        acccptedPush = mSpf.getBoolean("accept", false);
        mMessagePushSwitchSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b)
                {
                    Toast.makeText(MessagePushActivity.this, "接受推送消息!", Toast.LENGTH_SHORT).show();
                    mMessagePushContentTv.setText("已开启接受推送消息");
                }
                else
                {
                    Toast.makeText(MessagePushActivity.this, "不接受推送消息!", Toast.LENGTH_SHORT).show();
                    mMessagePushContentTv.setText("已关闭接受推送消息");
                }
                acccptedPush = b;
            }
        });
        mMessagePushSwitchSw.setButtonDrawable(new ColorDrawable(Color.YELLOW));
        if (acccptedPush)
        {
            mMessagePushSwitchSw.setChecked(true);
        }
        mMessagePushBarBackIv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    private void saveMessagePushStatue()
    {
        SharedPreferences.Editor editor = mSpf.edit();
        editor.putBoolean("accept", acccptedPush);
        editor.commit();
    }

    public boolean dispatchKeyEvent(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
        {
            saveMessagePushStatue();
        }
        return super.dispatchKeyEvent(event);
    }
}
