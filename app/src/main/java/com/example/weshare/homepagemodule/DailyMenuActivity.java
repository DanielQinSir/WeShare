package com.example.weshare.homepagemodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weshare.MainActivity;
import com.example.weshare.MyApplication;
import com.example.weshare.R;
import com.example.weshare.databean.DailyMenuBean;
import com.example.weshare.shoppingcartmodule.LoginActivity;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyMenuActivity extends AppCompatActivity {

    @BindView(R.id.daily_menu_back_iv)
    ImageView dailyMenuBackIv;
    @BindView(R.id.daily_menu_recyclerview)
    RecyclerView dailyMenuRecyclerview;
    @BindView(R.id.daily_menu_shoppingcart_iv)
    ImageView dailyMenuShoppingcartIv;
    @BindView(R.id.daily_menu_shoppingcart_num_iv)
    Button dailyMenuShoppingcartNumIv;

    private Context mContext;

    private List<DailyMenuBean.MenuBean> menu_list = new ArrayList<>();
    private List<DailyMenuBean.MenuBean.ProductBean> product_list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private MenuRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_menu);
        ButterKnife.bind(this);
        mContext = this;
        initView();
        loadDatas();

    }

    private void loadDatas() {
        HttpServiceUtil.init().getDailyMenuInfo("step2_4",1,HttpServiceUtil.SID,1).enqueue(new Callback<DailyMenuBean>() {
            @Override
            public void onResponse(Call<DailyMenuBean> call, Response<DailyMenuBean> response) {
                menu_list = response.body().getMenu();
                if(menu_list!=null){
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DailyMenuBean> call, Throwable t) {

            }
        });
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dailyMenuRecyclerview.setLayoutManager(linearLayoutManager);

        adapter = new MenuRecyclerViewAdapter();
        dailyMenuRecyclerview.setAdapter(adapter);

        dailyMenuBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dailyMenuShoppingcartIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.sUser == null) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                } else {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("flag", 1);
                    startActivity(intent);
                    finish();
                }
            }
        });
        dailyMenuShoppingcartNumIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.sUser == null) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                } else {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("flag", 1);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder>{

        @Override
        public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.daily_menu_recycler_view_item,parent,false);
            MenuViewHolder viewHolder = new MenuViewHolder(view,viewType);
            return viewHolder;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(MenuViewHolder holder, int position) {

            if(menu_list!=null){
                product_list = menu_list.get(position).getProduct();
                holder.list_item_id=menu_list.get(position).getProduct().get(0).getId();
                Glide.with(mContext).load(menu_list.get(position).getMenupic()).into(holder.menu_list_mian_iv);
                holder.menu_list_title_tv.setText(menu_list.get(position).getMenuname());
                holder.menu_list_date_tv.setText(menu_list.get(position).getTimes());
                holder.menu_list_foodnum_tv.setText("食材列表("+menu_list.get(position).getLength()+")");
                Glide.with(mContext).load(menu_list.get(position).getProduct().get(0).getPic()).into(holder.menu_list_item_iv);
                holder.nemu_list_item_name_tv.setText(menu_list.get(position).getProduct().get(0).getPname());
                holder.menu_list_ltem_price_tv.setText("¥"+menu_list.get(position).getProduct().get(0).getPrice());
                holder.menu_list_item_marketprice_tv.setText("¥"+menu_list.get(position).getProduct().get(0).getMarket_price());

            }

        }

        @Override
        public int getItemCount() {
            return menu_list==null?0:menu_list.size();
        }

        class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private int position;

            private String list_item_id;
            private final TextView menu_list_title_tv;
            private final RelativeLayout menu_RL2;
            private final TextView menu_list_display_tv;
            private final ImageView menu_list_display_iv;
            private final TextView menu_list_ltem_price_tv;
            private final TextView nemu_list_item_name_tv;
            private final TextView menu_list_item_marketprice_tv;
            private final RelativeLayout menu_RL1;
            private final CustomListView menu_item_listview;
            private final TextView menu_list_foodnum_tv;
            private final Button menu_list_buyall_btn;
            private final ImageView menu_list_item_iv;
            private final ImageView menu_list_mian_iv;
            private final TextView menu_list_date_tv;
            private MenuListAdapter adapter;

            public MenuViewHolder(View view,int position) {
                super(view);
                this.position = position;
                menu_list_mian_iv = (ImageView) view.findViewById(R.id.menu_recyclerview_item_main_iv);
                menu_list_title_tv = (TextView) view.findViewById(R.id.menu_recyclerview_item_title_tv);
                menu_list_date_tv = (TextView) view.findViewById(R.id.menu_recyclerview_item_date_tv);
                menu_list_foodnum_tv = (TextView) view.findViewById(R.id.menu_recyclerview_item_foodnum_tv);
                menu_list_buyall_btn = (Button) view.findViewById(R.id.menu_recyclerview_item_buyall_btn);
                menu_list_item_iv = (ImageView) view.findViewById(R.id.menu_recyclerview_item_list_item_iv);
                nemu_list_item_name_tv = (TextView) view.findViewById(R.id.menu_recyclerview_item_list_item_name_tv);
                menu_list_ltem_price_tv = (TextView) view.findViewById(R.id.menu_recyclerview_item_list_item_price_tv);
                menu_list_item_marketprice_tv = (TextView) view.findViewById(R.id.menu_recyclerview_item_list_item_marketprice_tv);
                menu_RL1 = (RelativeLayout) view.findViewById(R.id.menu_list_item_RL1);
                menu_RL2 = (RelativeLayout) view.findViewById(R.id.menu_list_item_RL2);
                menu_item_listview = (CustomListView) view.findViewById(R.id.menu_recyclerview_item_listview);
                menu_list_display_tv = (TextView) view.findViewById(R.id.menu_recyclerview_item_display_tv);
                menu_list_display_iv = (ImageView) view.findViewById(R.id.menu_recyclerview_item_display_iv);

                menu_list_mian_iv.setOnClickListener(this);
                menu_list_buyall_btn.setOnClickListener(this);
                menu_RL1.setOnClickListener(this);
                menu_RL2.setOnClickListener(this);

                adapter = new MenuListAdapter();
                menu_item_listview.setAdapter(adapter);
            }

            @Override
            public void onClick(View view) {

                switch(view.getId()){
                    case R.id.menu_recyclerview_item_main_iv:
                        Intent i = new Intent(mContext,DailyMenuDetailActivity.class);
                        i.putExtra("position",position);
                        startActivity(i);
                        break;
                    case R.id.menu_recyclerview_item_buyall_btn:


                        break;
                    case R.id.menu_list_item_RL1:
                        Intent i2 = new Intent(mContext, HomeListDetailTwo.class);
                        i2.putExtra("product_id", list_item_id);
                        startActivity(i2);
                        break;
                    case R.id.menu_list_item_RL2:
                        int num =1;
                        if(num%2==1){
                            menu_item_listview.setVisibility(View.VISIBLE);

                            if(product_list!=null){
                                adapter.notifyDataSetChanged();
                            }
                            menu_list_display_tv.setText("收起");
                            menu_list_display_iv.setImageResource(R.drawable.display_btn2_proc);

                        }else{
                            menu_item_listview.setVisibility(View.GONE);
                            menu_list_display_tv.setText("显示全部食材");
                            menu_list_display_iv.setImageResource(R.drawable.display_btn);
                        }
                            num++;
                        break;

                }

            }
        }
    }

    class MenuListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return product_list==null?0:product_list.size()-1;
        }

        @Override
        public Object getItem(int i) {
            return product_list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup parent) {
            View view = convertview;
            MenuListViewHolder viewholder =null;
            if(convertview==null){
                view=LayoutInflater.from(mContext).inflate(R.layout.local_sale_detail_listview_item,parent,false);
                viewholder = new MenuListViewHolder(view);
            }else{
                viewholder = (MenuListViewHolder) view.getTag();
            }
            if(product_list!=null){
                viewholder.product_id=product_list.get(position+1).getId();
                viewholder.list_item_name.setText(product_list.get(position+1).getPname());
                viewholder.list_item_price.setText("¥"+product_list.get(position+1).getPrice());
                viewholder.list_item_marketprice.setText("¥"+product_list.get(position+1).getMarket_price());
                Glide.with(mContext).load(product_list.get(position+1).getPic()).into(viewholder.list_item_iv);

            }
            return view;
        }

        class MenuListViewHolder implements View.OnClickListener{
            public String product_id;
            public final ImageView list_item_iv;
            public final TextView list_item_name;
            public final TextView list_item_price;
            public final TextView list_item_marketprice;

            public MenuListViewHolder(View view){
                view.setTag(this);
                list_item_iv = (ImageView) view.findViewById(R.id.local_sale_detail_listview_item_iv);
                list_item_name = (TextView) view.findViewById(R.id.local_sale_detail_listview_item_name_tv);
                list_item_price = (TextView) view.findViewById(R.id.local_sale_detail_listview_item_price_tv);
                list_item_marketprice = (TextView) view.findViewById(R.id.local_sale_detail_listview_item_marketprice_tv);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, HomeListDetailTwo.class);
                i.putExtra("product_id", product_id);
                startActivity(i);
            }
        }
    }
}
