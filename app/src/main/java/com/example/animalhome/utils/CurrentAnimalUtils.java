package com.example.animalhome.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.animalhome.entity.MyAnimal;

public class CurrentAnimalUtils {

    private static final String CURRENT_ANIMAL = "CURRENT_ANIMAL";
    /**
     * 单例
     */
    private static CurrentAnimalUtils instance;
    private final SharedPreferences sp;

    public CurrentAnimalUtils(Context context) {
        sp = context.getSharedPreferences(CURRENT_ANIMAL+CurrentUserUtils.getCurrentUser().getId(), Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new CurrentAnimalUtils(context);
        }
    }

    /**
     * 获取当前用户的宠物
     */
    public static MyAnimal getCurrentAnimal() {
        MyAnimal animal = new MyAnimal();
        String breed = instance.sp.getString("breed", null);
        String nickname = instance.sp.getString("nickname", null);
        String birthday = instance.sp.getString("birthday", null);
        String character = instance.sp.getString("character", null);
        String skill = instance.sp.getString("skill", null);
        animal.setBreed(breed);
        animal.setNickname(nickname);
        animal.setBirthday(birthday);
        animal.setCharacter(character);
        animal.setSkill(skill);
        return animal;
    }

    /**
     * 设置当前用户的宠物
     */
    public static void setCurrentAnimal(MyAnimal animal) {
        SharedPreferences.Editor editor = instance.sp.edit();
        editor.putString("breed", animal.getBreed());
        editor.putString("nickname", animal.getNickname());
        editor.putString("birthday", animal.getBirthday());
        editor.putString("character", animal.getCharacter());
        editor.putString("skill", animal.getSkill());
        editor.apply();
    }
}
