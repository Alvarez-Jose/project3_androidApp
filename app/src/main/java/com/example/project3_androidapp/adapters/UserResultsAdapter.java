package com.example.project3_androidapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.db.UserEntity;
import com.example.project3_androidapp.models.Search;

import java.util.ArrayList;
import java.util.List;

public class UserResultsAdapter extends RecyclerView.Adapter<UserResultsAdapter.SearchResultHolder> {
    private List<UserEntity> searchResults = new ArrayList<>();
    private Context context;
    private Button userProfileButton;

    @NonNull
    @Override
    public SearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        return new SearchResultHolder(itemView);
    }

    public UserResultsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultHolder holder, int position) {
//        UserEntity results = searchResults.get(position);
//
//        System.out.println(results.getUsername() + " - " + results.getUserId());
//        userProfileButton = holder.profileButton;

        holder.userNameSet.setText("username: ");
        holder.userIdSet.setText("ID: ");

        if (searchResults.get(position).getUsername()  != null) {
            holder.userNameResult.setText(searchResults.get(position).getUsername());
        } else {
            holder.userNameResult.setText("Error f");
        }
        if (searchResults.get(position).getUserId().toString() != null) {
            holder.userIdResult.setText(searchResults.get(position).getUserId().toString());
        } else {
            holder.userIdResult.setText("Error f");
        }

        View.OnClickListener handler = v -> {

            if (v == holder.profileButton) {
                toUserProfile();
            }


        };

        holder.profileButton.setOnClickListener(handler);
    }

    private void toUserProfile() {
        //TODO get other User's profile in detail
        System.out.println("Not out yet, sorry!");
        Toast.makeText(context.getApplicationContext(), "Not out yet, sorry!", Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        if (searchResults == null) {
            return 0;
        }
        return searchResults.size();
    }

    public void setResults(List<UserEntity> results) {
        this.searchResults = results;
        notifyDataSetChanged();
    }

    class SearchResultHolder extends RecyclerView.ViewHolder {
        public TextView userNameSet, userNameResult, userIdSet, userIdResult;
        public Button profileButton;

        public SearchResultHolder(@NonNull View itemView) {
            super(itemView);
            userNameSet = itemView.findViewById(R.id.userNameText);
            userNameResult = itemView.findViewById(R.id.userNameResult);
            userIdSet = itemView.findViewById(R.id.textId);
            userIdResult = itemView.findViewById(R.id.userID);
            profileButton = itemView.findViewById(R.id.toUserProfile);

        }
    }
}
