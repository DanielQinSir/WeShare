package com.example.weshare.homepagemodule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.weshare.R;
import com.example.weshare.databean.UserCommentBean;
import com.example.weshare.utils.HttpServiceUtil;

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
public class UserCommentFragment extends Fragment {
    @BindView(R.id.user_comment_fragment_listview)
    ListView comment_listview;
    private Context mContext;
    private Bundle bd;
    private String pid;
    private CommentListAdapter adapter;
    private UserCommentBean comment_bean;
    private List<UserCommentBean.ListBean> list_bean = new ArrayList<>();

    public static UserCommentFragment newInstance(String pid) {
        Bundle args = new Bundle();
        args.putString("pid", pid);
        UserCommentFragment fragment = new UserCommentFragment();
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
        View view = inflater.inflate(R.layout.user_comment_fragment, container, false);
        ButterKnife.bind(this, view);
        View view2 = inflater.inflate(R.layout.comment_listview_empty_view,container,false);
        ((ViewGroup)comment_listview.getParent()).addView(view2);
        comment_listview.setEmptyView(view2);
        adapter = new CommentListAdapter();
        comment_listview.setAdapter(adapter);

        loadDatas();
        return view;
    }

    private void loadDatas() {
        HttpServiceUtil.init().getCommentInfo("view",HttpServiceUtil.SID,pid).enqueue(new Callback<UserCommentBean>() {
            @Override
            public void onResponse(Call<UserCommentBean> call, Response<UserCommentBean> response) {
                comment_bean=response.body();
                list_bean = comment_bean.getList();
                if(list_bean!=null){
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<UserCommentBean> call, Throwable t) {

            }
        });
    }

    class CommentListAdapter extends BaseAdapter{

        private TextView comment_num_tv;
        private RatingBar ratingbar;
        private TextView comment_name;
        private TextView comment_time;
        private TextView comment_content;

        @Override
        public int getCount() {
           if(list_bean==null||list_bean.size()==0){
               return 0;
           }else{
               return list_bean.size()+1;
           }
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
            if(list_bean!=null){
                if(position==0){
                    view = LayoutInflater.from(mContext).inflate(R.layout.user_comment_fragment_listview_item1,parent,false);
                    comment_num_tv = (TextView) view.findViewById(R.id.comment_listview_item1_num_tv);
                    if(comment_bean!=null){
                        comment_num_tv.setText(comment_bean.getTotal()+"条评价");
                    }
                }else{
                    view = LayoutInflater.from(mContext).inflate(R.layout.user_comment_fragment_listview_item2,parent,false);
                    ratingbar = (RatingBar) view.findViewById(R.id.comment_listview_item2_ratingbar);
                    comment_name = (TextView)view.findViewById(R.id.comment_listview_item2_name_tv);
                    comment_time = (TextView) view.findViewById(R.id.comment_listview_item2_time_tv);
                    comment_content = (TextView) view.findViewById(R.id.comment_listview_item2_content_tv);
                    if(list_bean!=null){
                        ratingbar.setNumStars(Integer.parseInt(list_bean.get(position-1).getStars()));
                        ratingbar.setRating(Float.parseFloat((list_bean.get(position-1).getStars())));
                        comment_name.setText(list_bean.get(position-1).getUsername());
                        comment_time.setText(list_bean.get(position-1).getUptime());
                        if(list_bean.get(position-1).getContent()!=null){
                            comment_content.setText(list_bean.get(position-1).getContent());
                        }
                    }
                }
            }

            return view;
        }
    }
}
