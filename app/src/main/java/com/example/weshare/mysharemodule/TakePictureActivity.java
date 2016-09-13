package com.example.weshare.mysharemodule;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.weshare.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TakePictureActivity extends AppCompatActivity implements SurfaceHolder.Callback
{

    @BindView(R.id.preview_surface)
    SurfaceView mPreviewSurface;
    @BindView(R.id.take_pic_cancel_btn)
    Button mTakePicCancelBtn;
    @BindView(R.id.take_pic_save_btn)
    Button mTakePicSaveBtn;
    private SurfaceHolder mHolder;
    private Camera mCamera;
    private boolean isPreview;
    private String fileName;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews()
    {
        //1，初始化Surfaceview
        mHolder = mPreviewSurface.getHolder();
        mHolder.addCallback(this);
        mTakePicCancelBtn.setVisibility(View.GONE);
        mTakePicSaveBtn.setVisibility(View.GONE);
    }

    public void onTakePictureClick(View view)
    {
        mCamera.autoFocus(new Camera.AutoFocusCallback()
        {
            @Override
            public void onAutoFocus(boolean success, Camera camera)
            {
                if (success)
                {
                    if (camera != null)
                    {
                        //拍照
                        camera.takePicture(shutter, null, new Camera.PictureCallback()
                        {
                            @Override
                            public void onPictureTaken(byte[] data, Camera camera)
                            {
                                //对照片数据进行处理
                                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                Matrix matrix = new Matrix();//获取竖直方向照片
                                matrix.setRotate(90);
                                mBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                                camera.stopPreview();
                                mTakePicCancelBtn.setVisibility(View.VISIBLE);
                                mTakePicSaveBtn.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                }
            }
        });
    }

    //保存bitmap到磁盘
    private void saveBitmap(Bitmap bitmap)
    {
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + "pictures";
        File dir = new File(dirPath);
        if (! dir.exists())
        {
            dir.mkdir();
        }
        fileName = dir.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
        File picFile = new File(fileName);
        try
        {
            FileOutputStream outputStream = new FileOutputStream(picFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            Toast.makeText(TakePictureActivity.this, "已保存" + fileName + "!", Toast.LENGTH_SHORT).show();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        //2，打开摄像头
        mCamera = Camera.open();//打开硬件摄像头，这里导包得时候一定要注意是android.hardware.Camera
        if (mCamera == null)
        {
            int cametacount = Camera.getNumberOfCameras();

            mCamera = Camera.open(cametacount - 1);
        }
        int PreviewWidth = 0;
        int PreviewHeight = 0;
        //
        //3，设置摄像头的参数
        Camera.Parameters parameters = mCamera.getParameters();//得到摄像头的参数
        // 选择合适的预览尺寸
        List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();

        // 如果sizeList只有一个我们也没有必要做什么了，因为就他一个别无选择
        if (sizeList.size() > 1)
        {
            Iterator<Camera.Size> itor = sizeList.iterator();
            while (itor.hasNext())
            {
                Camera.Size cur = itor.next();
                if (cur.width >= PreviewWidth
                        && cur.height >= PreviewHeight)
                {
                    PreviewWidth = cur.width;
                    PreviewHeight = cur.height;
                    break;
                }
            }
        }
        //竖直拍照

        if (Integer.parseInt(Build.VERSION.SDK) >= 8)
        {
            mCamera.setDisplayOrientation(90);
        }
        else
        {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            {
                parameters.set("orientation", "portrait");
                parameters.set("rotation", 90);
            }
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
                parameters.set("orientation", "landscape");
                parameters.set("rotation", 90);
            }
        }
        parameters.setPreviewFrameRate(3);//设置每秒3帧
        parameters.setPictureFormat(PixelFormat.JPEG);//设置照片的格式
        parameters.setJpegQuality(100);//设置照片的质量
        parameters.setPreviewSize(PreviewWidth, PreviewHeight); //获得摄像区域的大小
        parameters.setPictureSize(PreviewWidth, PreviewHeight);//设置拍出来的屏幕大小
        mCamera.setParameters(parameters);
        //4,设置摄像头的预览界面
        try
        {
            mCamera.setPreviewDisplay(mHolder);//通过SurfaceView显示取景画面
            //5，开始预览
            mCamera.startPreview();//开始预览
            isPreview = true;//设置是否预览参数为真
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        if (mCamera != null)
        {
            if (isPreview)
            {//如果正在预览
                mCamera.stopPreview();
                mCamera.release();//释放摄像头资源
                isPreview = false;
            }
        }
    }

    public void onNoClick(View view)
    {
        //暂停并重新预览
        mCamera.startPreview();
        mTakePicCancelBtn.setVisibility(View.GONE);
        mTakePicSaveBtn.setVisibility(View.GONE);
    }

    public void onYesClick(View view)
    {
        saveBitmap(mBitmap);
        Intent intent = new Intent();
        intent.putExtra("pic", fileName);
        setResult(RESULT_OK, intent);
        finish();
    }

    private Camera.ShutterCallback shutter = new Camera.ShutterCallback()
    {

        @Override
        public void onShutter()
        {
            // TODO Auto-generated method stub
            // 发出提示用户的声音
            ToneGenerator tone = new ToneGenerator(AudioManager.STREAM_SYSTEM,
                    ToneGenerator.MAX_VOLUME);
            AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            int ringerMode = mAudioManager.getRingerMode();
            if ((ringerMode != AudioManager.RINGER_MODE_SILENT) && (ringerMode != AudioManager.RINGER_MODE_VIBRATE))
            {
// 静音或者震动时不发出按键声音
                tone.startTone(ToneGenerator.TONE_PROP_BEEP);
            }
            else
            {
                tone.stopTone();
                Vibrator vibrator = (Vibrator) TakePictureActivity.this
                        .getSystemService(Service.VIBRATOR_SERVICE);
                vibrator.vibrate(100);
            }
        }
    };
}
