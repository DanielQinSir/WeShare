package com.example.weshare.homepagemodule;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/9/7.
 */
public class CustomGridView extends GridView {
    public CustomGridView(Context context) {
        this(context,null);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpect = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpect);
    }
}
