package com.example.weshare.welcomemodule;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weshare.MainActivity;
import com.example.weshare.R;
import com.example.weshare.databean.LocationBean;
import com.example.weshare.shoppingcartmodule.LoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends AppCompatActivity
{
    @BindView(R.id.location_next_tv)
    TextView locationNextTv;
    @BindView(R.id.location_depart_tv)
    TextView locationDepartTv;
    @BindView(R.id.location_chooseaddress_RL)
    RelativeLayout locationChooseaddressRL;
    @BindView(R.id.location_register_btn)
    Button locationRegisterBtn;
    @BindView(R.id.location_street_tv)
    TextView locationStreetTv;
    @BindView(R.id.location_address_tv)
    TextView locationAddressTv;

    private String selectedArea, selectedStreet, selectedZone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        initView();
    }

    private void initView()
    {
        locationNextTv.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToActivity(MainActivity.class);
                finish();
            }
        });
        locationRegisterBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (locationAddressTv.getText().toString().equals("请选择"))
                {
                    Toast.makeText(LocationActivity.this, "请先选择您所在的小区!", Toast.LENGTH_SHORT).show();
                    return;
                }
                goToActivity(LoginActivity.class);
            }
        });
        locationChooseaddressRL.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivityForResult(new Intent(LocationActivity.this, SelectAdressActivity.class), 1);
            }
        });
    }

    private void goToActivity(Class clazz)
    {
        startActivity(new Intent(LocationActivity.this, clazz));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            LocationBean.ZoneBean selected = (LocationBean.ZoneBean) data.getSerializableExtra("selected");
            selectedZone = selected.getName();
            String streetID = selected.getIndex();
            List<LocationBean.StreetBean> streets = SelectAdressActivity.mData.getStreet();
            String areaID = null;
            for (LocationBean.StreetBean street : streets)
            {
                if (street.getId().equals(streetID))
                {
                    selectedStreet = street.getName();
                    areaID = street.getIndex();
                    break;
                }
            }
            List<LocationBean.AreaBean> areas = SelectAdressActivity.mData.getArea();
            for (LocationBean.AreaBean area : areas)
            {
                if (area.getId().equals(areaID))
                {
                    selectedArea = area.getName();
                    break;
                }
            }
            locationDepartTv.setText(selectedArea);
            locationStreetTv.setText(selectedStreet);
            locationAddressTv.setText(selectedZone);
            SharedPreferences spf = getSharedPreferences("location",MODE_PRIVATE);
            SharedPreferences.Editor editor = spf.edit();
            editor.putString("areaID",areaID);
            editor.putString("streetID",streetID);
            editor.putString("zoneID",selected.getId());
            editor.putString("zoneName",selected.getName());
            editor.commit();
        }
    }
}
