package com.example.weshare.homepagemodule;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weshare.R;
import com.example.weshare.clubmodule.ViewPagerADFragment;
import com.example.weshare.databean.ADbean;
import com.example.weshare.databean.HomeViewPagerBean;
import com.example.weshare.databean.ProductIntroduceInfoBean;
import com.example.weshare.utils.HttpServiceUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.w3c.dom.Text;

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
public class ProductIntroduceFragment extends Fragment {
    @BindView(R.id.product_introduce_fragment_listview)
    PullToRefreshListView productIntroduceFragmentListview;
    private Context mContext;
    private IntroduceListAdapter listadapter;
    private String pid;
    private Bundle bd;
    public ProductIntroduceInfoBean introduce_bean;

   public static ProductIntroduceFragment newInstance(String pid) {

       Bundle args = new Bundle();
       args.putString("pid",pid);
       ProductIntroduceFragment fragment = new ProductIntroduceFragment();
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
        View view = inflater.inflate(R.layout.product_introduce_fragment, container, false);
        ButterKnife.bind(this, view);
        initView();
        loadDatas();

        return view;
    }

    private void loadDatas() {
        HttpServiceUtil.init().getIntroduceInfo(pid,HttpServiceUtil.SID).enqueue(new Callback<ProductIntroduceInfoBean>() {
            @Override
            public void onResponse(Call<ProductIntroduceInfoBean> call, Response<ProductIntroduceInfoBean> response) {
                introduce_bean = response.body();
                listadapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ProductIntroduceInfoBean> call, Throwable t) {

            }
        });
    }

    private void initView() {
        listadapter = new IntroduceListAdapter();
        productIntroduceFragmentListview.setAdapter(listadapter);
        productIntroduceFragmentListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    class IntroduceListAdapter extends BaseAdapter{

        private TextView collection_tv;
        private TextView product_name_tv;
        private TextView product_content_tv;
        private TextView product_price_tv;
        private TextView produc_marketprice_tv;
        private TextView product_size_tv;
        private TextView product_address_tv;
        private TextView product_num_tv;
        private TextView product_rule_tv;

        @Override
        public int getCount() {
            return 1;
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
            View view = LayoutInflater.from(mContext).inflate(R.layout.product_introduce_fragment_listview_item,parent,false);
            collection_tv = (TextView) view.findViewById(R.id.product_fragment_listview_item_collection);
            product_name_tv = (TextView) view.findViewById(R.id.product_fragment_listview_item_name);
            product_content_tv = (TextView) view.findViewById(R.id.product_fragment_listview_item_content);
            product_price_tv = (TextView) view.findViewById(R.id.product_fragment_listview_item_price);
            produc_marketprice_tv = (TextView) view.findViewById(R.id.product_fragment_listview_item_marketprice);
            product_size_tv = (TextView) view.findViewById(R.id.product_fragment_listview_item_size);
            product_address_tv = (TextView) view.findViewById(R.id.product_fragment_listview_item_address);
            product_num_tv = (TextView) view.findViewById(R.id.product_fragment_listview_item_num);
            product_rule_tv = (TextView) view.findViewById(R.id.product_fragment_listview_item_rule);
            collection_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO
                }
            });
            if(introduce_bean!=null){
                product_name_tv.setText(introduce_bean.getName());
                product_content_tv.setText(introduce_bean.getDesc());
                product_price_tv.setText("¥"+introduce_bean.getPrice());
                produc_marketprice_tv.setText("¥"+introduce_bean.getMarket_price());
                product_size_tv.setText(introduce_bean.getWeight());
                product_address_tv.setText(introduce_bean.getMember_name());
                product_num_tv.setText(introduce_bean.getSales());
                product_rule_tv.setText(introduce_bean.getDelivery());
                initAd();
            }

            return view;
        }
        private void initAd()
        {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            List<ADbean> ads = new ArrayList<>();

            for (int i = 0; i <introduce_bean.getPic_more().size() ; i++) {
                String pic_url = introduce_bean.getPic_more().get(i);
                String id = "";
                ads.add(new ADbean(pic_url,id));
            }
            fragmentTransaction.replace(R.id.product_fragment_listview_item_framelayout, ViewPagerADFragment.newInstance(ads));
            fragmentTransaction.commit();
        }
    }
}
