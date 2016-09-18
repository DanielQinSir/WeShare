package com.example.weshare.homepagemodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.weshare.R;
import com.example.weshare.databean.DailyMenuBean;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyMenuDetailActivity extends AppCompatActivity {

    @BindView(R.id.daily_menu_back_iv)
    ImageView dailyMenuBackIv;
    @BindView(R.id.daily_menu_detail_listview)
    ListView dailyMenuDetailListview;

    private Context mContext;
    private int position;
    private List<DailyMenuBean.MenuBean> menu_list = new ArrayList<>();
    private List<String> cook_list = new ArrayList<>();
    private MenuDetailListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_menu_detail);
        ButterKnife.bind(this);
        mContext = this;
        getIntentData();
        initview();
        loadDatas();
    }

    private void getIntentData() {
        Intent i = getIntent();
        position = i.getIntExtra("position",100);

    }

    private void loadDatas() {
        HttpServiceUtil.init().getDailyMenuInfo("step2_4",1,HttpServiceUtil.SID,1).enqueue(new Callback<DailyMenuBean>() {
            @Override
            public void onResponse(Call<DailyMenuBean> call, Response<DailyMenuBean> response) {
                menu_list = response.body().getMenu();
                cook_list = menu_list.get(position).getCookmethod().getPics();
                if(menu_list!=null){
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DailyMenuBean> call, Throwable t) {

            }
        });

    }

    private void initview() {
        adapter = new MenuDetailListViewAdapter();
        dailyMenuDetailListview.setAdapter(adapter);

        dailyMenuBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    class MenuDetailListViewAdapter extends BaseAdapter {

        private ImageView picture_item_iv;

        @Override
        public int getCount() {
            return cook_list == null ? 0 : cook_list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup parent) {
            View view = null;

                view = LayoutInflater.from(mContext).inflate(R.layout.picture_fragment_listview_item2, parent, false);
                picture_item_iv = (ImageView) view.findViewById(R.id.picture_fragment_listview_item_iv);
                if (menu_list != null) {
                    Glide.with(mContext).load(cook_list.get(position)).into(picture_item_iv);
                }

            return view;
        }


    }
}
