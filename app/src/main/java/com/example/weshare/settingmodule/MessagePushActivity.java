package com.example.weshare.settingmodule;

import android.content.Intent;
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

import com.example.weshare.MyApplication;
import com.example.weshare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class MessagePushActivity extends AppCompatActivity
{

    @BindView(R.id.message_push_content_tv)
    TextView mMessagePushContentTv;
    @BindView(R.id.message_push_switch_sw)
    Switch mMessagePushSwitchSw;
    @BindView(R.id.message_push_bar_back_iv)
    ImageView mMessagePushBarBackIv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_push);
        ButterKnife.bind(this);

        mMessagePushSwitchSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b)
                {
                    Toast.makeText(MessagePushActivity.this, "接受推送消息!", Toast.LENGTH_SHORT).show();
                    mMessagePushContentTv.setText("已开启接受推送消息");
                    MyApplication.acceptMessage = 1;
                }
                else
                {
                    Toast.makeText(MessagePushActivity.this, "不接受推送消息!", Toast.LENGTH_SHORT).show();
                    mMessagePushContentTv.setText("已关闭接受推送消息");
                    MyApplication.acceptMessage = 2;
                }
            }
        });
        mMessagePushSwitchSw.setButtonDrawable(new ColorDrawable(Color.YELLOW));
        if (MyApplication.acceptMessage == 1)
        {
            mMessagePushSwitchSw.setChecked(true);
        }
        mMessagePushBarBackIv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                saveMessagePushStatue();
                finish();
            }
        });
    }

    private void saveMessagePushStatue()
    {
        Intent intent = new Intent();
        intent.putExtra("push", MyApplication.acceptMessage);
        setResult(RESULT_OK, intent);
        SharedPreferences sharedPreferences = getSharedPreferences("push", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("accept", MyApplication.acceptMessage);
        editor.commit();
        switch (MyApplication.acceptMessage)
        {
            case 1:
                if (JPushInterface.isPushStopped(MessagePushActivity.this))
                {
                    JPushInterface.resumePush(MessagePushActivity.this);//接受推送
                }
                break;
            case 2:
                JPushInterface.stopPush(MessagePushActivity.this);//停止推送
                break;
        }
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
