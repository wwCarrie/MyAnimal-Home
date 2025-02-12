package com.example.animalhome.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalhome.R;
import com.example.animalhome.activity.FindEditActivity;
import com.example.animalhome.adapter.FindAdapter;
import com.example.animalhome.db.BusinessResult;
import com.example.animalhome.db.FindDB;
import com.example.animalhome.entity.Find;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {

    private FindAdapter adapter;

    private RecyclerView rv;

    private TextView tvAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_find, container, false);
        rv = view.findViewById(R.id.rv_find);
        tvAdd = view.findViewById(R.id.tv_add);
        adapter = new FindAdapter();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置为2列的网格布局
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv.setAdapter(adapter);
        //监听当列表滑动到底部时，加载更多数据
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //判断是否滑动到底部
                if (!recyclerView.canScrollVertically(1)) {
                    //加载更多数据
                    adapter.addList(getList());
                }
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FindEditActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setList(getList());
    }

    public List<Find> getList() {
        BusinessResult<List<Find>> result = FindDB.getAllFindList();
        if (result.isSuccess()) {
            return result.getData();
        }
        return new ArrayList<>();
    }
}
