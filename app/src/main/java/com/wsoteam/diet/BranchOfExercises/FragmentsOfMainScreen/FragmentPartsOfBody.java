package com.wsoteam.diet.BranchOfExercises.FragmentsOfMainScreen;

import android.content.Intent;
import android.graphics.Typeface;
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

import java.util.ArrayList;

import onepic.bkgcom.com.rtreningexercises.ActivitiesPartOfBody.ActivityListOfExGroups;
import onepic.bkgcom.com.rtreningexercises.ObjectHolder;
import onepic.bkgcom.com.rtreningexercises.POJOs.AllPartOfBody;
import onepic.bkgcom.com.rtreningexercises.POJOs.PartOfBody;
import onepic.bkgcom.com.rtreningexercises.R;

public class FragmentPartsOfBody extends Fragment {

    private static String TAG_OF_FRAGMENT = "FragmentPartsOfBody";
    private RecyclerView rvListOfEx;
    private AllPartOfBody allPartOfBody;

    /*public static FragmentPartsOfBody newInstance(AllPartOfBody allPartOfBody){
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG_OF_FRAGMENT, allPartOfBody);
        FragmentPartsOfBody fragmentPartsOfBody = new FragmentPartsOfBody();
        fragmentPartsOfBody.setArguments(bundle);
        return fragmentPartsOfBody;
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_exercises, container, false);
        rvListOfEx = view.findViewById(R.id.rvListOfEx);
        //allPartOfBody = (AllPartOfBody) getArguments().getSerializable(TAG_OF_FRAGMENT);
        allPartOfBody = ObjectHolder.getGlobalObject().getExercises();
        rvListOfEx.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvListOfEx.setAdapter(new ExAdapter((ArrayList<PartOfBody>) allPartOfBody.getPartOfBodyList()));


        return view;
    }

    private class ExViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvNameOfPartOfBody;
        private ImageView ivBackground;

        public ExViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_fragment_part_of_body_list_main_screen, viewGroup, false));
            tvNameOfPartOfBody = itemView.findViewById(R.id.tvNamePartOfBodyHomeList);
            tvNameOfPartOfBody.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "asProgramMainScreen.ttf"));
            ivBackground = itemView.findViewById(R.id.ivBackgroundItemListOfPartOfBodyMainScreen);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ActivityListOfExGroups.class);
            intent.putExtra(ActivityListOfExGroups.TAG, getAdapterPosition());
            startActivity(intent);
        }

        public void bind(PartOfBody partOfBody) {
            Glide.with(getActivity()).load(partOfBody.getUrl_of_image()).into(ivBackground);
            Log.d("DDD", partOfBody.getUrl_of_image());
            tvNameOfPartOfBody.setText(partOfBody.getName());
        }
    }

    private class ExAdapter extends RecyclerView.Adapter<ExViewHolder>{
        ArrayList<PartOfBody> partOfBodyArrayList;

        public ExAdapter(ArrayList<PartOfBody> partOfBodyArrayList) {
            this.partOfBodyArrayList = partOfBodyArrayList;
        }

        @NonNull
        @Override
        public ExViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ExViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ExViewHolder holder, int position) {
            holder.bind(partOfBodyArrayList.get(position));
        }

        @Override
        public int getItemCount() {
            return partOfBodyArrayList.size();
        }
    }
}
