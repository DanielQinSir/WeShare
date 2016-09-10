package com.example.weshare.homepagemodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weshare.R;
import com.example.weshare.clubmodule.ViewPagerADFragment;
import com.example.weshare.databean.ADbean;
import com.example.weshare.databean.HomeListBean;
import com.example.weshare.databean.HomeRecyclerViewBean;
import com.example.weshare.databean.HomeRecyclerViewLogoBean;
import com.example.weshare.databean.HomeViewPagerBean;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/5.
 */
public class HomeFragment extends Fragment
{

    private static final String TAG = "androidxx";
    @BindView(R.id.home_listview)
    ListView home_Listview;

    private Context mContext;
    private List<Integer> pic_list = new ArrayList<>();
    private List<String> title_list = new ArrayList<>();
    private HomeRecyclerViewBean recyclerView_firstpic_been;
    private HomeRecyclerViewBean recyclerView_secondpic_been;
    private HomeRecyclerViewLogoBean recycler_logo_bean;
    private List<HomeListBean.ThemeAdBean> list_bean = new ArrayList<>();
    private List<HomeViewPagerBean.AdvsBean> viewpager_bean = new ArrayList<>();
    private GridViewAdapter gridadapter;
    private ListViewAdapter listadapter;
    private RecyclerViewAdapter recyclerviewadapter;
    private FrameLayout home_framelayout;
    private GridView home_gridview;
    private RecyclerView home_recyclerview;
    private GridLayoutManager gridLayoutManager;
    private View.OnClickListener gridViewListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            int position = (int) view.getTag();
            switch (position)
            {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4://每日签到
                    goToActivity(SignInActivity.class);
                    break;
                case 5://摇一摇
                    goToActivity(ShakeActivity.class);
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        }
    };

    public static HomeFragment newInstance()
    {
        return new HomeFragment();
    }

    private void goToActivity(Class clazz)
    {
        startActivity(new Intent(mContext, clazz));
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
        View view = inflater.inflate(R.layout.home_fragmnet, container, false);
        ButterKnife.bind(this, view);
        initHeaderView();
        loadDatas();
        notifyadapter();
        return view;
    }

    private void notifyadapter()
    {
        recyclerviewadapter.notifyDataSetChanged();
        listadapter.notifyDataSetChanged();
    }

    private void initAdapter()
    {

        gridadapter = new GridViewAdapter();
        home_gridview.setAdapter(gridadapter);

        listadapter = new ListViewAdapter();
        home_Listview.setAdapter(listadapter);

        recyclerviewadapter = new RecyclerViewAdapter();
        home_recyclerview.setAdapter(recyclerviewadapter);

    }

    private void initHeaderView()
    {
        View view2 = LayoutInflater.from(mContext).inflate(R.layout.home_headview, null, false);
        home_framelayout = (FrameLayout) view2.findViewById(R.id.home_framelayout);
        home_gridview = (GridView) view2.findViewById(R.id.home_gridview);
        home_recyclerview = (RecyclerView) view2.findViewById(R.id.home_recyclerview);
        gridLayoutManager = new GridLayoutManager(mContext, 3);
        home_recyclerview.setLayoutManager(gridLayoutManager);
        home_Listview.addHeaderView(view2);
        initAdapter();

    }

    private void initAd()
    {
        if (viewpager_bean != null)
        {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            List<ADbean> ads = new ArrayList<>();
            for (HomeViewPagerBean.AdvsBean ad : viewpager_bean)
            {
                String pic_url = ad.getPic();
                String areaid = ad.getSpecial_areaid();
                ads.add(new ADbean(pic_url, areaid));
            }
            fragmentTransaction.replace(R.id.home_framelayout, ViewPagerADFragment.newInstance(ads));
            fragmentTransaction.commit();
        }

    }

    private void loadDatas()
    {
        pic_list.clear();
        title_list.clear();

        pic_list.add(R.drawable.new_product_btn);
        pic_list.add(R.drawable.everyday_cookbook);
        pic_list.add(R.drawable.nutrition_recommend);
        pic_list.add(R.drawable.help_main);
        pic_list.add(R.drawable.register_everyday);
        pic_list.add(R.drawable.preferential);
        pic_list.add(R.drawable.invite);
        pic_list.add(R.drawable.activity_information);

        title_list.add("新品上架");
        title_list.add("每日菜谱");
        title_list.add("营养推荐");
        title_list.add("售后·帮助");
        title_list.add("每日签到");
        title_list.add("摇优惠");
        title_list.add("邀请好友");
        title_list.add("享享之旅");
        if (gridadapter != null)
        {
            gridadapter.notifyDataSetChanged();
        }

        HttpServiceUtil.init().getRecyclerviewInfo("promote278", HttpServiceUtil.SID).enqueue(new Callback<HomeRecyclerViewBean>()
        {
            @Override
            public void onResponse(Call<HomeRecyclerViewBean> call, Response<HomeRecyclerViewBean> response)
            {
                recyclerView_firstpic_been = response.body();
                if (recyclerView_firstpic_been != null)
                {
                    recyclerviewadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<HomeRecyclerViewBean> call, Throwable t)
            {

            }
        });
        HttpServiceUtil.init().getRecyclerviewInfo("logo278", HttpServiceUtil.SID).enqueue(new Callback<HomeRecyclerViewBean>()
        {
            @Override
            public void onResponse(Call<HomeRecyclerViewBean> call, Response<HomeRecyclerViewBean> response)
            {
                recyclerView_secondpic_been = response.body();
                if (recyclerView_secondpic_been != null)
                {
                    recyclerviewadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<HomeRecyclerViewBean> call, Throwable t)
            {

            }
        });
        HttpServiceUtil.init().getLogoInfo("local_sale", HttpServiceUtil.SID).enqueue(new Callback<HomeRecyclerViewLogoBean>()
        {
            @Override
            public void onResponse(Call<HomeRecyclerViewLogoBean> call, Response<HomeRecyclerViewLogoBean> response)
            {
                recycler_logo_bean = response.body();
                if (recycler_logo_bean != null)
                {
                    recyclerviewadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<HomeRecyclerViewLogoBean> call, Throwable t)
            {

            }
        });

        HttpServiceUtil.init().getListInfo("step1", HttpServiceUtil.SID).enqueue(new Callback<HomeListBean>()
        {
            @Override
            public void onResponse(Call<HomeListBean> call, Response<HomeListBean> response)
            {
                list_bean = response.body().getTheme_ad();
                if (list_bean != null)
                {
                    listadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<HomeListBean> call, Throwable t)
            {

            }
        });
        HttpServiceUtil.init().getViewpagerInfo(HttpServiceUtil.SID).enqueue(new Callback<HomeViewPagerBean>()
        {
            @Override
            public void onResponse(Call<HomeViewPagerBean> call, Response<HomeViewPagerBean> response)
            {
                viewpager_bean = response.body().getAdvs();
                initAd();
            }

            @Override
            public void onFailure(Call<HomeViewPagerBean> call, Throwable t)
            {
                Log.i(TAG, "onFailure: 333333");
            }
        });
    }

    class GridViewAdapter extends BaseAdapter
    {

        private ImageView home_gridview_item_iv;
        private TextView home_gridview_item_tv;

        @Override
        public int getCount()
        {
            return pic_list == null ? 0 : pic_list.size();
        }

        @Override
        public Object getItem(int i)
        {
            return null;
        }

        @Override
        public long getItemId(int i)
        {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            View view1 = LayoutInflater.from(mContext).inflate(R.layout.home_gridview_item, parent, false);
            home_gridview_item_iv = (ImageView) view1.findViewById(R.id.home_gridview_item_iv);
            home_gridview_item_tv = (TextView) view1.findViewById(R.id.home_gridview_item_tv);
            home_gridview_item_iv.setImageResource(pic_list.get(position));
            home_gridview_item_tv.setText(title_list.get(position));
            view1.setTag(position);
            view1.setOnClickListener(gridViewListener);
            return view1;
        }
    }

    class ListViewAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return list_bean == null ? 0 : list_bean.size();
        }

        @Override
        public Object getItem(int i)
        {
            return null;
        }

        @Override
        public long getItemId(int i)
        {
            return 0;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup parent)
        {
            View view = convertview;
            ListViewHolder viewholder = null;

            if (convertview == null)
            {
                view = LayoutInflater.from(mContext).inflate(R.layout.home_listview_item, parent, false);
                viewholder = new ListViewHolder(view);
            }
            else
            {
                viewholder = (ListViewHolder) view.getTag();
            }
            viewholder.home_listview_item_iv.setImageResource(R.drawable.default_image);
            if (list_bean != null)
            {
                viewholder.id = list_bean.get(position).getId();
                Glide.with(mContext).load(list_bean.get(position).getAdv_pic()).into(viewholder.home_listview_item_iv);
            }
            return view;
        }

        class ListViewHolder implements View.OnClickListener
        {

            public String id;
            public ImageView home_listview_item_iv;

            public ListViewHolder(View view)
            {
                view.setTag(this);
                home_listview_item_iv = (ImageView) view.findViewById(R.id.home_listview_item_iv);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(mContext, HomeListDetail.class);
                i.putExtra("id", id);
                mContext.startActivity(i);
            }
        }

    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(mContext).inflate(R.layout.home_recyclerview_item, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.home_recyclerview_item_iv1.setImageResource(R.drawable.default_image);
            if (position == 0 && recyclerView_firstpic_been != null)
            {
                Glide.with(mContext).load(recyclerView_firstpic_been.getPic()).into(holder.home_recyclerview_item_iv1);
                Glide.with(mContext).load(recyclerView_firstpic_been.getFirst_pic()).into(holder.home_recyclerview_item_iv2);
                holder.home_recyclerview_item_price_tv.setText("¥" + recyclerView_firstpic_been.getFirst_limit_price());
            }
            if (position == 1 && recyclerView_secondpic_been != null)
            {
                Glide.with(mContext).load(recyclerView_secondpic_been.getPic()).into(holder.home_recyclerview_item_iv1);
                Glide.with(mContext).load(recyclerView_secondpic_been.getFirst_pic()).into(holder.home_recyclerview_item_iv2);
                holder.home_recyclerview_item_price_tv.setText("¥" + recyclerView_secondpic_been.getFirst_limit_price());
            }
            if (position == 2 && recycler_logo_bean != null)
            {
                Glide.with(mContext).load(recycler_logo_bean.getLogo()).into(holder.home_recyclerview_item_iv1);
            }
        }

        @Override
        public int getItemCount()
        {
            return 3;
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            public final ImageView home_recyclerview_item_iv2;
            public final TextView home_recyclerview_item_price_tv;
            public final ImageView home_recyclerview_item_iv1;

            public MyViewHolder(View view)
            {
                super(view);

                home_recyclerview_item_iv1 = (ImageView) view.findViewById(R.id.home_recyclerview_item_iv1);
                home_recyclerview_item_iv2 = (ImageView) view.findViewById(R.id.home_recyclerview_item_iv2);
                home_recyclerview_item_price_tv = (TextView) view.findViewById(R.id.home_recyclerview_item_price_tv);

            }
        }
    }

}
