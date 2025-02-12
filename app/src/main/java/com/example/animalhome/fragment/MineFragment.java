package com.example.animalhome.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.animalhome.R;
import com.example.animalhome.activity.AnimalActivity;
import com.example.animalhome.activity.FavoriteActivity;
import com.example.animalhome.activity.LoginActivity;
import com.example.animalhome.activity.PublishActivity;
import com.example.animalhome.activity.VaccineLogActivity;
import com.example.animalhome.entity.MyAnimal;
import com.example.animalhome.utils.CurrentAnimalUtils;

public class MineFragment extends Fragment {

    private TextView tvBreed, tvNickname, tvBirthday, tvCharacter, tvSkill, tvEdit, tvVaccine,tvPublish,tvFavorite;

    private Button btnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = getLayoutInflater().inflate(R.layout.fragment_mine, container, false);
        tvBreed = inflate.findViewById(R.id.tv_breed);
        tvNickname = inflate.findViewById(R.id.tv_nickname);
        tvBirthday = inflate.findViewById(R.id.tv_birthday);
        tvCharacter = inflate.findViewById(R.id.tv_character);
        tvSkill = inflate.findViewById(R.id.tv_skill);
        tvEdit = inflate.findViewById(R.id.tv_edit);
        btnLogout = inflate.findViewById(R.id.btn_logout);
        tvVaccine = inflate.findViewById(R.id.tv_vaccine);
        tvPublish = inflate.findViewById(R.id.tv_publish);
        tvFavorite = inflate.findViewById(R.id.tv_favorite);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        setCurrentAnimal();
    }

    private void setCurrentAnimal() {
        MyAnimal animal = CurrentAnimalUtils.getCurrentAnimal();
        tvBreed.setText(animal.getBreed());
        tvNickname.setText(animal.getNickname());
        tvBirthday.setText(animal.getBirthday());
        tvCharacter.setText(animal.getCharacter());
        tvSkill.setText(animal.getSkill());
    }

    private void initView() {
        tvFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FavoriteActivity.class);
                startActivity(intent);
            }
        });
        tvPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PublishActivity.class);
                startActivity(intent);
            }
        });
        tvVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), VaccineLogActivity.class);
                startActivity(intent);
            }
        });
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AnimalActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }
}
