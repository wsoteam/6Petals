package com.wsoteam.diet.BranchCircleProgress.Fragments;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wsoteam.diet.POJOsCircleProgress.Eating.Breakfast;
import com.wsoteam.diet.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentBreakfast extends Fragment {
    private List<Breakfast> breakfastList;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breakfast, container, false);
        breakfastList = Breakfast.listAll(Breakfast.class);
        recyclerView = view.findViewById(R.id.rvEatingBreakfast);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new BreakfastItemAdapter((ArrayList<Breakfast>) breakfastList));

        return view;
    }

    private class BreakfastItemHolder extends RecyclerView.ViewHolder {
        private TextView tvEatingItemName, tvEatingItemFat, tvEatingItemCarbo, tvEatingItemProt, tvEatingItemKcal, tvEatingItemWeight;
        private ImageView ivImage;

        public BreakfastItemHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_eating_diary_lists, viewGroup, false));

            tvEatingItemName = itemView.findViewById(R.id.tvEatingItemName);
            tvEatingItemFat = itemView.findViewById(R.id.tvEatingItemFat);
            tvEatingItemCarbo = itemView.findViewById(R.id.tvEatingItemCarbo);
            tvEatingItemProt = itemView.findViewById(R.id.tvEatingItemProt);
            tvEatingItemKcal = itemView.findViewById(R.id.tvEatingItemKcal);
            tvEatingItemWeight = itemView.findViewById(R.id.tvEatingItemWeight);
            ivImage = itemView.findViewById(R.id.ivImage);

        }

        public void bind(Breakfast breakfast) {
            tvEatingItemName.setText(String.valueOf(breakfast.getName()));
            tvEatingItemFat.setText(String.valueOf(breakfast.getFat()));
            tvEatingItemCarbo.setText(String.valueOf(breakfast.getCarbohydrates()));
            tvEatingItemProt.setText(String.valueOf(breakfast.getProtein()));
            tvEatingItemKcal.setText(String.valueOf(breakfast.getCalories()));
            tvEatingItemWeight.setText(String.valueOf(breakfast.getWeight()));

            Glide.with(getActivity()).load(breakfast.getUrlOfImages()).into(ivImage);

        }
    }

    private class BreakfastItemAdapter extends RecyclerView.Adapter<BreakfastItemHolder> {
        ArrayList<Breakfast> breakfastList;

        public BreakfastItemAdapter(ArrayList<Breakfast> breakfastList) {
            this.breakfastList = breakfastList;
        }

        @NonNull
        @Override
        public BreakfastItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new BreakfastItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BreakfastItemHolder holder, int position) {
            holder.bind(breakfastList.get(position));
        }

        @Override
        public int getItemCount() {
            return breakfastList.size();
        }
    }
}
