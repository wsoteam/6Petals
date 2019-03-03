package com.wsoteam.diet.OtherActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wsoteam.diet.Authenticate.ActivityAuthenticate;
import com.wsoteam.diet.Config;
import com.wsoteam.diet.IPCheck.IPCheckApi;
import com.wsoteam.diet.IPCheck.IPCheckObject;
import com.wsoteam.diet.MainScreen.MainActivity;
import com.wsoteam.diet.ObjectHolder;
import com.wsoteam.diet.POJOS.GlobalObject;
import com.wsoteam.diet.R;
import com.wsoteam.diet.TestFillDB;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivitySplash extends AppCompatActivity {
    private TextView tvTitle;
    private ImageView ivLoading;
    private Animation animationRotate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvTitle = findViewById(R.id.tvTitleSplashScreen);
        ivLoading = findViewById(R.id.ivLoadingIcon);
        tvTitle.setTypeface(Typeface.createFromAsset(getAssets(), "main_font.ttf"));
        animationRotate = AnimationUtils.loadAnimation(this, R.anim.animation_rotate);
        ivLoading.startAnimation(animationRotate);


        if (!hasConnection(this)) {
            Toast.makeText(this, R.string.check_internet_connection, Toast.LENGTH_SHORT).show();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Config.NAME_OF_ENTITY_FOR_DB);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ObjectHolder objectHolder = new ObjectHolder();
                objectHolder.bindObjectWithHolder(dataSnapshot.getValue(GlobalObject.class));

                checkCountryAndRunNextActivity();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private boolean hasConnection(Context context) {
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

    private void checkCountryAndRunNextActivity(){
        Intent intent = new Intent(ActivitySplash.this, ActivityAuthenticate.class);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ip-api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IPCheckApi checkApi = retrofit.create(IPCheckApi.class);
        Call<IPCheckObject> objectCall = checkApi.getResponse();
        objectCall.enqueue(new Callback<IPCheckObject>() {
            @Override
            public void onResponse(Call<IPCheckObject> call, Response<IPCheckObject> response) {
                intent.putExtra("MainActivity", response.body().getCountryCode());
                ivLoading.clearAnimation();
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<IPCheckObject> call, Throwable t) {
                intent.putExtra("MainActivity", "by");
                ivLoading.clearAnimation();
                startActivity(intent);
                finish();
            }
        });
    }


}
