package com.wsoteam.diet.MainScreen;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.wsoteam.diet.BranchOfDiary.ActivityListOfDiary;
import com.wsoteam.diet.BranchOfDescription.ActivityDescription;
import com.wsoteam.diet.BranchOfNotifications.ActivityListOfNotifications;
import com.wsoteam.diet.BranchOfRecipes.ActivityGroupsOfRecipes;
import com.wsoteam.diet.Config;
import com.wsoteam.diet.Manifest;
import com.wsoteam.diet.OtherActivity.ActivityEmpty;
import com.wsoteam.diet.BranchOfCalculating.ActivityListOfCalculating;
import com.wsoteam.diet.BranchOfMonoDiets.ActivityMonoDiet;
import com.wsoteam.diet.R;
import com.yandex.metrica.YandexMetrica;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private CardView cvMonoDiets, cvCalculating, cvDiary, cvRecipes, cvAnalyzator;
    private ImageView ivDiets, ivCalculating, ivDiary, ivRecipes, ivAnalyzator;
    private AnimatedVectorDrawable animatedVectorDrawable;
    private RewardedVideoAd mRewardedVideoAd;
    Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                animatedVectorDrawable = (AnimatedVectorDrawable) menu.getItem(0).getIcon();
                final Handler mainHandler = new Handler(Looper.getMainLooper());
                animatedVectorDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                animatedVectorDrawable.start();
                            }
                        });

                    }
                });
                animatedVectorDrawable.start();
            }
        } catch (Exception e) {

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ad_point:
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                    loadAd();
                    YandexMetrica.reportEvent("Открыта видео-реклама: Анимационная кнопка");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_g);
        navigationView.setNavigationItemSelectedListener(this);


        setTitle(getString(R.string.main_menu));


        cvMonoDiets = findViewById(R.id.cvMAMonoDiets);
        cvCalculating = findViewById(R.id.cvMACalculation);
        cvDiary = findViewById(R.id.cvMADiary);
        cvRecipes = findViewById(R.id.cvMARecipe);
        cvAnalyzator = findViewById(R.id.cvMAAnalyzer);

        ivAnalyzator = findViewById(R.id.ivAnalyzerMA);
        ivCalculating = findViewById(R.id.ivCalculationMA);
        ivDiary = findViewById(R.id.ivDiaryMA);
        ivDiets = findViewById(R.id.ivDietsMA);
        ivRecipes = findViewById(R.id.ivRecipesMA);

        Glide.with(this).load(R.drawable.background_diets).into(ivDiets);
        Glide.with(this).load(R.drawable.background_analyzer).into(ivAnalyzator);
        Glide.with(this).load(R.drawable.background_calculate).into(ivCalculating);
        Glide.with(this).load(R.drawable.background_diary).into(ivDiary);
        Glide.with(this).load(R.drawable.background_recipes).into(ivRecipes);

        loadAd();


        //YandexMetrica.reportEvent("Открыта реклама с анимационной кнопки");


        cvCalculating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityListOfCalculating.class);
                startActivity(intent);
            }
        });
        cvMonoDiets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityMonoDiet.class);
                startActivity(intent);
            }
        });
        cvDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityListOfDiary.class);
                startActivity(intent);
            }
        });
        cvRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityGroupsOfRecipes.class);
                startActivity(intent);
            }
        });
        cvAnalyzator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityEmpty.class);
                startActivity(intent);
            }
        });

        YandexMetrica.reportEvent("Открыт экран: Стартовый экран");
    }

    private void loadAd() {
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.loadAd(getResources().getString(R.string.admob_award),
                new AdRequest.Builder().build());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean isOpenMarket = false;

        int id = item.getItemId();
        Intent intent = new Intent(MainActivity.this, ActivityDescription.class);

        switch (id) {
            case R.id.menu_nav_core:
                intent.putExtra(Config.ID_OF_SELECT_MENU, 0);
                break;
            case R.id.menu_nav_faq:
                intent.putExtra(Config.ID_OF_SELECT_MENU, 1);
                break;
            case R.id.menu_nav_exit:
                intent.putExtra(Config.ID_OF_SELECT_MENU, 2);
                break;
            case R.id.menu_nav_contraindications:
                intent.putExtra(Config.ID_OF_SELECT_MENU, 3);
                break;
            case R.id.menu_nav_setting:
                intent.putExtra(Config.ID_OF_SELECT_MENU, 4);
                break;
            case R.id.menu_nav_more:
                Intent intentForViewMoreApps = new Intent(Intent.ACTION_VIEW);
                intentForViewMoreApps.setData(Uri.parse("market://details?id=onepic.bkgcom.com.ex"));
                startActivity(intentForViewMoreApps);
                YandexMetrica.reportEvent("Переход на тренировки");
                isOpenMarket = true;
                break;
            /*case R.id.menu_nav_notification:
                //intent = new Intent(MainActivity.this, ActivityDetailNotification.class);
                intent = new Intent(MainActivity.this, ActivityListOfNotifications.class);
                break;*/
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if (!isOpenMarket) {
            startActivity(intent);
        }
        return true;
    }
}
