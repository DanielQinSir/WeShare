package com.example.weshare.club_module;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weshare.R;

public class ForumFragmen extends Fragment
{
    private Context mContext;

    public static ForumFragmen newInstance()
    {
        return new ForumFragmen();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        TextView textView = new TextView(mContext);
        textView.setText("没有登录,请先登录吧!");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

}
