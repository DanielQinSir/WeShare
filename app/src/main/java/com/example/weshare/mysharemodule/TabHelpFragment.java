package com.example.weshare.mysharemodule;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.weshare.R;
import com.example.weshare.databean.HelpBean;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-08
 * Time: 11:42
 * For:
 */
public class TabHelpFragment extends Fragment
{

    @BindView(R.id.tab_help_fragment_ev)
    ExpandableListView mTabHelpFragmentEv;
    private Context mContext;
    private List<HelpBean.ListBean> mData;
    private MyExpanListviewAdapter mMyExpanListviewAdapter;

    public static TabHelpFragment newInstance()
    {
        return new TabHelpFragment();
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
        View view = inflater.inflate(R.layout.tab_help_fragment, container, false);
        ButterKnife.bind(this, view);
        initData();
        mTabHelpFragmentEv.setGroupIndicator(null);
        mMyExpanListviewAdapter = new MyExpanListviewAdapter();
        mTabHelpFragmentEv.setAdapter(mMyExpanListviewAdapter);
        mTabHelpFragmentEv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l)
            {
                return true;
            }
        });
        return view;
    }

    private void initData()
    {
        HttpServiceUtil.init().getHelp("after_service", HttpServiceUtil.SID).enqueue(new Callback<HelpBean>()
        {

            @Override
            public void onResponse(Call<HelpBean> call, Response<HelpBean> response)
            {
                HelpBean helpBean = response.body();
                if (helpBean.getSucceed() == 1)
                {
                    mData = helpBean.getList();
                    if (mMyExpanListviewAdapter != null)
                    {
                        mMyExpanListviewAdapter.notifyDataSetChanged();
                        for (int i = 0; i < mData.size(); i++)
                        {
                            mTabHelpFragmentEv.expandGroup(i);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<HelpBean> call, Throwable t)
            {

            }
        });
    }

    class MyExpanListviewAdapter extends BaseExpandableListAdapter
    {

        @Override
        public int getGroupCount()
        {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public int getChildrenCount(int i)
        {
            return mData.get(i).getNext().size();
        }

        @Override
        public Object getGroup(int i)
        {
            return mData.get(i);
        }

        @Override
        public Object getChild(int i, int i1)
        {
            return mData.get(i).getNext().get(i1);
        }

        @Override
        public long getGroupId(int i)
        {
            return i;
        }

        @Override
        public long getChildId(int i, int i1)
        {
            return i1;
        }

        @Override
        public boolean hasStableIds()
        {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup)
        {
            TextView textView = new TextView(mContext);
            textView.setTextColor(Color.GREEN);
            textView.setPadding(dip2px(20), 0, 0, 0);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setBackgroundColor(Color.GRAY);
            textView.setText(mData.get(i).getTitle());
            return textView;
        }

        private int dip2px(float dpValue)
        {
            final float scale = getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup)
        {
            View itemView = view;
            if (itemView == null)
            {
                itemView = LayoutInflater.from(mContext).inflate(R.layout.tab_help_ev_child_item, null, false);
            }
            TextView mTabHelpChildItemTitleTv = (TextView) itemView.findViewById(R.id.tab_help_child_item_title_tv);
            final TextView mTabHelpChildItemContentTv = (TextView) itemView.findViewById(R.id.tab_help_child_item_content_tv);
            CheckBox mTabHelpChildItemShowmoreCb = (CheckBox) itemView.findViewById(R.id.tab_help_child_item_showmore_cb);
            HelpBean.ListBean.NextBean nextBean = mData.get(i).getNext().get(i1);
            mTabHelpChildItemTitleTv.setText(nextBean.getName());
            mTabHelpChildItemContentTv.setText(nextBean.getNote());
            mTabHelpChildItemShowmoreCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b)
                {
                    if (b)
                    {
                        mTabHelpChildItemContentTv.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        mTabHelpChildItemContentTv.setVisibility(View.GONE);
                    }
                }
            });
            return itemView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1)
        {
            return false;
        }
    }

}
