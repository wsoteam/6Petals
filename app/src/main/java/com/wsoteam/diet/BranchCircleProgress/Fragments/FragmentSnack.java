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

import com.bumptech.glide.Glide;
import com.wsoteam.diet.POJOsCircleProgress.Eating.Snack;
import com.wsoteam.diet.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentSnack extends Fragment {
    private List<Snack> snackList;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_snack, container, false);

        snackList = Snack.listAll(Snack.class);

        recyclerView = view.findViewById(R.id.rvEatingSnacks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new SnackItemAdapter((ArrayList<Snack>) snackList));
        return view;
    }

    private class SnackItemHolder extends RecyclerView.ViewHolder {
        private TextView tvEatingItemName, tvEatingItemFat, tvEatingItemCarbo, tvEatingItemProt, tvEatingItemKcal, tvEatingItemWeight;
        private ImageView ivImage;

        public SnackItemHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_eating_diary_lists, viewGroup, false));

            tvEatingItemName = itemView.findViewById(R.id.tvEatingItemName);
            tvEatingItemFat = itemView.findViewById(R.id.tvEatingItemFat);
            tvEatingItemCarbo = itemView.findViewById(R.id.tvEatingItemCarbo);
            tvEatingItemProt = itemView.findViewById(R.id.tvEatingItemProt);
            tvEatingItemKcal = itemView.findViewById(R.id.tvEatingItemKcal);
            tvEatingItemWeight = itemView.findViewById(R.id.tvEatingItemWeight);
            ivImage = itemView.findViewById(R.id.ivImage);

        }

        public void bind(Snack snack) {
            tvEatingItemName.setText(String.valueOf(snack.getName()));
            tvEatingItemFat.setText(String.valueOf(snack.getFat()));
            tvEatingItemCarbo.setText(String.valueOf(snack.getCarbohydrates()));
            tvEatingItemProt.setText(String.valueOf(snack.getProtein()));
            tvEatingItemKcal.setText(String.valueOf(snack.getCalories()) + " ккал");
            tvEatingItemWeight.setText(String.valueOf(snack.getWeight()) + " кг");

            Glide.with(getActivity()).load(snack.getUrlOfImages()).into(ivImage);

        }
    }

    private class SnackItemAdapter extends RecyclerView.Adapter<SnackItemHolder> {
        ArrayList<Snack> snackList;

        public SnackItemAdapter(ArrayList<Snack> snackList) {
            this.snackList = snackList;
        }

        @NonNull
        @Override
        public SnackItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new SnackItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SnackItemHolder holder, int position) {
            holder.bind(snackList.get(position));
        }

        @Override
        public int getItemCount() {
            return snackList.size();
        }
    }
}
