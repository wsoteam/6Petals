package com.wsoteam.diet.MainScreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.wsoteam.diet.BranchOfAnalyzer.ActivityListAndSearch;
import com.wsoteam.diet.BranchOfBilling.ActivityPresentation;
import com.wsoteam.diet.BranchOfCalculating.ActivityListOfCalculating;
import com.wsoteam.diet.BranchOfDescription.ActivityDescription;
import com.wsoteam.diet.BranchOfDiary.ActivityListOfDiary;
import com.wsoteam.diet.BranchOfMonoDiets.ActivityMonoDiet;
import com.wsoteam.diet.BranchOfNews.ActivityListOfNews;
import com.wsoteam.diet.BranchOfNotifications.ActivityListOfNotifications;
import com.wsoteam.diet.BranchOfRecipes.ActivityGroupsOfRecipes;
import com.wsoteam.diet.Config;
import com.wsoteam.diet.IPCheck.IPCheckApi;
import com.wsoteam.diet.IPCheck.IPCheckObject;
import com.wsoteam.diet.OtherActivity.ActivityEmpty;
import com.wsoteam.diet.R;
import com.yandex.metrica.YandexMetrica;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AnimatedVectorDrawable animatedVectorDrawable;
    private RewardedVideoAd mRewardedVideoAd;
    private Toolbar toolbar;
    private RecyclerView rvMainList;
    private Animation animationChangeScale;

    private int COUNT_OF_RUN = 0;
    private String TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG = "COUNT_OF_RUN";
    private SharedPreferences countOfRun;
    private boolean isAccessibleCountry = true;
    private String notAccessibleCountryCode = "UA";
    private Integer[] urlsOfImages = new Integer[]{R.drawable.ic_main_menu_newsfeed, R.drawable.ic_main_menu_targets,
            R.drawable.ic_main_menu_analyzer, R.drawable.ic_main_menu_calculating, R.drawable.ic_main_menu_diary,
            R.drawable.ic_main_menu_diets, R.drawable.ic_main_menu_reciepes, R.drawable.ic_main_menu_fitness};

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
        /*Intent intent = new Intent(MainActivity.this, ActivityPresentation.class);
        startActivity(intent);*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        MobileAds.initialize(this, Config.ADMOB_ID);

        if (getIntent().getStringExtra("MainActivity").equals(notAccessibleCountryCode)) {
            isAccessibleCountry = false;
        }

        rvMainList = findViewById(R.id.rvMainScreen);
        rvMainList.setLayoutManager(new GridLayoutManager(this, 2));
        rvMainList.setAdapter(new ItemAdapter(getResources().getStringArray(R.array.names_items_of_main_screen),
                urlsOfImages, getResources().getStringArray(R.array.properties_items_of_main_screen)));

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.main_menu));

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_g);
        navigationView.setNavigationItemSelectedListener(this);
        animationChangeScale = AnimationUtils.loadAnimation(this, R.anim.anim_change_scale);

        additionOneToSharedPreference();
        checkFirstRun();

        YandexMetrica.reportEvent("Открыт экран: Тренировки");

    }

    private void additionOneToSharedPreference() {
        countOfRun = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = countOfRun.edit();
        editor.putInt(TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG, countOfRun.getInt(TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG, COUNT_OF_RUN) + 1);
        editor.commit();

    }

    private void checkFirstRun() {
        if (countOfRun.getInt(TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG, COUNT_OF_RUN) == 10) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.alert_dialog_grade, null);
            builder.setView(view);
            builder.setNeutralButton(R.string.Late, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    countOfRun = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = countOfRun.edit();
                    editor.putInt(TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG, 0);
                    editor.commit();
                    YandexMetrica.reportEvent("Оценка отложена");
                }
            });
            builder.setPositiveButton(R.string.Grade, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    YandexMetrica.reportEvent("Переход в ГП для оценки");
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=" + MainActivity.this.getPackageName()));
                    startActivity(intent);
                }
            });
            builder.setNegativeButton(R.string.Never, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    YandexMetrica.reportEvent("Отказ в оценке");
                }
            });

            builder.show();
        }
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

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle, tvProperties;
        private ImageView ivImage, ivIsOpen;
        private CardView cardView;

        public ItemHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_list_main_menu, viewGroup, false));

            ivImage = itemView.findViewById(R.id.ivMainMenuImage);
            ivIsOpen = itemView.findViewById(R.id.ivIsOpen);
            tvTitle = itemView.findViewById(R.id.tvMainMenuTitle);
            tvProperties = itemView.findViewById(R.id.tvMainMenuProperties);
            cardView = itemView.findViewById(R.id.cvParentView);
            ivIsOpen.setVisibility(View.GONE);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            cardView.startAnimation(animationChangeScale);
            Intent intent = new Intent();
            switch (getAdapterPosition()) {
                case 0:
                    if (isAccessibleCountry) {
                        intent = new Intent(MainActivity.this, ActivityListOfNews.class);
                    } else {
                        intent = new Intent(MainActivity.this, ActivityEmpty.class);
                    }
                    break;
                case 1:
                    intent = new Intent(MainActivity.this, ActivityListOfNotifications.class);
                    break;
                case 2:
                    intent = new Intent(MainActivity.this, ActivityListAndSearch.class);
                    break;
                case 3:
                    intent = new Intent(MainActivity.this, ActivityListOfCalculating.class);
                    break;
                case 4:
                    intent = new Intent(MainActivity.this, ActivityListOfDiary.class);
                    break;
                case 5:
                    intent = new Intent(MainActivity.this, ActivityMonoDiet.class);
                    break;
                case 6:
                    intent = new Intent(MainActivity.this, ActivityGroupsOfRecipes.class);
                    break;
                case 7:
                    intent = new Intent(MainActivity.this, com.wsoteam.diet.BranchOfExercises.Activities.MainActivity.class);
                    break;
            }
            startActivity(intent);
        }

        public void bind(String name, Integer image, String properties) {
            tvTitle.setText(name);
            tvProperties.setText(properties);
            Glide.with(MainActivity.this).load(image).into(ivImage);
            /*if (getAdapterPosition() == 4 && isAccessibleCountry
                    || getAdapterPosition() == 5) {
                ivIsOpen.setVisibility(View.VISIBLE);
            }*/
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        String[] names, propeties;
        Integer[] images;

        public ItemAdapter(String[] names, Integer[] images, String[] propeties) {
            this.names = names;
            this.images = images;
            this.propeties = propeties;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            return new ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            holder.bind(names[position], images[position], propeties[position]);
        }

        @Override
        public int getItemCount() {
            return names.length;
        }
    }

}
