package com.wsoteam.diet.BranchCircleProgress.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wsoteam.diet.POJOsCircleProgress.Eating.Breakfast;
import com.wsoteam.diet.R;

import java.util.List;

public class FragmentBreakfast extends Fragment {
    private List<Breakfast> breakfastList;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breakfast, container, false);
        breakfastList = Breakfast.listAll(Breakfast.class);
        return view;
    }
}
