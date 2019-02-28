package com.wsoteam.diet.MainScreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.gms.ads.MobileAds;
import com.wsoteam.diet.BranchEatingDiary.ActivityEatingDiary;
import com.wsoteam.diet.BranchOfAnalyzer.ActivityListAndSearch;
import com.wsoteam.diet.BranchOfCalculating.ActivityListOfCalculating;
import com.wsoteam.diet.BranchOfDescription.ActivityDescription;
import com.wsoteam.diet.BranchOfDiary.ActivityListOfDiary;
import com.wsoteam.diet.BranchOfMonoDiets.ActivityMonoDiet;
import com.wsoteam.diet.BranchOfNews.ActivityListOfNews;
import com.wsoteam.diet.BranchOfNotifications.ActivityListOfNotifications;
import com.wsoteam.diet.BranchOfRecipes.ActivityGroupsOfRecipes;
import com.wsoteam.diet.BranchProfile.ActivityEditProfile;
import com.wsoteam.diet.BranchProfile.ActivityProfile;
import com.wsoteam.diet.Config;
import com.wsoteam.diet.OtherActivity.ActivityEmpty;
import com.wsoteam.diet.POJOProfile.Profile;
import com.wsoteam.diet.POJOsCircleProgress.Eating.Breakfast;
import com.wsoteam.diet.POJOsCircleProgress.Eating.Dinner;
import com.wsoteam.diet.POJOsCircleProgress.Eating.Lunch;
import com.wsoteam.diet.POJOsCircleProgress.Eating.Snack;
import com.wsoteam.diet.POJOsCircleProgress.Water;
import com.wsoteam.diet.R;
import com.yandex.metrica.YandexMetrica;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.tvCircleProgressProt) TextView tvCircleProgressProt;
    @BindView(R.id.apCollapsingKcal) ArcProgress apCollapsingKcal;
    @BindView(R.id.apCollapsingProt) ArcProgress apCollapsingProt;
    @BindView(R.id.apCollapsingCarbo) ArcProgress apCollapsingCarbo;
    @BindView(R.id.apCollapsingFat) ArcProgress apCollapsingFat;
    @BindView(R.id.waveLoadingView) WaveLoadingView waveLoadingView;
    @BindView(R.id.ivMainScreenCollapsingCancelWater) ImageView ivMainScreenCollapsingCancelWater;
    @BindView(R.id.ivCollapsingMainCompleteWater) ImageView ivCollapsingMainCompleteWater;
    @BindView(R.id.fabAddEating) FloatingActionButton fabAddEating;
    @BindView(R.id.tvCircleProgressCarbo) TextView tvCircleProgressCarbo;
    @BindView(R.id.tvCircleProgressFat) TextView tvCircleProgressFat;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.mainappbar) AppBarLayout mainappbar;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.collapsingToolbarLayout) CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView tvLeftNBName;
    private CircleImageView ivLeftNBAvatar;

    private AnimatedVectorDrawable animatedVectorDrawable;
    private Animation animChangeScale, animRotateCancelWater, animWaterComplete;
    private SoundPool soundPool;
    private int soundIDdBubble;

    private Water water;
    private Profile profile;

    private int COUNT_OF_RUN = 0;
    private final String TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG = "COUNT_OF_RUN";
    private SharedPreferences countOfRun;
    private boolean isAccessibleCountry = true;
    private boolean isFiveStarSend = false;
    private boolean isFullWater;
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
    protected void onResume() {
        super.onResume();

        if (Profile.count(Profile.class) == 1) {
            profile = Profile.last(Profile.class);

            tvLeftNBName.setText(profile.getFirstName() + " " + profile.getLastName());
            tvLeftNBName.setTextSize(17);
            if (!profile.getPhotoUrl().equals("default")) {
                Uri uri = Uri.parse(profile.getPhotoUrl());
                Glide.with(MainActivity.this).load(uri).into(ivLeftNBAvatar);
            }
        }

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        Handler bindHandler = new Handler(Looper.getMainLooper());
        bindHandler.post(new Runnable() {
            @Override
            public void run() {
                bindCircleProgressBars(day, month, year, profile);
                fillWaterView(day, month, year, profile);
            }
        });

        waveLoadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFullWater) {
                    soundPool.play(soundIDdBubble, 1, 1, 0, 0, 1);
                    addCountOfWater();
                }
            }
        });

        ivLeftNBAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profile == null) {
                    Intent intent = new Intent(MainActivity.this, ActivityEditProfile.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, ActivityProfile.class);
                    startActivity(intent);
                }

            }
        });

        ivMainScreenCollapsingCancelWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(soundIDdBubble, 1, 1, 0, 0, 1);
                ivMainScreenCollapsingCancelWater.startAnimation(animRotateCancelWater);
                backCountOfWater();
            }
        });

    }

    private void showThankToast() {
        if (isFiveStarSend) {
            isFiveStarSend = false;
            TextView tvToastCompleteGift;
            ImageView ivToastCompleteGift;
            LayoutInflater toastInflater = getLayoutInflater();
            View toastLayout = toastInflater.inflate(R.layout.toast_complete_gift, null, false);
            tvToastCompleteGift = toastLayout.findViewById(R.id.tvToastCompleteGift);
            ivToastCompleteGift = toastLayout.findViewById(R.id.ivToastCompleteGift);
            tvToastCompleteGift.setText("Спасибо за отзыв!");

            Glide.with(this).load(R.drawable.icon_toast_thank_for_grade).into(ivToastCompleteGift);

            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLayout);
            toast.show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        ButterKnife.bind(this);

        MobileAds.initialize(this, Config.ADMOB_ID);

        if (getIntent().getStringExtra("MainActivity").equals(notAccessibleCountryCode)) {
            isAccessibleCountry = false;
        }


        setSupportActionBar(toolbar);
        setTitle("");


        mainappbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }

                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getString(R.string.main_menu));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });
        loadSound();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_g);
        navigationView.setNavigationItemSelectedListener(this);
        animChangeScale = AnimationUtils.loadAnimation(this, R.anim.anim_change_scale);
        animWaterComplete = AnimationUtils.loadAnimation(this, R.anim.anim_water_complete_tick);

        animRotateCancelWater = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_cancel_water);

        View view = navigationView.getHeaderView(0);
        tvLeftNBName = view.findViewById(R.id.tvLeftNBName);
        ivLeftNBAvatar = view.findViewById(R.id.ivLeftNBAvatar);


        additionOneToSharedPreference();
        checkFirstRun();

        fabAddEating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityEatingDiary.class);
                startActivity(intent);
            }
        });


        waveLoadingView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showChoisePortionOfWaterAD();
                return true;
            }
        });
    }

    private void showChoisePortionOfWaterAD() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.alert_dialog_show_choise_portion_of_water, null);
        EditText edtADChoiseWaterPortion = view.findViewById(R.id.edtADChoiseWaterPortion);
        builder.setView(view);
        builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (edtADChoiseWaterPortion.getText().toString().equals("")
                        || Integer.parseInt(edtADChoiseWaterPortion.getText().toString()) == 0) {
                    Toast.makeText(MainActivity.this, "Введите порцию воды", Toast.LENGTH_SHORT).show();
                } else {
                    water = Water.last(Water.class);
                    water.setStep(Integer.parseInt(edtADChoiseWaterPortion.getText().toString()));
                    Water.deleteAll(Water.class);
                    water.save();
                    Toast.makeText(MainActivity.this, "Новая порция сохранена", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    private void loadSound() {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        try {
            soundIDdBubble = soundPool.load(getAssets().openFd("buble.mp3"), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addCountOfWater() {
        double defaultWaterCount = 2000;

        if (profile != null) {
            double percent = (double) water.getStep() / (double) profile.getWaterCount();
            int step = (int) Math.round(percent * 100);
            int newCurrentNumber = water.getCurrentNumber() + water.getStep();
            waveLoadingView.setCenterTitle(String.valueOf(newCurrentNumber) + "/" + String.valueOf(profile.getWaterCount()));
            waveLoadingView.setProgressValue(waveLoadingView.getProgressValue() + step);


            water.setCurrentNumber(newCurrentNumber);
            Water.deleteAll(Water.class);
            water.save();
        } else {
            double percent = (double) water.getStep() / defaultWaterCount;
            int step = (int) Math.round(percent * 100);
            int newCurrentNumber = water.getCurrentNumber() + water.getStep();
            waveLoadingView.setCenterTitle(String.valueOf(newCurrentNumber) + "/" + String.valueOf((int) defaultWaterCount));
            waveLoadingView.setProgressValue(waveLoadingView.getProgressValue() + step);

            water.setCurrentNumber(newCurrentNumber);
            Water.deleteAll(Water.class);
            water.save();
        }


        if (waveLoadingView.getProgressValue() >= 100) {
            waveLoadingView.setCenterTitle("");
            isFullWater = true;
            ivMainScreenCollapsingCancelWater.setVisibility(View.GONE);
            ivCollapsingMainCompleteWater.setVisibility(View.VISIBLE);
            ivCollapsingMainCompleteWater.startAnimation(animWaterComplete);
        }

    }

    private void backCountOfWater() {
        double defaultWaterCount = 2000;

        if (profile != null) {
            double percent = (double) water.getStep() / (double) profile.getWaterCount();
            int step = (int) Math.round(percent * 100);
            int newCurrentNumber = water.getCurrentNumber() - water.getStep();
            waveLoadingView.setCenterTitle(String.valueOf(newCurrentNumber) + "/" + String.valueOf(profile.getWaterCount()));
            waveLoadingView.setProgressValue(waveLoadingView.getProgressValue() - step);

            water.setCurrentNumber(newCurrentNumber);
            Water.deleteAll(Water.class);
            water.save();
        } else {
            double percent = (double) water.getStep() / defaultWaterCount;
            int step = (int) Math.round(percent * 100);
            int newCurrentNumber = water.getCurrentNumber() - water.getStep();
            waveLoadingView.setCenterTitle(String.valueOf(newCurrentNumber) + "/" + String.valueOf((int) defaultWaterCount));
            waveLoadingView.setProgressValue(waveLoadingView.getProgressValue() - step);

            water.setCurrentNumber(newCurrentNumber);
            Water.deleteAll(Water.class);
            water.save();
        }
    }

    private void fillWaterView(int day, int month, int year, @Nullable Profile profile) {
        final int DEFAULT_FIRST_STEP = 200;
        final int DEFAULT_FIRST_MAX = 2000;

        if (Water.count(Water.class) != 1) {
            water = new Water(day, month, year, DEFAULT_FIRST_STEP, 0);
            water.save();
        } else {
            water = Water.last(Water.class);
            if (water.getDay() < day || water.getMonth() < month || water.getYear() < year) {
                water = Water.last(Water.class);
                water.setDay(day);
                water.setMonth(month);
                water.setYear(year);
                water.setCurrentNumber(0);
                Water.deleteAll(Water.class);

                water.save();
            }
        }
        int maxWater = DEFAULT_FIRST_MAX;
        if (profile != null) {
            maxWater = profile.getWaterCount();
        }

        waveLoadingView.setCenterTitle(String.valueOf(water.getCurrentNumber()) + "/" + String.valueOf(maxWater));
        double percent = (double) water.getCurrentNumber() / (double) maxWater;
        double progress = percent * 100;
        waveLoadingView.setProgressValue((int) Math.round(progress));

        if (waveLoadingView.getProgressValue() >= 100) {
            isFullWater = true;
            waveLoadingView.setCenterTitle("");
            ivCollapsingMainCompleteWater.setVisibility(View.VISIBLE);
            ivMainScreenCollapsingCancelWater.setVisibility(View.GONE);
        }


    }

    private void bindCircleProgressBars(int day, int month, int year, @Nullable Profile profile) {
        Log.e("LOL", "Start");

        if (profile != null) {
            apCollapsingKcal.setMax(profile.getMaxKcal());
            apCollapsingProt.setMax(profile.getMaxProt());
            apCollapsingCarbo.setMax(profile.getMaxCarbo());
            apCollapsingFat.setMax(profile.getMaxFat());
        } else {
            apCollapsingKcal.setMax(2000);
            apCollapsingProt.setMax(100);
            apCollapsingCarbo.setMax(100);
            apCollapsingFat.setMax(100);
        }


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
        Log.e("LOL", "Finish");

        apCollapsingKcal.setProgress(kcal);
        apCollapsingProt.setProgress(prot);
        apCollapsingCarbo.setProgress(carbo);
        apCollapsingFat.setProgress(fat);


        if (apCollapsingKcal.getMax() < kcal) {
            apCollapsingKcal.setFinishedStrokeColor(getResources().getColor(R.color.over_eat_color));
            apCollapsingKcal.setSuffixText("-" + String.valueOf(kcal - apCollapsingKcal.getMax()));
        } else {
            apCollapsingKcal.setFinishedStrokeColor(getResources().getColor(R.color.kcalColor));
            apCollapsingKcal.setSuffixText("+" + String.valueOf(apCollapsingKcal.getMax() - kcal));
        }
        if (apCollapsingCarbo.getMax() < carbo) {
            apCollapsingCarbo.setFinishedStrokeColor(getResources().getColor(R.color.over_eat_color));
            tvCircleProgressCarbo.setText("избыток  " + String.valueOf(carbo - apCollapsingCarbo.getMax()) + " г");
        } else {
            apCollapsingCarbo.setFinishedStrokeColor(getResources().getColor(R.color.carboColor));
            tvCircleProgressCarbo.setText("осталось  " + String.valueOf(apCollapsingCarbo.getMax() - carbo) + " г");
        }
        if (apCollapsingFat.getMax() < fat) {
            apCollapsingFat.setFinishedStrokeColor(getResources().getColor(R.color.over_eat_color));
            tvCircleProgressFat.setText("избыток  " + String.valueOf(fat - apCollapsingFat.getMax()) + " г");
        } else {
            apCollapsingFat.setFinishedStrokeColor(getResources().getColor(R.color.fatColor));
            tvCircleProgressFat.setText("осталось  " + String.valueOf(apCollapsingFat.getMax() - fat) + " г");
        }
        if (apCollapsingProt.getMax() < prot) {
            apCollapsingProt.setFinishedStrokeColor(getResources().getColor(R.color.over_eat_color));
            tvCircleProgressProt.setText("избыток " + String.valueOf(prot - apCollapsingProt.getMax()) + " г");
        } else {
            apCollapsingProt.setFinishedStrokeColor(getResources().getColor(R.color.protColor));
            tvCircleProgressProt.setText("осталось " + String.valueOf(apCollapsingProt.getMax() - prot) + " г");
        }

    }

    private void additionOneToSharedPreference() {
        countOfRun = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = countOfRun.edit();
        editor.putInt(TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG, countOfRun.getInt(TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG, COUNT_OF_RUN) + 1);
        editor.commit();

    }


    private void checkFirstRun() {
        if (countOfRun.getInt(TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG, COUNT_OF_RUN) == 3) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog alertDialog = builder.create();
            View view = getLayoutInflater().inflate(R.layout.alert_dialog_grade, null);

            Animation movFromLeft = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_moving_from_left);
            Animation movOutToRight = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_moving_out_to_right);
            Animation movFromRight = AnimationUtils.loadAnimation(MainActivity.this, R.anim.moving_from_right);

            RatingBar ratingBar = view.findViewById(R.id.ratingBar);
            EditText edtReport = view.findViewById(R.id.edtRatingReport);
            TextView tvThank = view.findViewById(R.id.tvForGrade);
            Button btnGradeClose = view.findViewById(R.id.btnGradeClose);
            Button btnGradeLate = view.findViewById(R.id.btnGradeLate);
            Button btnGradeSend = view.findViewById(R.id.btnGradeSend);

            btnGradeClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    YandexMetrica.reportEvent("Отказ в оценке");
                    alertDialog.cancel();
                }
            });

            btnGradeLate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countOfRun = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = countOfRun.edit();
                    editor.putInt(TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG, 0);
                    editor.commit();
                    YandexMetrica.reportEvent("Оценка отложена");
                    alertDialog.cancel();
                }
            });

            btnGradeSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "sav@wsoteam.com", null));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Отзыв");
                    intent.putExtra(Intent.EXTRA_TEXT, edtReport.getText().toString());
                    startActivity(Intent.createChooser(intent, "Send Email"));
                    alertDialog.cancel();
                }
            });

            btnGradeSend.setVisibility(View.GONE);
            tvThank.setVisibility(View.GONE);
            edtReport.setVisibility(View.GONE);
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    if (v >= 4) {
                        YandexMetrica.reportEvent("Переход в ГП для оценки");
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("market://details?id=" + MainActivity.this.getPackageName()));
                        startActivity(intent);
                        alertDialog.cancel();
                        isFiveStarSend = true;
                    } else {
                        if (edtReport.getVisibility() == View.GONE) {
                            edtReport.setVisibility(View.VISIBLE);
                            edtReport.startAnimation(movFromLeft);
                            ratingBar.startAnimation(movOutToRight);
                            ratingBar.setVisibility(View.GONE);
                            tvThank.setVisibility(View.VISIBLE);
                            tvThank.startAnimation(movFromLeft);
                            btnGradeSend.setVisibility(View.VISIBLE);
                            btnGradeSend.startAnimation(movFromRight);
                        }
                    }
                }
            });
            alertDialog.setView(view);
            alertDialog.show();
        }
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
            cardView.startAnimation(animChangeScale);
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
