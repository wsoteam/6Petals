package com.wsoteam.diet.BranchOfNews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiGroups;
import com.vk.sdk.api.methods.VKApiWall;
import com.vk.sdk.api.model.VKList;
import com.wsoteam.diet.POJOSForVkResponse.Item;
import com.wsoteam.diet.POJOSForVkResponse.Response;
import com.wsoteam.diet.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class ActivityListOfNews extends AppCompatActivity {

    private static final int VISIBLE_POSITION = 5;
    private static final int INVISIBLE_POSITION = 3;
    private static final int MAX_COUNT_OF_LETTERS = 50;
    private static final String GROUP_ID = "amazingheapkotya";
    private static String ACCES_TOKEN = "28da670228da670228da6702e528bd27cc228da28da67027325b00cc2cf21e0c7892592";
    private static final int COUNT_OF_ITEM_FROM_RESPONSE = 100;

    private RecyclerView rvListOfNews;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_news);
        rvListOfNews = findViewById(R.id.rvListOfNews);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        rvListOfNews.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getResponseFromVK();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        getResponseFromVK();

    }

    private void getResponseFromVK() {

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Response> jsonAdapter = moshi.adapter(Response.class);
        VKRequest vkRequest = new VKApiGroups().getById(VKParameters.from(VKApiConst.GROUP_ID, GROUP_ID, VKApiConst.ACCESS_TOKEN, ACCES_TOKEN));
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onError(VKError error) {
                super.onError(error);
                Log.e("LOL", "Error");
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                super.attemptFailed(request, attemptNumber, totalAttempts);
                Log.e("LOL", "attemptFailed");
            }

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                //Toast.makeText(getActivity(), R.string.downloaded, Toast.LENGTH_SHORT).show();
                VKList vkList = (VKList) response.parsedModel;
                try {
                    final VKRequest vkRequestWall = new VKApiWall().get(VKParameters.from(VKApiConst.OWNER_ID,
                            "-" + vkList.get(0).fields.getInt("id"), VKApiConst.COUNT, COUNT_OF_ITEM_FROM_RESPONSE,
                            VKApiConst.ACCESS_TOKEN, ACCES_TOKEN));
                    vkRequestWall.executeWithListener(new VKRequest.VKRequestListener() {
                        @Override
                        public void onComplete(VKResponse response) {
                            super.onComplete(response);
                            try {
                                Log.i("RESPONSE", response.responseString);
                                Response responseVk = jsonAdapter.fromJson(response.json.getString("response"));
                                ObjectHolder objectHolder = new ObjectHolder();
                                objectHolder.createObject(responseVk);
                                updateUI();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateUI() {
        itemAdapter = new ItemAdapter((ArrayList<Item>) ObjectHolder.getResponseVk().getItems());
        itemAdapter.notifyDataSetChanged();
        rvListOfNews.setAdapter(itemAdapter);
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView imageView;
        CardView cardView;

        public ItemViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_of_news_list, viewGroup, false));
            title = itemView.findViewById(R.id.tvFromItemOfList);
            imageView = itemView.findViewById(R.id.ivItemOfList);
            cardView = imageView.findViewById(R.id.cardFromItemOfList);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            /*Intent intent = new Intent(getActivity(), ActivityDetail.class);
            intent.putExtra(Config.KEY_OF_CLICK_ITEM, getAdapterPosition());
            startActivity(intent);*/
        }

        public void bind(Item item) {
            if (item.getAttachments().get(0).getType().equals("photo")) {
                if (item.getText().length() > MAX_COUNT_OF_LETTERS) {
                    title.setText(item.getText().substring(0, MAX_COUNT_OF_LETTERS) + " ...");
                } else {
                    title.setText(item.getText());
                }
                if (item.getAttachments().get(0).getPhoto().getPhoto1280() != null) {
                    Glide.with(ActivityListOfNews.this).load(item.getAttachments().get(0).getPhoto().getPhoto1280()).into(imageView);
                } else {
                    if (item.getAttachments().get(0).getPhoto().getPhoto807() != null) {
                        Glide.with(ActivityListOfNews.this).load(item.getAttachments().get(0).getPhoto().getPhoto807()).into(imageView);
                    } else {
                        Glide.with(ActivityListOfNews.this).load(item.getAttachments().get(0).getPhoto().getPhoto604()).into(imageView);
                    }
                }
            } else {
                title.setText("error");
            }
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        ArrayList<Item> items;

        public ItemAdapter(ArrayList<Item> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ActivityListOfNews.this);
            return new ItemViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            /*if (position > VISIBLE_POSITION && fabUp.getVisibility() == View.GONE) {
                fabUp.startAnimation(movingIn);
                fabUp.setVisibility(View.VISIBLE);
            } else {
                if (position < INVISIBLE_POSITION && fabUp.getVisibility() == View.VISIBLE) {
                    fabUp.startAnimation(movingOut);
                    fabUp.setVisibility(View.GONE);
                }
            }*/
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}
