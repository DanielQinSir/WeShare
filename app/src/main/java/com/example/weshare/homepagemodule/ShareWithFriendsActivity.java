package com.example.weshare.homepagemodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weshare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ShareWithFriendsActivity extends AppCompatActivity
{

    @BindView(R.id.sharewithfriends_bar_back_iv)
    ImageView mSharewithfriendsBarBackIv;
    @BindView(R.id.sharewithfriends_share_btn)
    Button mSharewithfriendsShareBtn;
    @BindView(R.id.sharewithfriends_rlues_title_cb)
    CheckBox mSharewithfriendsRluesTitleCb;
    @BindView(R.id.sharewithfriends_rlues_content_tv)
    TextView mSharewithfriendsRluesContentTv;
    private View.OnClickListener mListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.sharewithfriends_bar_back_iv:
                    finish();
                    break;
                case R.id.sharewithfriends_share_btn:
                    showShare();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_with_friends);
        ButterKnife.bind(this);
        initView();
    }

    private void initView()
    {
        mSharewithfriendsBarBackIv.setOnClickListener(mListener);
        mSharewithfriendsRluesTitleCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b)
                {
                    mSharewithfriendsRluesContentTv.setVisibility(View.VISIBLE);
                }
                else
                {
                    mSharewithfriendsRluesContentTv.setVisibility(View.GONE);
                }
            }
        });
        mSharewithfriendsShareBtn.setOnClickListener(mListener);
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://user.qzone.qq.com/406827469");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("今天好开心啊!");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://p1.so.qhmsg.com/bdr/_240_/t0158cec82cc27c8eb1.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://user.qzone.qq.com/406827469");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("今天真的好开心~...");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://user.qzone.qq.com/406827469");

// 启动分享GUI
        oks.show(this);
    }

}
