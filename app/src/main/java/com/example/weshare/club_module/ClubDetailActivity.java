package com.example.weshare.club_module;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weshare.R;
import com.example.weshare.utils.HttpServiceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClubDetailActivity extends AppCompatActivity
{

    @BindView(R.id.club_fragment_bar_mine_iv)
    ImageView mClubFragmentBarMineIv;
    @BindView(R.id.club_detail_title_tv)
    TextView mClubDetailTitleTv;
    @BindView(R.id.club_detail_content_wv)
    WebView mClubDetailContentWv;
    private String id;
    private String key = "type";
    private String url = "http://www.xxiang365.com/mobile/control/forum/detail.php?" + key + "=" + id + "&sid=" + HttpServiceUtil.SID;
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
            mClubDetailTitleTv.setText("官方通告");
                break;
            case "2":
                mClubDetailTitleTv.setText("show菜");
                break;
            case "8":
                mClubDetailTitleTv.setText("线下活动");
                break;
            case "7":
                mClubDetailTitleTv.setText("吐槽吧");
                break;
            default:
                mClubDetailTitleTv.setText("主题帖");
                key = "id";
                break;
        }

    }
}
