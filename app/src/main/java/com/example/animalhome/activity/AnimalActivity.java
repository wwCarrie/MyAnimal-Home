package com.example.animalhome.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.animalhome.R;
import com.example.animalhome.entity.MyAnimal;
import com.example.animalhome.utils.CurrentAnimalUtils;

public class AnimalActivity extends AppCompatActivity {

    private EditText etBreed, etNickname, etBirthday, etCharacter, etSkill;

    private ImageView ivBack;

    private Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animal);

        etBreed = findViewById(R.id.et_breed);
        etNickname = findViewById(R.id.et_nickname);
        etBirthday = findViewById(R.id.et_birthday);
        etCharacter = findViewById(R.id.et_character);
        etSkill = findViewById(R.id.et_skill);
        ivBack = findViewById(R.id.iv_back);
        btnSave = findViewById(R.id.btn_save);

        MyAnimal animal = CurrentAnimalUtils.getCurrentAnimal();
        etBreed.setText(animal.getBreed());
        etNickname.setText(animal.getNickname());
        etBirthday.setText(animal.getBirthday());
        etCharacter.setText(animal.getCharacter());
        etSkill.setText(animal.getSkill());

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAnimal animal = new MyAnimal();
                animal.setBreed(etBreed.getText().toString());
                animal.setNickname(etNickname.getText().toString());
                animal.setBirthday(etBirthday.getText().toString());
                animal.setCharacter(etCharacter.getText().toString());
                animal.setSkill(etSkill.getText().toString());
                CurrentAnimalUtils.setCurrentAnimal(animal);
                Toast.makeText(AnimalActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
