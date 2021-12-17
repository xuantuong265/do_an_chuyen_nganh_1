package com.example.yo7a.healthwatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MenuNavigation extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_navigation);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.notification));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.hospital));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.person));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.message));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()) {
                    case 1:
                        fragment = new HistoryFragment();
                        break;
                    case 2:
                        fragment = new HospitalFragment();
                        break;
                    case 3:
                        fragment = new HomeFragment();
                        break;
                    case 4:
                        fragment = new PersonFragment();
                        break;
                    case 5:
                        fragment = new MessageFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

        bottomNavigation.setCount(1, "10");

        bottomNavigation.show(3, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}