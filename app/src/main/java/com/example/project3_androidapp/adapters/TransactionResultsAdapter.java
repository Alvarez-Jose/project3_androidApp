package com.example.project3_androidapp.adapters;

import static com.example.project3_androidapp.util.Constants.URL_BASE;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.activities.TransactionsActivity;
import com.example.project3_androidapp.db.TransactionEntity;
import com.example.project3_androidapp.models.Search;

import java.util.ArrayList;
import java.util.List;

public class TransactionResultsAdapter extends RecyclerView.Adapter<TransactionResultsAdapter.SearchResultHolder> {
    private List<TransactionEntity> searchResults = new ArrayList<>();
    private Context context;
    private View view;
    private Button acceptTransactionButton, declineTransactionButton;

    @NonNull
    @Override
    public SearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_item, parent, false);

        acceptTransactionButton = parent.findViewById(R.id.userNameText);
        declineTransactionButton = parent.findViewById(R.id.bankText);

        return new SearchResultHolder(itemView);
//        return null;
    }

    public TransactionResultsAdapter(Context context) {
        this.context = context;
    }

    public TransactionResultsAdapter() {

    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultHolder holder, int position) {
        TransactionEntity results = searchResults.get(position);


        if (results.getSendingId() != null) {
            holder.sendingIdText.setText(results.getSendingId());
        }
        if (results.getReceivingId() != null) {
            holder.receivingIdText.setText(results.getReceivingId());
        }
        if (results.getAmount() != null) {
            holder.amountText.setText(results.getAmount());
        }
        if (results.getIsFinalized() != null) {
            holder.isFinalizedText.setText(results.getIsFinalized());
        }

        View.OnClickListener handler = v -> {

            if (v == acceptTransactionButton) {
                transactionUpdate(1);
            }

            if(v == declineTransactionButton){
                transactionUpdate(0);
            }

        };

        acceptTransactionButton.setOnClickListener(handler);
        declineTransactionButton.setOnClickListener(handler);
    }

    public void transactionUpdate(int i) {
        String url = URL_BASE + "/retrieve_user/?user=&pass";

                RequestQueue queue = Volley.newRequestQueue(context);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                    Toast.makeText(context, "success!", Toast.LENGTH_LONG).show();
                    //Login success path
                }, err -> {
                    Toast.makeText(context, "failure", Toast.LENGTH_LONG).show();
                });
                queue.add(stringRequest);
    }

    @Override
    public int getItemCount() {
        if (searchResults == null) {
            return 0;
        }
        return searchResults.size();
    }

    public void setResults(List<TransactionEntity> results) {
        this.searchResults = results;
        notifyDataSetChanged();
    }

    class SearchResultHolder extends RecyclerView.ViewHolder {
        private TextView sendingIdText;
        private TextView receivingIdText;
        private TextView amountText;
        private TextView isFinalizedText;

        public SearchResultHolder(@NonNull View itemView) {
            super(itemView);

//            sendingIdText = itemView.findViewById(R.id.movie_item_title);
//            receivingIdText = itemView.findViewById(R.id.movie_releaseDate);
//            amountText = itemView.findViewById(R.id.movie_poster);
//            isFinalizedText = itemView.findViewById(R.id.movie_poster);
        }
    }
}
