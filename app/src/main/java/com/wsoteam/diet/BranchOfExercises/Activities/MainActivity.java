package com.wsoteam.diet.BranchOfExercises.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wsoteam.diet.BranchOfExercises.FragmentsOfMainScreen.FragmentFavorites;
import com.wsoteam.diet.BranchOfExercises.FragmentsOfMainScreen.FragmentPartsOfBody;
import com.wsoteam.diet.BranchOfExercises.FragmentsOfMainScreen.FragmentProgramms;
import com.wsoteam.diet.BranchOfExercises.FragmentsOfMainScreen.FragmentSettings;
import com.wsoteam.diet.BranchOfExercises.FragmentsOfMainScreen.FragmentsArticles;
import com.wsoteam.diet.BranchOfExercises.ObjectHolder;
import com.wsoteam.diet.Config;
import com.wsoteam.diet.POJOSExercises.GlobalObject;
import com.wsoteam.diet.R;

import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {

    private int COUNT_OF_RUN = 0;
    private String TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG = "COUNT_OF_RUN";
    private SharedPreferences countOfRun;
    private ImageView loadingBar;
    private Animation animationRotate;


    private SharedPreferences numberOfRun;
    private static final String TAG_COUNT_OF_RUN = "TAG_COUNT_OF_RUN";
    private final int DEFAULT_COUNT_OF_RUNS = 0;


    private ArrayList<Fragment> listOfFragments;
    private ViewPager mViewPager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_ex:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_favorites:
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_articles:
                    mViewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_settings:
                    mViewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_activity_main);

        MobileAds.initialize(this, getResources().getString(R.string.admob_id));
        AdRequest request = new AdRequest.Builder().build();

        loadingBar = findViewById(R.id.ex_ivLoadingCircle);
        animationRotate = AnimationUtils.loadAnimation(this, R.anim.animation_rotate);
        loadingBar.startAnimation(animationRotate);

        if (!hasConnection(this)) {
            Toast.makeText(this, R.string.check_connection, Toast.LENGTH_SHORT).show();
        }

        final BottomNavigationView navigation = findViewById(R.id.ex_navigation);
        mViewPager = findViewById(R.id.ex_vpMainActivity);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //WorkWithFB.fill();
        loadDataFromFireBase();

    }

    private void loadDataFromFireBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Config.EXERCISES_NAME_OF_ENTITY_FOR_DB);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ObjectHolder objectHolder = new ObjectHolder();
                objectHolder.createObjFromGirebase(dataSnapshot.getValue(GlobalObject.class));
                listOfFragments = new ArrayList<>();
                listOfFragments.add(new FragmentProgramms());
                listOfFragments.add(new FragmentPartsOfBody());
                listOfFragments.add(new FragmentFavorites());
                listOfFragments.add(new FragmentsArticles());
                listOfFragments.add(new FragmentSettings());

                mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                    @Override
                    public Fragment getItem(int position) {
                        return listOfFragments.get(position);
                    }

                    @Override
                    public int getCount() {
                        return listOfFragments.size();
                    }
                });
                loadingBar.clearAnimation();
                loadingBar.setVisibility(View.GONE);
                additionOneToSharedPreference();
                checkFirstRun();
                //showGDPRIfFirstRun();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }

    private void checkFirstRun() {
        if (countOfRun.getInt(TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG, COUNT_OF_RUN) == 3) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.ex_grade_alert_dialog, null);
            builder.setView(view);
            builder.setNeutralButton(R.string.Late, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    countOfRun = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = countOfRun.edit();
                    editor.putInt(TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG, 0);
                    editor.commit();
                }
            });
            builder.setPositiveButton(R.string.Grade, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=" + MainActivity.this.getPackageName()));
                    startActivity(intent);
                }
            });
            builder.setNegativeButton(R.string.Never, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();
        }
    }

    private void additionOneToSharedPreference() {
        countOfRun = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = countOfRun.edit();
        editor.putInt(TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG, countOfRun.getInt(TAG_COUNT_OF_RUN_FOR_ALERT_DIALOG, COUNT_OF_RUN) + 1);
        editor.commit();

    }


    public void showGDPRIfFirstRun() {
        numberOfRun = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = numberOfRun.edit();
        editor.putInt(TAG_COUNT_OF_RUN, numberOfRun.getInt(TAG_COUNT_OF_RUN, DEFAULT_COUNT_OF_RUNS) + 1);
        editor.commit();


        if (numberOfRun.getInt(TAG_COUNT_OF_RUN, DEFAULT_COUNT_OF_RUNS) == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.title_gdpr);
            builder.setMessage(R.string.body_gdpr);
            builder.setNeutralButton(R.string.yes_gdpr, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    numberOfRun = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = numberOfRun.edit();
                    editor.putInt(TAG_COUNT_OF_RUN, 2);
                    editor.commit();
                }
            });
            builder.setPositiveButton(R.string.open_gdpr, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(getResources().getString(R.string.url_gdpr)));
                    startActivity(intent);
                }
            });


            builder.show();
        }
    }


}
