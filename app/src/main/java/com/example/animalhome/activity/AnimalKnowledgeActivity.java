package com.example.animalhome.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.animalhome.R;
import com.example.animalhome.entity.AnimalKnowledge;

public class AnimalKnowledgeActivity extends AppCompatActivity {

    private ImageView ivBack,ivAnimal;

    private TextView tvTitle,tvContent;

    private AnimalKnowledge animalKnowledge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_knowledge);
        //绑定控件
        bindView();
        //初始化数据
        initData();
        //初始化控件
        initView();
    }

    private void initData() {
        animalKnowledge = (AnimalKnowledge) getIntent().getSerializableExtra("animalKnowledge");
    }

    private void bindView() {
        ivBack = findViewById(R.id.iv_back);
        ivAnimal = findViewById(R.id.iv_animal);
        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);
    }

    private void initView() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Glide.with(this).load(animalKnowledge.getImgResId()).into(ivAnimal);
        tvTitle.setText(animalKnowledge.getTitle());
        tvContent.setText(animalKnowledge.getContent());
    }
}
