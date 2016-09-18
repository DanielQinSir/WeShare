package com.example.weshare.homepagemodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weshare.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.search_listview)
    ListView searchListview;
    @BindView(R.id.search_btn)
    Button searchBtn;

    private Context mContext;
    private List<String> search_list = new ArrayList<>();
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mContext = this;
        initview();

    }



    private void initview() {
        adapter = new SearchAdapter();
        searchListview.setAdapter(adapter);
        searchListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String content = search_list.get(i);
                Intent intent = new Intent(mContext,SearchDetailActivity.class);
                intent.putExtra("content",content);
                startActivity(intent);
            }
        });
        searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content2 = searchEt.getText().toString();
                search_list.add(content2);
                Intent i = new Intent(mContext,SearchDetailActivity.class);
                i.putExtra("content",content2);
                startActivity(i);
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_list.clear();
                adapter.notifyDataSetChanged();
            }
        });

    }

    class SearchAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return search_list==null?0:search_list.size();
        }

        @Override
        public Object getItem(int i) {
            return search_list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup parent) {
            View view = convertview;
            SearchViewHolder viewHolder = null;
            if(convertview==null){
                view = LayoutInflater.from(mContext).inflate(R.layout.search_listview_item,parent,false);
                viewHolder = new SearchViewHolder(view);
            }else{
                viewHolder = (SearchViewHolder) view.getTag();
            }
            if(search_list!=null){
                viewHolder.search_lv_tv.setText(search_list.get(position));
            }
            return view;
        }
    }

    class SearchViewHolder {

        public final TextView search_lv_tv;

        public SearchViewHolder(View view){
            view.setTag(this);
            search_lv_tv = (TextView) view.findViewById(R.id.search_listview_item_txt);
        }
    }
}
