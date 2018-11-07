package com.wsoteam.diet.BranchOfDescription;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


import com.wsoteam.diet.R;

import java.util.ArrayList;

public class ActivityDescription extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        // Получаем ViewPager и устанавливаем в него адаптер
        ViewPager viewPager = findViewById(R.id.viewpager2);
        viewPager.setAdapter(
                new SectionsPagerAdapter(getSupportFragmentManager(), ActivityDescription.this));

        // Передаём ViewPager в TabLayout
        TabLayout tabLayout = findViewById(R.id.sliding_tabs2);
        tabLayout.setupWithViewPager(viewPager);

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

//        private String tabTitles[] = new String[]{"Tab1", "Tab2", "Tab3"};
        private String tabTitles[] = getResources().getStringArray(R.array.tab_title);
        final int PAGE_COUNT = tabTitles.length;
        private Context context;

        public SectionsPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0: return FragmentViewText.newInstance(getString(R.string.tab_core));

                case 1: return FragmentViewText.newInstance(getString(R.string.tab_faq));

                case 2: return FragmentViewText.newInstance(getString(R.string.tab_exit));

                case 3: return FragmentViewText.newInstance(getString(R.string.tab_contraindications));

                default: return FragmentViewText.newInstance("Error");

            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // генерируем заголовок в зависимости от позиции
            return tabTitles[position];
        }
    }
}
