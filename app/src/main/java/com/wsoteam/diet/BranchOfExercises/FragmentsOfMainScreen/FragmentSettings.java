package com.wsoteam.diet.BranchOfExercises.FragmentsOfMainScreen;

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

import com.wsoteam.diet.R;


public class FragmentSettings extends Fragment {
    CardView cvRate, cvPrivacy, cvShare;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ex_fragment_settings, container, false);

        cvPrivacy = view.findViewById(R.id.ex_cvPrivacy);
        cvRate = view.findViewById(R.id.ex_cvRate);
        cvShare = view.findViewById(R.id.ex_cvShare);

        cvPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrivacyPage();
            }
        });

        cvRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateApp();
            }
        });

        cvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareUrlOfApp();
            }
        });


        return view;
    }

    private void shareUrlOfApp() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.accompanying_text)
                + "\n"
                +"https://play.google.com/store/apps/details?id="
                + getActivity().getPackageName());
        startActivity(intent);
    }

    private void rateApp() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + getActivity().getPackageName()));
        startActivity(intent);
    }

    private void openPrivacyPage() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getResources().getString(R.string.url_gdpr)));
        startActivity(intent);
    }
}
