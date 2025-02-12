package com.example.animalhome.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalhome.R;
import com.example.animalhome.adapter.FindAdapter;
import com.example.animalhome.db.BusinessResult;
import com.example.animalhome.db.FindDB;
import com.example.animalhome.entity.Find;
import com.example.animalhome.utils.CurrentUserUtils;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private FindAdapter adapter;

    private RecyclerView rv;

    private ImageView ivBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        //绑定控件
        bindView();
        //初始化数据
        initData();
        //初始化控件
        initView();
    }

    private void bindView() {
        rv = findViewById(R.id.rv_publish);
        ivBack = findViewById(R.id.iv_back);
    }

    private void initData() {
        adapter = new FindAdapter();
        adapter.setOnItemClickListener(new FindAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Find item) {

            }

            @Override
            public void onUnLike(int position, Find item) {
                adapter.removeItem(position);
            }
        });
    }

    private void initView() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //设置为2列的网格布局
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setList(getList());
    }

    public List<Find> getList() {
        BusinessResult<List<Find>> result = FindDB.getFavoriteFindListByUserId(CurrentUserUtils.getCurrentUser().getId());
        if (result.isSuccess()) {
            return result.getData();
        }
        return new ArrayList<>();
    }
}
