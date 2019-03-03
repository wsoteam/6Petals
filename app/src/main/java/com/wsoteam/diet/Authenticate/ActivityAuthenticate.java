package com.wsoteam.diet.Authenticate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wsoteam.diet.R;

public class ActivityAuthenticate extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager fm;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.auth_frame_layout);
        if (fragment == null) {
            fragment = new FragmentAuthFerst();
            fm.beginTransaction()
                    .add(R.id.auth_frame_layout, fragment)
                    .commit();
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.auth_first_btn_registration:
                Toast.makeText(this, "Вы нажали на кнопку Регистрация",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.auth_first_btn_signin:
                Toast.makeText(this, "Вы нажали на кнопку Войти",
                        Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
