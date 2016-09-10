package com.example.weshare;

import android.support.v4.app.Fragment;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-10
 * Time: 10:37
 * For:
 */

public class BaseFragment extends Fragment
{
    public IMyCallBack mIMyCallBack;

    public void setListener(IMyCallBack IMyCallBack)
    {
        this.mIMyCallBack = IMyCallBack;
    }

}
