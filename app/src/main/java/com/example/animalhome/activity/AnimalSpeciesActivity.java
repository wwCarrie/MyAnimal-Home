package com.example.animalhome.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.animalhome.R;
import com.example.animalhome.entity.AnimalSpecies;

public class AnimalSpeciesActivity extends AppCompatActivity {

    private ImageView ivBack, ivAnimal;

    private TextView tvMainTitle, tvNation, tvCharacter, tvLife, tvDisease, tvDesc, tvFeature;

    private AnimalSpecies animalSpecies;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_species);
        // 绑定控件
        bindView();
        // 初始化数据
        initData();
        //初始化控件
        initView();
    }

    private void bindView() {
        ivBack = findViewById(R.id.iv_back);
        tvMainTitle = findViewById(R.id.tv_main_title);
        ivAnimal = findViewById(R.id.iv_animal);
        tvNation = findViewById(R.id.tv_nation);
        tvCharacter = findViewById(R.id.tv_character);
        tvLife = findViewById(R.id.tv_life);
        tvDisease = findViewById(R.id.tv_disease);
        tvDesc = findViewById(R.id.tv_desc);
        tvFeature = findViewById(R.id.tv_feature);
    }

    private void initData() {
        animalSpecies = (AnimalSpecies) getIntent().getSerializableExtra("animalSpecies");
    }

    private void initView() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvMainTitle.setText(animalSpecies.getName());
        Glide.with(this).load(animalSpecies.getCoverURL()).into(ivAnimal);
        tvNation.setText(animalSpecies.getNation());
        tvCharacter.setText(animalSpecies.getCharacters());
        tvLife.setText(animalSpecies.getLife());
        tvDisease.setText(animalSpecies.getEasyOfDisease());
        tvDesc.setText(animalSpecies.getDesc());
        tvFeature.setText(animalSpecies.getFeature());
    }
}
