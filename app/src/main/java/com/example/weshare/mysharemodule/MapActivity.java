package com.example.weshare.mysharemodule;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.weshare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapActivity extends AppCompatActivity
{

    @BindView(R.id.map_bar_back_iv)
    ImageView mMapBarBackIv;
    @BindView(R.id.map_activity_mv)
    MapView mMapActivityMv;
    private BaiduMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);

        initView();
        markMap();
    }

    private void markMap()
    {
        LatLng latLng = new LatLng(31.808785, 117.242741);
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(latLng)
                .icon(bitmap);
//在地图上添加Marker，并显示
        mMap.addOverlay(option);

        TextOptions textOptions = new TextOptions();

        textOptions.fontColor(0x60ff0000)//设置字体颜色
                .text("  享享社区在这里")//文字内容
                .position(latLng)//位置
                .fontSize(33)//字体大小
                .typeface(Typeface.SERIF)//字体
                .rotate(0);//旋转

        mMap.addOverlay(textOptions);

        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(latLng)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mMap.setMapStatus(mMapStatusUpdate);
    }

    private void initView()
    {
        mMapBarBackIv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        mMap = mMapActivityMv.getMap();
        mMap.setMaxAndMinZoomLevel(19, 16);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mMapActivityMv.onDestroy();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mMapActivityMv.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mMapActivityMv.onPause();
    }
}
