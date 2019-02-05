package com.wsoteam.diet.BranchProfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wsoteam.diet.POJOProfile.Profile;
import com.wsoteam.diet.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityProfile extends AppCompatActivity {
    private CircleImageView civProfile;
    private ImageButton ibProfileEdit, ibProfileBack;
    private TextView tvProfileName, tvProfileOld, tvProfileGender,
            tvProfileLifestyle, tvProfileWeight, tvProfileLevel,
            tvProfileHeight, tvProfileFirstEnter, tvProfileChangeWeight,
            tvProfileMaxKcal, tvProfileMaxWater, tvProfileMaxFat, tvProfileMaxCarbo,
            tvProfileMaxProt;

    @Override
    protected void onResume() {
        super.onResume();
        if (Profile.count(Profile.class) == 1) {
            fillViewsIfProfileNotNull();
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

    private void fillViewsIfProfileNotNull() {
        Profile profile = Profile.last(Profile.class);

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
        tvProfileFirstEnter.setText(profile.getFirstEnter());
        tvProfileMaxKcal.setText(String.valueOf(profile.getMaxKcal()));
        tvProfileMaxCarbo.setText(String.valueOf(profile.getMaxCarbo()));
        tvProfileMaxFat.setText(String.valueOf(profile.getMaxFat()));
        tvProfileMaxProt.setText(String.valueOf(profile.getMaxProt()));
        tvProfileMaxWater.setText(String.valueOf(profile.getWaterCount()));
        //tvProfileChangeWeight.setText(profile.);
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
            Glide.with(this).load(profile.getPhotoUrl()).into(civProfile);
        }
    }

}
