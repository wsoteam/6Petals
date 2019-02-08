package com.wsoteam.diet.BranchProfile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wsoteam.diet.POJOForDB.DiaryData;
import com.wsoteam.diet.POJOProfile.Profile;
import com.wsoteam.diet.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityProfile extends AppCompatActivity {
    private CircleImageView civProfile;
    private ImageButton ibProfileEdit, ibProfileBack;
    private TextView tvProfileName, tvProfileOld, tvProfileGender,
            tvProfileLifestyle, tvProfileWeight, tvProfileLevel,
            tvProfileHeight, tvProfileFirstEnter, tvProfileChangeWeight,
            tvProfileMaxKcal, tvProfileMaxWater, tvProfileMaxFat, tvProfileMaxCarbo,
            tvProfileMaxProt;
    private RecyclerView rvProfileMainParams;
    private SharedPreferences firstEnter;
    private ImageView ivProfileChangeWeight;
    private final String FIRST_ENTER = "FIRST_ENTER";
    private int[] arrayOfDrawabaleArrowForChangeWeight = new int[]{R.drawable.ic_decrease_weight, R.drawable.ic_increase_weight};

    @Override
    protected void onResume() {
        super.onResume();
        if (Profile.count(Profile.class) == 1) {
            Profile profile = Profile.last(Profile.class);
            fillViewsIfProfileNotNull(profile);
            Handler bindHandler = new Handler(Looper.getMainLooper());
            bindHandler.post(new Runnable() {
                @Override
                public void run() {
                    double difference = getDifferenceWeight(profile);

                    if (difference > 0){
                        tvProfileChangeWeight.setText("+" + String.valueOf(difference) + " " + getResources().getString(R.string.kg));
                        Glide.with(ActivityProfile.this)
                                .load(arrayOfDrawabaleArrowForChangeWeight[1]).into(ivProfileChangeWeight);
                    }else{
                        tvProfileChangeWeight.setText(String.valueOf(difference) + " " + getResources().getString(R.string.kg));
                    }
                }
            });

        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        civProfile = findViewById(R.id.civProfile);
        ibProfileEdit = findViewById(R.id.ibProfileEdit);
        ibProfileBack = findViewById(R.id.ibProfileBack);
        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfileOld = findViewById(R.id.tvProfileOld);
        tvProfileGender = findViewById(R.id.tvProfileGender);
        tvProfileLifestyle = findViewById(R.id.tvProfileLifestyle);
        tvProfileWeight = findViewById(R.id.tvProfileWeight);
        tvProfileLevel = findViewById(R.id.tvProfileLevel);
        tvProfileHeight = findViewById(R.id.tvProfileHeight);
        tvProfileFirstEnter = findViewById(R.id.tvProfileFirstEnter);
        tvProfileChangeWeight = findViewById(R.id.tvProfileChangeWeight);
        tvProfileMaxKcal = findViewById(R.id.tvProfileMaxKcal);
        tvProfileMaxWater = findViewById(R.id.tvProfileMaxWater);
        tvProfileMaxFat = findViewById(R.id.tvProfileMaxFat);
        tvProfileMaxCarbo = findViewById(R.id.tvProfileMaxCarbo);
        tvProfileMaxProt = findViewById(R.id.tvProfileMaxProt);
        ivProfileChangeWeight = findViewById(R.id.ivProfileChangeWeight);

        rvProfileMainParams = findViewById(R.id.rvProfileMainParams);
        rvProfileMainParams.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvProfileMainParams.setAdapter(new ItemAdapter());

        firstEnter = getSharedPreferences(FIRST_ENTER, MODE_PRIVATE);
        tvProfileFirstEnter.setText(firstEnter.getString(FIRST_ENTER, "-"));
        Log.e("LOL", firstEnter.getString(FIRST_ENTER, "-"));


        ibProfileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ibProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityProfile.this, ActivityEditProfile.class);
                startActivity(intent);
            }
        });

    }

    private void fillViewsIfProfileNotNull(Profile profile) {

        tvProfileName.setText(profile.getFirstName() + " " + profile.getLastName());
        tvProfileOld.setText(String.valueOf(profile.getAge()));
        if (profile.isFemale()) {
            tvProfileGender.setText("Женщина");
        } else {
            tvProfileGender.setText("Мужчина");
        }
        tvProfileLifestyle.setText(profile.getExerciseStress());
        tvProfileWeight.setText(String.valueOf(profile.getWeight()) + " " + getString(R.string.kg));
        tvProfileLevel.setText(profile.getDifficultyLevel());
        tvProfileHeight.setText(String.valueOf(profile.getHeight()) + " " + getString(R.string.cm));
        tvProfileMaxKcal.setText(String.valueOf(profile.getMaxKcal()));
        tvProfileMaxCarbo.setText(String.valueOf(profile.getMaxCarbo()));
        tvProfileMaxFat.setText(String.valueOf(profile.getMaxFat()));
        tvProfileMaxProt.setText(String.valueOf(profile.getMaxProt()));
        tvProfileMaxWater.setText(String.valueOf(profile.getWaterCount()));
        if (profile.getDifficultyLevel().equals(getString(R.string.dif_level_easy))) {
            tvProfileLevel.setTextColor(getResources().getColor(R.color.level_easy));
        } else {
            if (profile.getDifficultyLevel().equals(getString(R.string.dif_level_normal))) {
                tvProfileLevel.setTextColor(getResources().getColor(R.color.level_normal));
            } else {
                tvProfileLevel.setTextColor(getResources().getColor(R.color.level_hard));
            }
        }
        if (!profile.getPhotoUrl().equals("default")) {
            Uri uri = Uri.parse(profile.getPhotoUrl());
            Glide.with(this).load(uri).into(civProfile);
        }
    }

    private double getDifferenceWeight(Profile profile) {
        double difference = 0;
        if (DiaryData.count(DiaryData.class) > 0) {
            List<DiaryData> diaryDataArrayList = DiaryData.listAll(DiaryData.class);

            if (diaryDataArrayList.size() > 1){

                DiaryData[] arrayForSort = new DiaryData[diaryDataArrayList.size()];

                for (int i = 0; i < diaryDataArrayList.size(); i++) {
                    arrayForSort[i] = diaryDataArrayList.get(i);
                }

                int lenght = arrayForSort.length;
                for (int i = 0; i < lenght - 1; i++) {
                    for (int j = 0; j < lenght - i - 1; j++) {
                        if (arrayForSort[j].getOwnId() < arrayForSort[j + 1].getOwnId()) {
                            DiaryData temp = arrayForSort[j];
                            arrayForSort[j] = arrayForSort[j + 1];
                            arrayForSort[j + 1] = temp;
                        }
                    }

                    difference = arrayForSort[0].getWeight() - profile.getWeight();
                }
            }else{
                difference = diaryDataArrayList.get(0).getWeight() - profile.getWeight();
            }

        }

        return difference;
    }

    private class ItemHolder extends RecyclerView.ViewHolder{
        public ItemHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_main_params_profile, viewGroup, false));
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder>{
        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ActivityProfile.this);
            return new ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 9;
        }
    }

}
