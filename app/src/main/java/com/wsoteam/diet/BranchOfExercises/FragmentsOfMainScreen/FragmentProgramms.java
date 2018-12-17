package com.wsoteam.diet.BranchOfExercises.FragmentsOfMainScreen;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.startad.lib.SADView;
import com.wsoteam.diet.BranchOfExercises.ActivitiesProgramm.ActivityListOfTraining;
import com.wsoteam.diet.BranchOfExercises.ObjectHolder;
import com.wsoteam.diet.POJOSExercises.GlobalObject;
import com.wsoteam.diet.POJOSExercises.Programm;
import com.wsoteam.diet.R;

import java.util.ArrayList;



public class FragmentProgramms extends Fragment {
    private RecyclerView rvListOfProgramm;
    private GlobalObject globalObject;

    protected SADView sadView;
    private String AD_ID = "5bd2e325cd3e9671008b4568";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ex_fragment_programms, container, false);
        rvListOfProgramm = view.findViewById(R.id.ex_rvListOfHomeFragment);
        sadView = new SADView(getActivity(), AD_ID);

        globalObject = ObjectHolder.getGlobalObject();
        rvListOfProgramm.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvListOfProgramm.setAdapter(new ProgrammAdapter((ArrayList<Programm>) globalObject.getProgrammList()));

        return view;
    }

    private class ProgrammViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitleOfProgramm, tvCountOfProgrammInside;
        ImageView imageView;
        FrameLayout frameLayout;

        public ProgrammViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
            super(layoutInflater.inflate(R.layout.ex_item_fragment_programms_main_screen, parent, false));

            tvTitleOfProgramm = itemView.findViewById(R.id.ex_tvNameHomeList);
            tvCountOfProgrammInside = itemView.findViewById(R.id.ex_tvCountOfProgrammInside);
            frameLayout = itemView.findViewById(R.id.ex_flItemOfProgrammList);
            tvTitleOfProgramm.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "asProgramMainScreen.ttf"));
            imageView = itemView.findViewById(R.id.ivBackgroundItemListOfProgrammMainScreen);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ActivityListOfTraining.class);
            intent.putExtra(ActivityListOfTraining.TAG, getAdapterPosition() - 1);
            startActivity(intent);
        }

        public void bind(Programm programm, boolean isAd) {
            if (isAd){
                frameLayout.addView(sadView);
                sadView.loadAd(SADView.LANGUAGE_RU);
            }else {
                Glide.with(getActivity()).load(programm.getImg_url()).into(imageView);
                tvTitleOfProgramm.setText(programm.getTitle());
                tvCountOfProgrammInside.setText(String.valueOf(programm.getTrainingList().size()));
            }
        }
    }

    private class ProgrammAdapter extends RecyclerView.Adapter<ProgrammViewHolder> {
        ArrayList<Programm> programmArrayList;

        public ProgrammAdapter(ArrayList<Programm> programmArrayList) {
            this.programmArrayList = programmArrayList;
        }

        @NonNull
        @Override
        public ProgrammViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ProgrammViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ProgrammViewHolder holder, int position) {
            if (position == 0){
                holder.bind(programmArrayList.get(position + 1), true);
            }else {
                holder.bind(programmArrayList.get(position - 1), false);
            }
        }

        @Override
        public int getItemCount() {
            return programmArrayList.size() + 1;
        }
    }
}
