package com.example.weshare.shoppingcart_module;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weshare.R;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ShoppingCartFragment extends Fragment{
    private Context mContext;

    public static ShoppingCartFragment newInstance() {
       return new ShoppingCartFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.shoppingcart_fragmnet,container,false);
        return view;
    }
}
