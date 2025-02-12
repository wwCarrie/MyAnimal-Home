package com.example.animalhome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.animalhome.R;
import com.example.animalhome.db.BusinessResult;
import com.example.animalhome.db.FindDB;
import com.example.animalhome.entity.Find;
import com.example.animalhome.utils.AlbumUtils;
import com.example.animalhome.utils.CurrentUserUtils;

public class FindEditActivity extends AppCompatActivity {

    private ImageView ivBack, ivFindImg;

    private TextView tvTitle, tvSelect,tvDelete;

    private EditText etFindTitle;

    private Button btnSubmit;

    private Find find;

    private boolean isEdit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_edit);
        //绑定控件
        bindView();
        //初始化数据
        initData();
        //初始化控件
        initView();
    }

    private void bindView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_main_title);
        ivFindImg = findViewById(R.id.iv_find_img);
        tvSelect = findViewById(R.id.tv_select);
        etFindTitle = findViewById(R.id.et_find_title);
        btnSubmit = findViewById(R.id.btn_submit);
        tvDelete = findViewById(R.id.tv_delete);
    }

    private void initData() {
        find = (Find) getIntent().getSerializableExtra("find");
        if (find != null) {
            Glide.with(this).load(find.getUrl()).into(ivFindImg);
            etFindTitle.setText(find.getTitle());
            tvDelete.setVisibility(View.VISIBLE);
            isEdit = true;
        } else {
            find = new Find();
            tvDelete.setVisibility(View.GONE);
            isEdit = false;
        }
    }

    private void initView() {
        tvTitle.setText(isEdit ? "编辑内容" : "发布内容");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivFindImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //选择图片
                AlbumUtils.openAlbum(FindEditActivity.this);
            }
        });
        tvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //选择图片
                AlbumUtils.openAlbum(FindEditActivity.this);
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find.setTitle(etFindTitle.getText().toString());
                find.setUserId(CurrentUserUtils.getCurrentUser().getId());
                BusinessResult<Void> result;
                if (isEdit) {
                    //编辑
                    result = FindDB.updateFind(find);
                } else {
                    //添加
                    result = FindDB.addFind(find);
                }
                Toast.makeText(FindEditActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if (result.isSuccess()) {
                    finish();
                }
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusinessResult<Void> result = FindDB.deleteFind(find.getId());
                Toast.makeText(FindEditActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if (result.isSuccess()) {
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //如果是打开相册，获取图片路径，显示图片
            if (requestCode == AlbumUtils.OPEN_ALBUM_REQUEST_CODE) {
                String path = AlbumUtils.getImagePath(data);
                Glide.with(this).load(path).into(ivFindImg);
                find.setUrl(path);
            }
        }
    }
}
