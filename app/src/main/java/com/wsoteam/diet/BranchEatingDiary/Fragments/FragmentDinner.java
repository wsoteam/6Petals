package com.wsoteam.diet.BranchEatingDiary.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wsoteam.diet.POJOsCircleProgress.Eating.Dinner;
import com.wsoteam.diet.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentDinner extends Fragment {
    private List<Dinner> dinnerList;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dinner, container, false);

        dinnerList = Dinner.listAll(Dinner.class);

        recyclerView = view.findViewById(R.id.rvEatingDinner);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new DinnerItemAdapter((ArrayList<Dinner>) dinnerList));

        return view;
    }

    private class DinnerItemHolder extends RecyclerView.ViewHolder {
        private TextView tvEatingItemName, tvEatingItemFat, tvEatingItemCarbo, tvEatingItemProt, tvEatingItemKcal, tvEatingItemWeight;
        private ImageView ivImage;

        public DinnerItemHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_eating_diary_lists, viewGroup, false));

            tvEatingItemName = itemView.findViewById(R.id.tvEatingItemName);
            tvEatingItemFat = itemView.findViewById(R.id.tvEatingItemFat);
            tvEatingItemCarbo = itemView.findViewById(R.id.tvEatingItemCarbo);
            tvEatingItemProt = itemView.findViewById(R.id.tvEatingItemProt);
            tvEatingItemKcal = itemView.findViewById(R.id.tvEatingItemKcal);
            tvEatingItemWeight = itemView.findViewById(R.id.tvEatingItemWeight);
            ivImage = itemView.findViewById(R.id.ivImage);

        }

        public void bind(Dinner dinner) {
            tvEatingItemName.setText(String.valueOf(dinner.getName()));
            tvEatingItemFat.setText(String.valueOf(dinner.getFat())+ " г");
            tvEatingItemCarbo.setText(String.valueOf(dinner.getCarbohydrates())+ " г");
            tvEatingItemProt.setText(String.valueOf(dinner.getProtein())+ " г");
            tvEatingItemKcal.setText(String.valueOf(dinner.getCalories()) + " ккал");
            tvEatingItemWeight.setText(String.valueOf(dinner.getWeight()) + " г");

            Glide.with(getActivity()).load(dinner.getUrlOfImages()).into(ivImage);

        }
    }

    private class DinnerItemAdapter extends RecyclerView.Adapter<DinnerItemHolder> {
        ArrayList<Dinner> dinnerList;

        public DinnerItemAdapter(ArrayList<Dinner> dinnerList) {
            this.dinnerList = dinnerList;
        }

        @NonNull
        @Override
        public DinnerItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new DinnerItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DinnerItemHolder holder, int position) {
            holder.bind(dinnerList.get(position));
        }

        @Override
        public int getItemCount() {
            return dinnerList.size();
        }
    }
}
