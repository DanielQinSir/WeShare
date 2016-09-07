package com.example.weshare.club_module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weshare.R;
import com.example.weshare.databean.ADbean;
import com.example.weshare.databean.ClubBean;
import com.example.weshare.utils.HttpServiceUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ClubFragment extends Fragment
{

    @BindView(R.id.club_fragment_bar_mine_iv)
    ImageView mClubFragmentBarMineIv;
    @BindView(R.id.club_fragment_listview_lv)
    ListView mClubFragmentListviewLv;
    private Button mClubMyPostBtn;
    private Button mClubReplayMeBtn;
    private Context mContext;
    private ClubBean mData;
    private ClubListViewAdapter mClubListViewAdapter;
    private PopupWindow mPopWindow;

    public static ClubFragment newInstance()
    {
        return new ClubFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.club_fragmnet, container, false);
        ButterKnife.bind(this, view);
        loadData();
        mClubFragmentBarMineIv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showPubMenu();
            }
        });
        mClubListViewAdapter = new ClubListViewAdapter();
        mClubFragmentListviewLv.setAdapter(mClubListViewAdapter);
        return view;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Intent intent = new Intent(mContext, ForumActivity.class);
            switch (view.getId())
            {
                case R.id.club_my_post_btn:
                    intent.putExtra("tag", 1);
                    break;
                case R.id.club_replay_me_btn:
                    intent.putExtra("tag", 2);
                    break;
            }
            mPopWindow.dismiss();
            startActivity(intent);
        }
    };

    private void showPubMenu()
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.club_popupmenu, null, false);
        mClubMyPostBtn = (Button) view.findViewById(R.id.club_my_post_btn);
        mClubReplayMeBtn = (Button) view.findViewById(R.id.club_replay_me_btn);
        mClubMyPostBtn.setOnClickListener(mOnClickListener);
        mClubReplayMeBtn.setOnClickListener(mOnClickListener);
// Focusable 为True，PopupWindow的点击事件才会相应
        mPopWindow = new PopupWindow(view, 150, 120, true);
//// 必须在代码中设置一下背景色，点击外面不会隐藏此弹窗
//        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
// Focusable 为False时，不执行则点击外面不会隐藏此弹窗
        mPopWindow.setOutsideTouchable(true);

// 显示 默认显示View的正下方，可以使用重载方法设置偏移量来调整位置
        mPopWindow.showAsDropDown(mClubFragmentBarMineIv, 20, 5);
    }

    private void loadData()
    {
        HttpServiceUtil.init().getClubInfo(HttpServiceUtil.SID).enqueue(new Callback<ClubBean>()
        {

            @Override
            public void onResponse(Call<ClubBean> call, Response<ClubBean> response)
            {
                mData = response.body();
                if (mData != null)
                {
                    initAd();
                    if (mClubListViewAdapter == null)
                    {
                        mClubListViewAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ClubBean> call, Throwable t)
            {

            }
        });
    }

    private void initAd()
    {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        List<ClubBean.AdBean> adBeen = mData.getAd();
        List<ADbean> ads = new ArrayList<>();
        for (ClubBean.AdBean ad : adBeen)
        {
            String pic = ad.getPic();
            String tid = ad.getTid();
            ads.add(new ADbean(pic, tid));
        }
        fragmentTransaction.replace(R.id.club_fragment_viewpager_fm, ViewPagerADFragment.newInstance(ads));
        fragmentTransaction.commit();
    }

    private class ClubListViewAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return mData == null ? 0 : mData.getModule_num();
        }

        @Override
        public Object getItem(int i)
        {
            return null;
        }

        @Override
        public long getItemId(int i)
        {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup)
        {
            View itemView = view;
            ViewHolder holder = null;
            if (itemView == null)
            {
                itemView = LayoutInflater.from(mContext).inflate(R.layout.club_fragment_lv_item, viewGroup, false);
                holder = new ViewHolder(itemView);
            }
            else
            {
                holder = (ViewHolder) itemView.getTag();
            }
            ClubBean.ModuleBean moduleBean = mData.getModule().get(i);
            holder.mClubListviewItemTitleTv.setText(moduleBean.getModule_name());
            holder.mClubListviewItemContentTv.setText(moduleBean.getDesc());
            Glide.with(mContext).load(moduleBean.getLogo()).into(holder.mClubListviewItemHeadIv);
            holder.mClubListviewItemEnterBt.setTag(i);
            holder.mClubListviewItemEnterBt.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    String id = mData.getModule().get((Integer) view.getTag()).getId();
                    goToDetailActivity(id);
                }
            });
            return itemView;
        }

    }

    private void goToDetailActivity(String id)
    {
        Intent intent = new Intent(mContext, ClubDetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    class ViewHolder
    {

        @BindView(R.id.club_listview_item_head_iv)
        RoundedImageView mClubListviewItemHeadIv;
        @BindView(R.id.club_listview_item_enter_bt)
        Button mClubListviewItemEnterBt;
        @BindView(R.id.club_listview_item_title_tv)
        TextView mClubListviewItemTitleTv;
        @BindView(R.id.club_listview_item_content_tv)
        TextView mClubListviewItemContentTv;

        ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
