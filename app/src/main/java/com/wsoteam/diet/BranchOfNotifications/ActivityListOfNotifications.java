package com.wsoteam.diet.BranchOfNotifications;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wsoteam.diet.POJOForDB.ObjectForNotification;
import com.wsoteam.diet.R;

import java.util.ArrayList;

public class ActivityListOfNotifications extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fabCreateNewNotification;
    private ArrayList<ObjectForNotification> notificationArrayList;

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_notification);
        fabCreateNewNotification = findViewById(R.id.fabActivityListOfNotificationCreate);
        recyclerView = findViewById(R.id.rvActivityListOfNotification);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();

        fabCreateNewNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityListOfNotifications.this, ActivityDetailNotification.class);
                intent.putExtra(ActivityDetailNotification.TAG_OF_ACTIVITY, -1);
                startActivity(intent);
            }
        });
    }

    private void updateUI() {
        notificationArrayList = (ArrayList<ObjectForNotification>) ObjectForNotification.listAll(ObjectForNotification.class);
        recyclerView.setAdapter(new ItemAdapter(notificationArrayList));
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle, tvSubTitle;

        public ItemViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_of_list_activity_list_of_notification, viewGroup, false));

            tvTitle = itemView.findViewById(R.id.tvItemOfListNotificationTitle);
            tvSubTitle = itemView.findViewById(R.id.tvItemOfListNotificationSubTitle);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ActivityListOfNotifications.this, ActivityDetailNotification.class);
            intent.putExtra(ActivityDetailNotification.TAG_OF_ACTIVITY, getAdapterPosition());
            startActivity(intent);
        }

        public void bind(ObjectForNotification objectForNotification) {
            tvTitle.setText(objectForNotification.getText());
            tvSubTitle.setText(objectForNotification.getRepeat()
                    + " в " + String.valueOf(objectForNotification.getHour())
                    + ":" + String.valueOf(objectForNotification.getMinute())
                    + ", начиная с  " + String.valueOf(objectForNotification.getDay())
                    + "." + String.valueOf(objectForNotification.getMonth()
                    + "." + String.valueOf(objectForNotification.getYear())));

        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        ArrayList<ObjectForNotification> notificationArrayList;

        public ItemAdapter(ArrayList<ObjectForNotification> notificationArrayList) {
            this.notificationArrayList = notificationArrayList;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ActivityListOfNotifications.this);
            return new ItemViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.bind(notificationArrayList.get(position));
        }

        @Override
        public int getItemCount() {
            return notificationArrayList.size();
        }
    }
}
