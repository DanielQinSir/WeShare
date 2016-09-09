package com.example.weshare.homepagemodule;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RatingBar;

/**
 * Created by Administrator on 2016/9/8.
 */
public class CustomRatingBar extends RatingBar {
    public CustomRatingBar(Context context) {
        this(context,null);
    }

    public CustomRatingBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        Log.e("AAA", "onMeasure(): MyRatingBar " + w + " " + h);
        h = w / 5 - 1; // 这里需要根据实际图片宽高调整
        setMeasuredDimension(w, h);
    }
}
