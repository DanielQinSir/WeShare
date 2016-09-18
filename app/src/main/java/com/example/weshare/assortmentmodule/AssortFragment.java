package com.example.weshare.assortmentmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weshare.R;
import com.example.weshare.databean.AssortExpandableListBean;
import com.example.weshare.homepagemodule.CustomRecyclerView;
import com.example.weshare.utils.HttpServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/5.
 */
public class AssortFragment extends Fragment {
    private static final String TAG ="androidxxx" ;
    @BindView(R.id.assort_search_btn)
    Button assortSearchBtn;
    @BindView(R.id.assort_search_exlistview)
    ExpandableListView assortSearchExlistview;

    private Context mContext;
    private AssortExpandableListViewAdapter adapter =new AssortExpandableListViewAdapter();
    private ChildRecyclerViewAdapter adapter2;
    private List<AssortExpandableListBean.ListBean.FirstBean> first_list = new ArrayList<>();
    private List<AssortExpandableListBean.ListBean.SecondBean> second_list = new ArrayList<>();
    private List<String> keys = new ArrayList<>();
    private List<String> indexs = new ArrayList<>();
    private Map<String,List<AssortExpandableListBean.ListBean.SecondBean>> assort_map = new HashMap<>();


    public static AssortFragment newInstance() {
        return new AssortFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        Log.d(TAG, "onCreate: 0");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assort_fragmnet, container, false);
        ButterKnife.bind(this, view);
        initview();
        loadDatas();
        Log.d(TAG, "onCreateView: 1");
        return view;
    }

    private void loadDatas() {

        HttpServiceUtil.init().getAssortExpandableListInfo(HttpServiceUtil.SID).enqueue(new Callback<AssortExpandableListBean>() {
            @Override
            public void onResponse(Call<AssortExpandableListBean> call, Response<AssortExpandableListBean> response) {

                first_list = response.body().getList().getFirst();
                second_list = response.body().getList().getSecond();
                if(first_list!=null){
                    for (int i = 0; i <first_list.size() ; i++) {
                        keys.add(first_list.get(i).getName());
                    }
                }
                if(first_list!=null&&second_list!=null){
                    for (int i = 0; i <second_list.size() ; i++) {
                        String index = second_list.get(i).getIndex();
                        AssortExpandableListBean.ListBean.SecondBean secondbean = second_list.get(i);
                        if(!assort_map.containsKey(index)){
                            indexs.add(index);
                            List<AssortExpandableListBean.ListBean.SecondBean> childs = new ArrayList<AssortExpandableListBean.ListBean.SecondBean>();
                            childs.add(secondbean);
                            assort_map.put(index,childs);
                        }else{
                            assort_map.get(index).add(secondbean);
                        }
                    }
                    for (int i = 0; i <keys.size(); i++) {
                        assortSearchExlistview.expandGroup(i);
                    }
                  //  assortSearchExlistview.deferNotifyDataSetChanged();
                    adapter.notifyDataSetChanged();
                   // adapter2.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<AssortExpandableListBean> call, Throwable t) {

            }
        });

    }

    private void initview() {
        clearDatas();
       // adapter = new AssortExpandableListViewAdapter();
        assortSearchExlistview.setAdapter(adapter);
        assortSearchExlistview.setGroupIndicator(null);
        assortSearchExlistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
        assortSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

    }

    private void clearDatas() {
        first_list.clear();
        second_list.clear();
        keys.clear();
        indexs.clear();
        assort_map.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: 2");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 3");
    }

    class AssortExpandableListViewAdapter extends BaseExpandableListAdapter{


        private RecyclerView child_recyclerview;
        private GridLayoutManager gridLayoutManager;

        @Override
        public int getGroupCount() {
            return first_list==null?0:first_list.size();
        }

        @Override
        public int getChildrenCount(int i) {

            return 1;
        }

        @Override
        public Object getGroup(int i) {
            return indexs.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return assort_map.get(indexs.get(i)).get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertview, ViewGroup parent) {
            View view =convertview;
            GroupViewHolder viewholder =null;
            if(convertview==null){
                view = LayoutInflater.from(mContext).inflate(R.layout.assort_exlistview_group_item,parent,false);
                viewholder = new GroupViewHolder(view);
            }else{
                viewholder = (GroupViewHolder) view.getTag();
            }
            if(keys!=null){
                viewholder.group_item_name.setText(keys.get(groupPosition));
            }
            return view;
        }
            class GroupViewHolder {

                public final TextView group_item_name;

                public GroupViewHolder(View view){
                    view.setTag(this);
                    group_item_name = (TextView) view.findViewById(R.id.assort_exlistview_group_item_name_tv);
            }
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastchild, View convertview, ViewGroup parent) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.assort_exlistview_child_item,parent,false);
            child_recyclerview = (RecyclerView) view.findViewById(R.id.assort_exlistview_child_recyclerview);

            gridLayoutManager = new GridLayoutManager(mContext,4);
            child_recyclerview.setLayoutManager(gridLayoutManager);
            adapter2 = new ChildRecyclerViewAdapter(groupPosition);
            child_recyclerview.setAdapter(adapter2);

            return view;
        }


        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
    public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.ChildViewHolder>{

        private int groupPosition;
        public ChildRecyclerViewAdapter(int groupPosition) {
            this.groupPosition = groupPosition;
        }

        @Override
        public ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.assort_exlistview_child_recyclerview_item,parent,false);
            ChildViewHolder viewholder = new ChildViewHolder(view,viewType);
            return viewholder;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(ChildViewHolder holder, int position) {
            if(first_list!=null&&second_list!=null){
                if(position==0){
                    holder.first_catid = first_list.get(groupPosition).getCatid();
                    Glide.with(mContext).load(first_list.get(groupPosition).getPicNew()).into(holder.exlistview_recyclerview_iv);
                    holder.exlistview_recyclerview_tv.setText("全部");
                }else{
                    holder.second_catid = assort_map.get(indexs.get(groupPosition)).get(position-1).getCatid();
                    Glide.with(mContext).load(assort_map.get(indexs.get(groupPosition)).get(position-1).getPic()).into(holder.exlistview_recyclerview_iv);
                    holder.exlistview_recyclerview_tv.setText(assort_map.get(indexs.get(groupPosition)).get(position-1).getName());
                }
            }

        }

        @Override
        public int getItemCount() {
            String index = indexs.get(groupPosition);
            List<AssortExpandableListBean.ListBean.SecondBean> child_list = assort_map.get(index);
            return child_list==null?0:child_list.size()+1;
        }

        class ChildViewHolder extends RecyclerView.ViewHolder {
            private String first_catid;
            private String second_catid;
            private final ImageView exlistview_recyclerview_iv;
            private final TextView exlistview_recyclerview_tv;

            public ChildViewHolder(View view, final int position) {
                super(view);
                exlistview_recyclerview_iv = (ImageView) view.findViewById(R.id.assort_exlistview_child_recyclerview_iv);
                exlistview_recyclerview_tv = (TextView) view.findViewById(R.id.assort_exlistview_child_recyclerview_tv);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(position==0){
                            Intent i = new Intent(mContext,AllGoodsActivity.class);
                            i.putExtra("catid",first_catid);
                            mContext.startActivity(i);
                        }else{
                            Intent i = new Intent(mContext,OneGoodActivity.class);
                            i.putExtra("catid",second_catid);
                            mContext.startActivity(i);
                        }
                    }
                });
            }


        }
    }
}
