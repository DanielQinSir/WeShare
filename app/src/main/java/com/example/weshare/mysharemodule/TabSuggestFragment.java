package com.example.weshare.mysharemodule;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weshare.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-08
 * Time: 11:42
 * For:
 */
public class TabSuggestFragment extends Fragment
{

    @BindView(R.id.tab_suggest_content_et)
    EditText mTabSuggestContentEt;
    @BindView(R.id.tab_suggest_addimage_iv)
    ImageView mTabSuggestAddimageIv;
    @BindView(R.id.tab_suggest_submit_btn)
    Button mTabSuggestSubmitBtn;
    @BindView(R.id.tab_suggest_image_gv)
    GridView mTabSuggestImageGv;
    private Context mContext;
    private List<Bitmap> pics;

    private View.OnClickListener mlistener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.tab_suggest_addimage_iv:
                    showPopMenu();
                    break;
                case R.id.tab_suggest_submit_btn:
                    Toast.makeText(mContext, mTabSuggestContentEt.getText().toString().trim() + "\n提交成功!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private GridViewAdapter mGridViewAdapter;

    public static TabSuggestFragment newInstance()
    {
        return new TabSuggestFragment();
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
        View view = inflater.inflate(R.layout.tab_suggest_fragment, container, false);
        ButterKnife.bind(this, view);
        mTabSuggestAddimageIv.setOnClickListener(mlistener);
        mTabSuggestSubmitBtn.setOnClickListener(mlistener);
        mTabSuggestContentEt.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (TextUtils.isEmpty(editable.toString().trim()))
                {
                    mTabSuggestSubmitBtn.setEnabled(false);
                    mTabSuggestSubmitBtn.setBackgroundColor(Color.GRAY);
                }
                else
                {
                    mTabSuggestSubmitBtn.setEnabled(true);
                    mTabSuggestSubmitBtn.setBackgroundColor(Color.RED);
                }
            }
        });
        mTabSuggestSubmitBtn.setEnabled(false);
        mGridViewAdapter = new GridViewAdapter();
        mTabSuggestImageGv.setAdapter(mGridViewAdapter);
        return view;
    }

    private void showPopMenu()
    {
        final PopupMenu popupMenu = new PopupMenu(mContext, mTabSuggestAddimageIv, Gravity.BOTTOM);
        Menu menu = popupMenu.getMenu();
        menu.add(Menu.NONE, 1, 1, "拍照");
        menu.add(Menu.NONE, 2, 2, "从相册选取");
        menu.add(Menu.NONE, 3, 3, "取消");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case 1:
                        takePhotoes();
                        popupMenu.dismiss();
                        break;
                    case 2:
                        selectFromPhone();
                        popupMenu.dismiss();
                        break;
                    case 3:
                        popupMenu.dismiss();
                        break;
                }
                return true;
            }
        });
        popupMenu.setGravity(Gravity.BOTTOM);
        popupMenu.show();
    }

    private void selectFromPhone()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 2:
                getImageByUri(data);
                break;
            case 1:
                getImageByFilePath(data);
                break;
        }
    }

    private void getImageByFilePath(Intent data)
    {
        String path = data.getStringExtra("pic");
        if (TextUtils.isEmpty(path))
        {
            Toast.makeText(mContext, "未拍照!", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(path);
        if (file.exists())
        {
            try
            {
                InputStream input = new FileInputStream(file);
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                if (pics == null)
                {
                    pics = new ArrayList<>();
                }
                pics.add(bitmap);
                if (mGridViewAdapter != null)
                {
                    mGridViewAdapter.notifyDataSetChanged();
                }
                input.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void getImageByUri(Intent data)
    {
        if (data == null)
        {
            Toast.makeText(mContext, "未选择图片!", Toast.LENGTH_SHORT).show();
            return;
        }
        Uri uri = data.getData();
        try
        {
            InputStream inputStream = mContext.getContentResolver().openInputStream(uri);

            int len = 0;
            byte[] buffer = new byte[ 1024 ];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != - 1)
            {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            if (pics == null)
            {
                pics = new ArrayList<>();
            }
            pics.add(bitmap);
            if (mGridViewAdapter == null)
            {
                mGridViewAdapter.notifyDataSetChanged();
            }
            inputStream.close();
            baos.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //关闭流
        }
    }

    private void takePhotoes()
    {
        startActivityForResult(new Intent(mContext, TakePictureActivity.class), 1);
    }

    private class GridViewAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return pics == null ? 0 : pics.size();
        }

        @Override
        public Object getItem(int i)
        {
            return pics.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            imageView.setMinimumHeight(20);
            imageView.setPadding(10,10,10,10);
            imageView.setImageBitmap(pics.get(i));
            return imageView;
        }
    }

}
