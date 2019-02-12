package com.wsoteam.diet.BranchOfDescription;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.wsoteam.diet.Config;
import com.wsoteam.diet.R;
import com.yandex.metrica.YandexMetrica;


public class ActivityDescription extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);


        // Получаем ViewPager и устанавливаем в него адаптер
        viewPager = findViewById(R.id.viewpager2);
        viewPager.setAdapter(
                new SectionsPagerAdapter(getSupportFragmentManager(), ActivityDescription.this));

        // Передаём ViewPager в TabLayout
        tabLayout = findViewById(R.id.sliding_tabs2);
        tabLayout.setupWithViewPager(viewPager);

        //открываем на нужной вкладке
        int position = (int) getIntent().getSerializableExtra(Config.ID_OF_SELECT_MENU);
        selectPage(position);

        YandexMetrica.reportEvent("Открыт экран: Описание");


    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

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

            switch (position) {
                case 0:
                    return FragmentViewText.newInstance(getString(R.string.tab_core));

                case 1:
                    return FragmentViewText.newInstance(getString(R.string.tab_faq));

                case 2:
                    return FragmentViewText.newInstance(getString(R.string.tab_exit));

                case 3:
                    return FragmentViewText.newInstance(getString(R.string.tab_contraindications));

                case 4:
                    return new FragmentSetting();

                default:
                    return FragmentViewText.newInstance("Error");

            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

    void selectPage(int pageIndex) {
        viewPager.setCurrentItem(pageIndex);
        tabLayout.setupWithViewPager(viewPager);
    }
}
