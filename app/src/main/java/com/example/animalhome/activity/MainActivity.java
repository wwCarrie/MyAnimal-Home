package com.example.animalhome.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.animalhome.R;
import com.example.animalhome.fragment.FindFragment;
import com.example.animalhome.fragment.HomeFragment;
import com.example.animalhome.fragment.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new MineFragment());

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_home) {
                    FragmentTransaction tran3 = getSupportFragmentManager().beginTransaction();
                    tran3.replace(R.id.main, fragmentList.get(0));
                    tran3.commit();
                    return true;
                } else if (itemId == R.id.menu_find) {
                    FragmentTransaction tran2 = getSupportFragmentManager().beginTransaction();
                    tran2.replace(R.id.main, fragmentList.get(1));
                    tran2.commit();

                    return true;
                } else if (itemId == R.id.menu_mine) {
                    FragmentTransaction tran1 = getSupportFragmentManager().beginTransaction();
                    tran1.replace(R.id.main, fragmentList.get(2));
                    tran1.commit();
                    return true;
                }
                return false;
            }
        });
        navigation.setSelectedItemId(R.id.menu_home);
    }
}
