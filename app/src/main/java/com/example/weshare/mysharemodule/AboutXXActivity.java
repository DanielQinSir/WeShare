package com.example.weshare.mysharemodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.weshare.R;
import com.example.weshare.databean.AboutXXBean;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutXXActivity extends AppCompatActivity
{

    @BindView(R.id.aboutxx_bar_back_iv)
    ImageView mAboutxxBarBackIv;
    @BindView(R.id.aboutxx_ll)
    LinearLayout mAboutxxLl;
    private AboutXXBean mData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_xx);
        ButterKnife.bind(this);

        loadData();

        mAboutxxBarBackIv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    private void loadData()
    {
        HttpServiceUtil.init().aboutXX("apk_pic",HttpServiceUtil.SID).enqueue(new Callback<AboutXXBean>()
        {

            @Override
            public void onResponse(Call<AboutXXBean> call, Response<AboutXXBean> response)
            {
                mData = response.body();
                showDatas();
            }

            @Override
            public void onFailure(Call<AboutXXBean> call, Throwable t)
            {

            }
        });
    }

    private void showDatas()
    {
        List<String> pics = mData.getPic();
        for (int i = 0; i < mData.getLength(); i++)
        {
            ImageView imageView = new ImageView(this);
            Glide.with(this).load(pics.get(i)).into(imageView);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mAboutxxLl.addView(imageView);
        }
    }
}
