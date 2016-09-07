package com.example.weshare.club_module;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weshare.R;
import com.example.weshare.utils.HttpServiceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClubDetailActivity extends AppCompatActivity
{

    @BindView(R.id.club_detail_bar_back_iv)
    ImageView mClubDetailBarBackIv;
    @BindView(R.id.club_detail_bar_title_tv)
    TextView mClubDetailBarTitleTv;
    @BindView(R.id.club_detail_content_wv)
    WebView mClubDetailContentWv;
    private String id;
    private String key = "index.php?type";
    private String pre_Url = "http://www.xxiang365.com/mobile/control/forum/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_detail);
        ButterKnife.bind(this);

        loadData();
        initView();
    }

    private void loadData()
    {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    private void initView()
    {
        switch (id)
        {
            case "1":
                mClubDetailBarTitleTv.setText("官方通告");
                break;
            case "2":
                mClubDetailBarTitleTv.setText("show菜");
                break;
            case "8":
                mClubDetailBarTitleTv.setText("线下活动");
                break;
            case "7":
                mClubDetailBarTitleTv.setText("吐槽吧");
                break;
            default:
                mClubDetailBarTitleTv.setText("主题帖");
                key = "detail.php?id";
                break;
        }
        mClubDetailBarBackIv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        WebViewClient client = new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        };
        mClubDetailContentWv.setWebViewClient(client);
        WebSettings settings = mClubDetailContentWv.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);//设置WebView 支持加载更多格式页面
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//WebView加载页面优先使用缓存加载
        String url = pre_Url + key + "=" + id + "&sid=" + HttpServiceUtil.SID;
        mClubDetailContentWv.loadUrl(url);
    }


}
