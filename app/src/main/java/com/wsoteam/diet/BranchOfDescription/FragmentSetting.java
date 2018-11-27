package com.wsoteam.diet.BranchOfDescription;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wsoteam.diet.OtherActivity.ActivityAboutSetingsNotifications;
import com.wsoteam.diet.OtherActivity.ActivitySettings;
import com.wsoteam.diet.R;

public class FragmentSetting extends Fragment {

    CardView cvRate, cvPrivacy, cvShare, cvNotification;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_settings,container,false);

        cvPrivacy = view.findViewById(R.id.cvPrivacy);
        cvRate = view.findViewById(R.id.cvRate);
        cvShare = view.findViewById(R.id.cvShare);
        cvNotification = view.findViewById(R.id.cvOpenAutoLaunch);


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

        return view;
    }

}
