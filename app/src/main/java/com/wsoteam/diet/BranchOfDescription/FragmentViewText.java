package com.wsoteam.diet.BranchOfDescription;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wsoteam.diet.R;

public class FragmentViewText extends Fragment {

    private  final static String KEY_FOR_BUNDLE = "fragment_View_Text";

    public static FragmentViewText newInstance(String body) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_FOR_BUNDLE, body);
        FragmentViewText fragmentViewText = new FragmentViewText();
        fragmentViewText.setArguments(bundle);
        return fragmentViewText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_view_text,container, false);

        TextView textView = view.findViewById(R.id.tvDescriptionViewText);

        textView.setText(Html.fromHtml((String)getArguments().getSerializable(KEY_FOR_BUNDLE)));

        return view;
    }
}
