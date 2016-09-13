package com.example.weshare.homepagemodule;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weshare.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShakeActivity extends AppCompatActivity
{

    @BindView(R.id.shake_bar_back_iv)
    ImageView mShakeBarBackIv;
    @BindView(R.id.shake_content_tv)
    TextView mShakeContentTv;
    private SensorManager sensorManager;
    private Vibrator vibrator;

    private static final int SENSOR_SHAKE = 10;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        ButterKnife.bind(this);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        initView();
    }

    private void initView()
    {
        mShakeBarBackIv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (sensorManager != null)
        {// 注册监听器
            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
            // 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if (sensorManager != null)
        {// 取消监听器
            sensorManager.unregisterListener(sensorEventListener);
        }
    }

    /**
     * 重力感应监听
     */
    private SensorEventListener sensorEventListener = new SensorEventListener()
    {

        @Override
        public void onSensorChanged(SensorEvent event)
        {
            // 传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[ 0 ]; // x轴方向的重力加速度，向右为正
            float y = values[ 1 ]; // y轴方向的重力加速度，向前为正
            float z = values[ 2 ]; // z轴方向的重力加速度，向上为正
            // 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
            int medumValue = 18;// 三星 i9250怎么晃都不会超过20，没办法，只设置19了
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue)
            {
                sensorManager.unregisterListener(sensorEventListener);
                Toast.makeText(ShakeActivity.this, "x轴方向的重力加速度" + x + "；y轴方向的重力加速度" + y + "；z轴方向的重力加速度" + z, Toast.LENGTH_SHORT).show();
                mShakeContentTv.setText("正在搜索惊喜~");
                vibrator.vibrate(200);//振动
                Message msg = new Message();
                msg.what = SENSOR_SHAKE;
                handler.sendMessage(msg);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {

        }
    };

    /**
     * 动作执行
     */
    Handler handler = new Handler()
    {

        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case SENSOR_SHAKE:
//                    Toast.makeText(ShakeActivity.this, "检测到摇晃，执行操作！", Toast.LENGTH_SHORT).show();
                    showShakeDialog();
                    break;
            }
        }

    };

    private void showShakeDialog()
    {
        final boolean result = new Random().nextBoolean();
        new AlertDialog.Builder(ShakeActivity.this).setTitle("查找结果").setMessage(result ? "恭喜你,摇到了一个妹子!" : "很遗憾,连个恐龙都没有.").setPositiveButton(result ? "立即去前台领取" : "再试一次", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
                if (result)
                {
                    Toast.makeText(ShakeActivity.this, "成功领取妹子一枚!", Toast.LENGTH_SHORT).show();
                        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
                        // 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
                }
            }
        }).setNegativeButton("不玩了", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
                finish();
            }
        }).show();
    }
}
