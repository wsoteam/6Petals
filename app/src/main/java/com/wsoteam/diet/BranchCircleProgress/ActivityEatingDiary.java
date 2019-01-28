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
import com.wsoteam.diet.POJOsCircleProgress.Eating.Breakfast;
import com.wsoteam.diet.POJOsCircleProgress.Eating.Dinner;
import com.wsoteam.diet.POJOsCircleProgress.Eating.Lunch;
import com.wsoteam.diet.POJOsCircleProgress.Eating.Snack;
import com.wsoteam.diet.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

        compareDate();

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

    private void compareDate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        int prot = 0, kcal = 0, fat = 0, carbo = 0;

        List<Breakfast> breakfasts = Breakfast.listAll(Breakfast.class);
        List<Lunch> lunches = Lunch.listAll(Lunch.class);
        List<Dinner> dinners = Dinner.listAll(Dinner.class);
        List<Snack> snacks = Snack.listAll(Snack.class);

        Breakfast.deleteAll(Breakfast.class);
        Lunch.deleteAll(Lunch.class);
        Dinner.deleteAll(Dinner.class);
        Snack.deleteAll(Snack.class);

        for (int i = 0; i < breakfasts.size(); i++) {
            if (breakfasts.get(i).getDay() < day || breakfasts.get(i).getMonth() < month || breakfasts.get(i).getYear() < year) {

            } else {
                breakfasts.get(i).save();
                prot += breakfasts.get(i).getProtein();
                kcal += breakfasts.get(i).getCalories();
                fat += breakfasts.get(i).getFat();
                carbo += breakfasts.get(i).getCarbohydrates();
            }
        }
        for (int i = 0; i < lunches.size(); i++) {
            if (lunches.get(i).getDay() < day || lunches.get(i).getMonth() < month || lunches.get(i).getYear() < year) {

            } else {
                lunches.get(i).save();
                prot += lunches.get(i).getProtein();
                kcal += lunches.get(i).getCalories();
                fat += lunches.get(i).getFat();
                carbo += lunches.get(i).getCarbohydrates();
            }
        }
        for (int i = 0; i < dinners.size(); i++) {
            if (dinners.get(i).getDay() < day || dinners.get(i).getMonth() < month || dinners.get(i).getYear() < year) {

            } else {
                dinners.get(i).save();
                prot += dinners.get(i).getProtein();
                kcal += dinners.get(i).getCalories();
                fat += dinners.get(i).getFat();
                carbo += dinners.get(i).getCarbohydrates();
            }
        }
        for (int i = 0; i < snacks.size(); i++) {
            if (snacks.get(i).getDay() < day || snacks.get(i).getMonth() < month || snacks.get(i).getYear() < year) {

            } else {
                snacks.get(i).save();
                prot += snacks.get(i).getProtein();
                kcal += snacks.get(i).getCalories();
                fat += snacks.get(i).getFat();
                carbo += snacks.get(i).getCarbohydrates();
            }
        }
    }
}
