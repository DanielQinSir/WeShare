package com.example.weshare.mysharemodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.weshare.MainActivity;
import com.example.weshare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-10
 * Time: 10:18
 * For:
 */

public class AllOrderFragment extends Fragment
{

    @BindView(R.id.allorder_lv)
    ListView mAllorderLv;
    @BindView(R.id.allorder_empty_btn)
    Button mAllorderEmptyBtn;
    @BindView(R.id.allorder_empty_rl)
    RelativeLayout mAllorderEmptyRl;
    private Context mContext;

    public static AllOrderFragment newInstance()
    {
        return new AllOrderFragment();
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
        View view = inflater.inflate(R.layout.allorder_fragment, container, false);
        ButterKnife.bind(this, view);
        mAllorderLv.setEmptyView(mAllorderEmptyRl);
        mAllorderEmptyBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(mContext, MainActivity.class));
            }
        });
        return view;
    }
}
