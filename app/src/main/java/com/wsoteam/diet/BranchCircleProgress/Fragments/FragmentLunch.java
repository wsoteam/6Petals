package com.wsoteam.diet.BranchCircleProgress.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wsoteam.diet.POJOsCircleProgress.Eating.Lunch;
import com.wsoteam.diet.POJOsCircleProgress.Eating.Snack;
import com.wsoteam.diet.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentLunch extends Fragment {
    private RecyclerView recyclerView;
    private List<Lunch> lunchList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lunch, container, false);

        lunchList = Lunch.listAll(Lunch.class);
        Log.e("LOL", String.valueOf(lunchList.size()));

        recyclerView = view.findViewById(R.id.rvEatingLunch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new LunchItemAdapter((ArrayList<Lunch>) lunchList));

        return view;
    }

    private class LunchItemHolder extends RecyclerView.ViewHolder {
        private TextView tvEatingItemName, tvEatingItemFat, tvEatingItemCarbo, tvEatingItemProt, tvEatingItemKcal, tvEatingItemWeight;
        private ImageView ivImage;

        public LunchItemHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_eating_diary_lists, viewGroup, false));

            tvEatingItemName = itemView.findViewById(R.id.tvEatingItemName);
            tvEatingItemFat = itemView.findViewById(R.id.tvEatingItemFat);
            tvEatingItemCarbo = itemView.findViewById(R.id.tvEatingItemCarbo);
            tvEatingItemProt = itemView.findViewById(R.id.tvEatingItemProt);
            tvEatingItemKcal = itemView.findViewById(R.id.tvEatingItemKcal);
            tvEatingItemWeight = itemView.findViewById(R.id.tvEatingItemWeight);
            ivImage = itemView.findViewById(R.id.ivImage);

        }

        public void bind(Lunch lunch) {
            tvEatingItemName.setText(String.valueOf(lunch.getName()));
            tvEatingItemFat.setText(String.valueOf(lunch.getFat()));
            tvEatingItemCarbo.setText(String.valueOf(lunch.getCarbohydrates()));
            tvEatingItemProt.setText(String.valueOf(lunch.getProtein()));
            tvEatingItemKcal.setText(String.valueOf(lunch.getCalories()));
            tvEatingItemWeight.setText(String.valueOf(lunch.getWeight()));

            Glide.with(getActivity()).load(lunch.getUrlOfImages()).into(ivImage);

        }
    }

    private class LunchItemAdapter extends RecyclerView.Adapter<LunchItemHolder> {
        ArrayList<Lunch> lunchList;

        public LunchItemAdapter(ArrayList<Lunch> lunchList) {
            this.lunchList = lunchList;
        }

        @NonNull
        @Override
        public LunchItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new LunchItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull LunchItemHolder holder, int position) {
            holder.bind(lunchList.get(position));
        }

        @Override
        public int getItemCount() {
            return lunchList.size();
        }
    }
}
