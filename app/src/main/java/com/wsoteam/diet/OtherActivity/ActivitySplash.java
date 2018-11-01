package com.wsoteam.diet.OtherActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wsoteam.diet.Config;
import com.wsoteam.diet.MainScreen.MainActivity;
import com.wsoteam.diet.ObjectHolder;
import com.wsoteam.diet.POJOS.GlobalObject;
import com.wsoteam.diet.R;

public class ActivitySplash extends AppCompatActivity {
    TextView tvTitle, tvSubtitle;
    ImageView ivLoading;
    Animation animationRotate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvSubtitle = findViewById(R.id.tvSubtitleSplashScreen);
        tvTitle = findViewById(R.id.tvTitleSplashScreen);
        ivLoading = findViewById(R.id.ivLoadingIcon);
        tvTitle.setTypeface(Typeface.createFromAsset(getAssets(), "main_font.ttf"));
        tvSubtitle.setTypeface(Typeface.createFromAsset(getAssets(), "main_font.ttf"));
        animationRotate = AnimationUtils.loadAnimation(this, R.anim.animation_rotate);
        ivLoading.startAnimation(animationRotate);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Config.NAME_OF_ENTITY_FOR_DB);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ObjectHolder objectHolder = new ObjectHolder();
                objectHolder.bindObjectWithHolder(dataSnapshot.getValue(GlobalObject.class));
                Intent intent = new Intent(ActivitySplash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
