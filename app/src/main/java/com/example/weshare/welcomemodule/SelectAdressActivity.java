package com.example.weshare.welcomemodule;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weshare.R;
import com.example.weshare.databean.LocationBean;
import com.example.weshare.utils.HttpServiceUtil;
import com.example.weshare.utils.PinYinUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectAdressActivity extends AppCompatActivity
{

    @BindView(R.id.selectadress_searchview_sv)
    SearchView mSelectadressSearchviewSv;
    @BindView(R.id.selectadress_index_lv)
    ListView mSelectadressIndexLv;
    @BindView(R.id.selectadress_content_ev)
    ExpandableListView mSelectadressContentEv;
    public static LocationBean mData;
    private LinkedHashMap<String, List<LocationBean.ZoneBean>> zongMap;
    private static LinkedHashMap<String, List<LocationBean.ZoneBean>> databak;
    private List<String> indexs;
    private MyListViewAdapter mMyListViewAdapter;
    private ExplanListViewAdapter mExplanListViewAdapter;
    private LoaderManager mManager;
    private LoaderManager.LoaderCallbacks<LinkedHashMap<String, List<LocationBean.ZoneBean>>> mCallback = new LoaderManager.LoaderCallbacks<LinkedHashMap<String, List<LocationBean.ZoneBean>>>()
    {
        @Override
        public Loader<LinkedHashMap<String, List<LocationBean.ZoneBean>>> onCreateLoader(int i, Bundle bundle)
        {
            return new MyLoader(SelectAdressActivity.this, bundle);
        }

        @Override
        public void onLoadFinished(Loader<LinkedHashMap<String, List<LocationBean.ZoneBean>>> loader, LinkedHashMap<String, List<LocationBean.ZoneBean>> stringListLinkedHashMap)
        {
            if (zongMap != null)
            {
                zongMap.clear();
                zongMap.putAll(stringListLinkedHashMap);
                indexs.clear();
                indexs.addAll(stringListLinkedHashMap.keySet());
                mExplanListViewAdapter.notifyDataSetChanged();
                mMyListViewAdapter.notifyDataSetChanged();
                for (int i = 0; i < indexs.size(); i++)
                {
                    mSelectadressContentEv.expandGroup(i);
                }
            }
        }

        @Override
        public void onLoaderReset(Loader<LinkedHashMap<String, List<LocationBean.ZoneBean>>> loader)
        {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_adress);
        ButterKnife.bind(this);
        mManager = getLoaderManager();
        mManager.initLoader(9, null, mCallback);
        loadData();

        initView();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void initView()
    {
        mMyListViewAdapter = new MyListViewAdapter();
        mSelectadressIndexLv.setDividerHeight(0);
        mSelectadressIndexLv.setAdapter(mMyListViewAdapter);
        mExplanListViewAdapter = new ExplanListViewAdapter();
        mSelectadressContentEv.setAdapter(mExplanListViewAdapter);
        mSelectadressContentEv.setGroupIndicator(null);
        mSelectadressIndexLv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                mSelectadressContentEv.setSelectedGroup(i);
            }
        });
        mSelectadressContentEv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l)
            {
                return true;
            }
        });
        mSelectadressContentEv.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l)
            {
                LocationBean.ZoneBean tag = (LocationBean.ZoneBean) view.getTag();
                Intent intent = new Intent();
                intent.putExtra("selected", tag);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            }
        });
        mSelectadressSearchviewSv.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                Bundle bundle = new Bundle();
                bundle.putString("searchkey", s);
                //重新启动Loader
                mManager.restartLoader(9, bundle, mCallback);
                return true;
            }
        });
    }

    private void loadData()
    {
        final ProgressDialog dialog = ProgressDialog.show(SelectAdressActivity.this, "联网中", "正在更新地址信息...");
        HttpServiceUtil.init().getAdressesData("", "", "186").enqueue(new Callback<LocationBean>()
        {
            @Override
            public void onResponse(Call<LocationBean> call, Response<LocationBean> response)
            {
                mData = response.body();
                if (mData != null)
                {
//                    HttpServiceUtil.SID = mData.getSid();
                    reFormData();
                    if (mMyListViewAdapter != null)
                    {
                        mMyListViewAdapter.notifyDataSetChanged();
                    }
                    if (mExplanListViewAdapter != null)
                    {
                        mExplanListViewAdapter.notifyDataSetChanged();
                    }
                    for (int i = 0; i < indexs.size(); i++)
                    {
                        mSelectadressContentEv.expandGroup(i);
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<LocationBean> call, Throwable t)
            {
                dialog.dismiss();
                Toast.makeText(SelectAdressActivity.this, "无法更新地址列表,请检查网络后重试!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void reFormData()
    {
        initMap();
        List<LocationBean.ZoneBean> zones = mData.getZone();
        for (LocationBean.ZoneBean zone : zones)
        {
            String firstSpell = PinYinUtil.getFirstSpell(zone.getName());
            zongMap.get(firstSpell).add(zone);
        }
        databak = new LinkedHashMap<>();
        databak.putAll(zongMap);
    }

    private void initMap()
    {
        zongMap = new LinkedHashMap<>();
        for (int i = 0; i < 26; i++)
        {
            zongMap.put((char) ('A' + i) + "", new ArrayList<LocationBean.ZoneBean>());
        }
        zongMap.put("#", new ArrayList<LocationBean.ZoneBean>());
        Set<String> keySet = zongMap.keySet();
        indexs = new ArrayList<>();
        indexs.addAll(keySet);

    }

    private static class MyLoader extends AsyncTaskLoader<LinkedHashMap<String, List<LocationBean.ZoneBean>>>
    {

        private Bundle mBundle;

        public MyLoader(Context context, Bundle bundle)
        {
            super(context);
            mBundle = bundle;
        }

        @Override
        protected void onStartLoading()
        {
            super.onStartLoading();
            forceLoad();
        }

        @Override
        public LinkedHashMap<String, List<LocationBean.ZoneBean>> loadInBackground()
        {
            String searchkey = null;
            if (mBundle != null)
            {
                searchkey = mBundle.getString("searchkey");
            }
            LinkedHashMap<String, List<LocationBean.ZoneBean>> data = new LinkedHashMap<>();
            if (TextUtils.isEmpty(searchkey))
            {
                if (databak != null)
                {
                    data.putAll(databak);
                }
            }
            else
            {
                for (Map.Entry<String, List<LocationBean.ZoneBean>> entry : databak.entrySet())
                {
                    List<LocationBean.ZoneBean> zoneBeen = entry.getValue();
                    for (int i = 0; i < zoneBeen.size(); i++)
                    {
                        LocationBean.ZoneBean bean = zoneBeen.get(i);
                        if (bean.getName().contains(searchkey))
                        {
                            if (data.get(entry.getKey()) == null)
                            {
                                ArrayList<LocationBean.ZoneBean> zoneBeentemp = new ArrayList<>();
                                zoneBeentemp.add(bean);
                                data.put(entry.getKey(),zoneBeentemp);
                            }
                            else
                            {
                                data.get(entry.getKey()).add(bean);
                            }
                        }
                    }
                }
            }
            return data;
        }
    }

    private class MyListViewAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return indexs == null ? 0 : indexs.size();
        }

        @Override
        public Object getItem(int i)
        {
            return null;
        }

        @Override
        public long getItemId(int i)
        {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            TextView textView = new TextView(SelectAdressActivity.this);
            textView.setTextColor(Color.RED);
            textView.setTextSize(20);
            textView.setHeight(dip2px(30));
            textView.setPadding(10, 0, 10, 0);
            textView.setGravity(Gravity.CENTER);
            textView.setText(indexs.get(i));
            return textView;
        }
    }

    private class ExplanListViewAdapter extends BaseExpandableListAdapter
    {

        @Override
        public int getGroupCount()
        {
            return zongMap == null ? 0 : zongMap.size();
        }

        @Override
        public int getChildrenCount(int i)
        {
            return zongMap.get(indexs.get(i)).size();
        }

        @Override
        public Object getGroup(int i)
        {
            return zongMap.get(indexs.get(i));
        }

        @Override
        public Object getChild(int i, int i1)
        {
            return zongMap.get(indexs.get(i)).get(i1);
        }

        @Override
        public long getGroupId(int i)
        {
            return i;
        }

        @Override
        public long getChildId(int i, int i1)
        {
            return i1;
        }

        @Override
        public boolean hasStableIds()
        {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup)
        {
            TextView textView = new TextView(SelectAdressActivity.this);
            textView.setTextSize(20);
            textView.setHeight(dip2px(50));
            textView.setPadding(10, 0, 10, 0);
            textView.setWidth(getWindowManager().getDefaultDisplay().getWidth());
            textView.setBackgroundColor(Color.GRAY);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setText(indexs.get(i));
            return textView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup)
        {
            TextView textView = new TextView(SelectAdressActivity.this);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(20);
            textView.setHeight(dip2px(40));
            textView.setPadding(10, 0, 10, 0);
            textView.setWidth(getWindowManager().getDefaultDisplay().getWidth());
            textView.setGravity(Gravity.CENTER_VERTICAL);
            LocationBean.ZoneBean zoneBean = zongMap.get(indexs.get(i)).get(i1);
            textView.setText(zoneBean.getName());
            textView.setTag(zoneBean);
            return textView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1)
        {
            return true;
        }
    }
}
