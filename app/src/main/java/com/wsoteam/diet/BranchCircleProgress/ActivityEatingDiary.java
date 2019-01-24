package com.wsoteam.diet.BranchCircleProgress;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.wsoteam.diet.BranchCircleProgress.Fragments.FragmentBreakfast;
import com.wsoteam.diet.BranchCircleProgress.Fragments.FragmentDinner;
import com.wsoteam.diet.BranchCircleProgress.Fragments.FragmentLunch;
import com.wsoteam.diet.BranchCircleProgress.Fragments.FragmentSnack;
import com.wsoteam.diet.R;

import java.util.ArrayList;

public class ActivityEatingDiary extends AppCompatActivity {
    private ArrayList<Fragment> listOfFragments;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.btn_nav_eating_breakfast:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.btn_nav_eating_lunch:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.btn_nav_eating_dinner:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.btn_nav_eating_snack:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eating_diary);

        bottomNavigationView = findViewById(R.id.bottNavEatingDinner);
        viewPager = findViewById(R.id.vpEatingDiary);

        listOfFragments = new ArrayList<>();
        listOfFragments.add(new FragmentBreakfast());
        listOfFragments.add(new FragmentLunch());
        listOfFragments.add(new FragmentDinner());
        listOfFragments.add(new FragmentSnack());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listOfFragments.get(position);
            }

            @Override
            public int getCount() {
                return listOfFragments.size();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);


    }
}
