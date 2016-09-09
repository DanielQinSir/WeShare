package com.example.weshare.homepagemodule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.weshare.R;
import com.example.weshare.databean.ProductIntroduceInfoBean;
import com.example.weshare.utils.HttpServiceUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/8.
 */
public class PictureDetailFragment extends Fragment {
    @BindView(R.id.picture_detail_fragment_listview)
    PullToRefreshListView picture_detail_listview;
    private Context mContext;
    private Bundle bd;
    private String pid;
    private PictureListViewAdapter adapter;
    private List<String> pic_list=new ArrayList<>();

    public static PictureDetailFragment newInstance(String pid) {
        Bundle args = new Bundle();
        args.putString("pid", pid);
        PictureDetailFragment fragment = new PictureDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        bd = getArguments();
        pid = bd.getString("pid");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_detail_fragment, container, false);
        ButterKnife.bind(this,view);
        adapter = new PictureListViewAdapter();
        picture_detail_listview.setAdapter(adapter);
        loadDatas();
        return view;
    }

    private void loadDatas() {
        HttpServiceUtil.init().getIntroduceInfo(pid,HttpServiceUtil.SID).enqueue(new Callback<ProductIntroduceInfoBean>() {
            @Override
            public void onResponse(Call<ProductIntroduceInfoBean> call, Response<ProductIntroduceInfoBean> response) {
                pic_list= response.body().getPic_desc();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ProductIntroduceInfoBean> call, Throwable t) {

            }
        });
    }

    class PictureListViewAdapter extends BaseAdapter{

        private ImageView picture_item_iv;

        @Override
        public int getCount() {
            return pic_list==null?0:pic_list.size()+1;
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
            if(position==0){
                view = LayoutInflater.from(mContext).inflate(R.layout.picture_fragment_listview_item1,parent,false);
            }else{
                view = LayoutInflater.from(mContext).inflate(R.layout.picture_fragment_listview_item2,parent,false);
                picture_item_iv = (ImageView) view.findViewById(R.id.picture_fragment_listview_item_iv);
                if(pic_list!=null){
                    Glide.with(mContext).load(pic_list.get(position-1)).into(picture_item_iv);
                }
            }
            return view;
        }


    }
}
