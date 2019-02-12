package com.wsoteam.diet.BranchOfDescription;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.wsoteam.diet.Config;
import com.wsoteam.diet.OtherActivity.ActivityAboutSetingsNotifications;
import com.wsoteam.diet.OtherActivity.ActivitySettings;
import com.wsoteam.diet.R;
import com.yandex.metrica.YandexMetrica;

public class FragmentSetting extends Fragment {

    private CardView cvRate, cvPrivacy, cvShare, cvNotification;
    private Switch switchRewrite;
    private SharedPreferences isRewriteProfileData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_settings, container, false);

        cvPrivacy = view.findViewById(R.id.cvPrivacy);
        cvRate = view.findViewById(R.id.cvRate);
        cvShare = view.findViewById(R.id.cvShare);
        cvNotification = view.findViewById(R.id.cvOpenAutoLaunch);
        switchRewrite =view.findViewById(R.id.switchRewrite);

        handleSwitch();

        cvRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + getActivity().getPackageName()));
                startActivity(intent);
            }
        });

        cvPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getResources().getString(R.string.url_gdpr)));
                startActivity(intent);
            }
        });

        cvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.accompanying_text)
                        + "\n"
                        + "https://play.google.com/store/apps/details?id="
                        + getActivity().getPackageName());
                startActivity(intent);
            }
        });

        cvNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ActivityAboutSetingsNotifications.class);
                startActivity(intent);
            }
        });

        YandexMetrica.reportEvent("Открыт фрагмент: Настройки");
        return view;
    }

    private void handleSwitch() {
        isRewriteProfileData = getActivity().getSharedPreferences(Config.TAG_OF_REWRITE, Context.MODE_PRIVATE);

        if (isRewriteProfileData.getInt(Config.TAG_OF_REWRITE, Config.NOT_ENTER_EARLY) == Config.REWRITE_PROFILE){
            switchRewrite.setChecked(true);
        }
        SharedPreferences.Editor editor = isRewriteProfileData.edit();

        switchRewrite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editor.putInt(Config.TAG_OF_REWRITE, Config.REWRITE_PROFILE).commit();
                }else{
                    editor.putInt(Config.TAG_OF_REWRITE, Config.NOT_REWRITE_PROFILE).commit();
                }
            }
        });
    }

}
