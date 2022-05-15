package com.example.project3_androidapp.adapters;

import static com.example.project3_androidapp.util.Constants.URL_BASE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.db.TransactionEntity;
import com.example.project3_androidapp.db.UserDao;
import com.example.project3_androidapp.db.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TransactionResultsAdapter extends RecyclerView.Adapter<TransactionResultsAdapter.SearchResultHolder> {
    private List<TransactionEntity> searchResults = new ArrayList<>();
    private Context context;
    private Button acceptTransactionButton, declineTransactionButton;

    public TransactionResultsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.transaction_item, parent, false);

        acceptTransactionButton = parent.findViewById(R.id.buttonAccept);
        declineTransactionButton = parent.findViewById(R.id.buttonDecline);

        return (new SearchResultHolder(view));
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultHolder holder, int position) {
//        TransactionEntity results = searchResults.get(position);
//        System.out.println("onBindViewHolder - "+results.getSendingId());

        acceptTransactionButton = holder.acceptB;
        declineTransactionButton = holder.declineB;

        System.out.println(position + " --- " + searchResults.get(position).getIsFinalized());
        if(searchResults.get(position).getIsFinalized() == 1){
            acceptTransactionButton.setEnabled(false);
            declineTransactionButton.setEnabled(false);
        } else {
            acceptTransactionButton.setEnabled(true);
            declineTransactionButton.setEnabled(true);
        }

        holder.sendingText.setText("From:");
        holder.recievingText.setText("To:");
//        System.out.println("results = " + searchResults.get(position));
//
//        System.out.println("S = " + searchResults.get(position).getSendingId());
//        System.out.println("R = " + searchResults.get(position).getReceivingId());
        if (searchResults.get(position).getSendingId() != null) {
            holder.sendingIdText.setText(searchResults.get(position).getSendingId().toString());
        }

        if (searchResults.get(position).getReceivingId() != null) {
            holder.receivingIdText.setText(searchResults.get(position).getReceivingId().toString());
        }

        TextView amountText = holder.amountText;
        if (searchResults.get(position).getAmount() != null) {
            amountText.setText("$ " + (searchResults.get(position).getAmount()));
        }
//        if (results.getIsFinalized() != null) {
//            holder.isFinalizedText.setText(results.getIsFinalized());
//        }
        acceptTransactionButton = holder.acceptB;
        declineTransactionButton = holder.declineB;

//        if (searchResults.get(position).getIsFinalized() != 0) {
//            acceptTransactionButton.setEnabled(false);
//        } else {
//            acceptTransactionButton.setEnabled(true);
//        }


        View.OnClickListener handler = v -> {

            if (v == acceptTransactionButton) {
                transactionUpdate(true, searchResults.get(position)); // update transaction to show accepted
            }

            if (v == declineTransactionButton) {
                transactionUpdate(false, searchResults.get(position)); // delete transaction from db
            }

        };

        acceptTransactionButton.setOnClickListener(handler);
        declineTransactionButton.setOnClickListener(handler);
    }

    public void transactionUpdate(boolean accept, TransactionEntity transaction) {

        AtomicReference<String> str = new AtomicReference<>("");
        int offset = 0;
        System.out.println("update");

        if (accept) {
            // first update the transaction as done and then update both users' bank.
            str.set( str + "update_transaction - ");
            String url = URL_BASE + "/accept_transaction/?tid=" + transaction.getTransactionId()+offset
                    + "&amt=" + transaction.getAmount()
                    + "&cur=" + transaction.getCurrency()
                    + "&fin=1&sid=" + transaction.getSendingId()
                    + "&rid=" + transaction.getReceivingId();
            RequestQueue queue = Volley.newRequestQueue(context);
            System.out.println(url);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                str.set( str + "success ");
//                Toast.makeText(context, "success!", Toast.LENGTH_LONG).show();
                transaction.setIsFinalized(1);
            }, err -> {
                Toast.makeText(context, "failure to accept", Toast.LENGTH_LONG).show();
                str.set( str + "failure ");
            });

            queue.add(stringRequest);
        } else {
            str.set( str + "delete_transaction - ");
            String url = URL_BASE + "/delete_transaction/?tid=" + transaction.getTransactionId()+offset;
            RequestQueue queue = Volley.newRequestQueue(context);
            System.out.println(url);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                str.set( str + "success ");
//                Toast.makeText(context, "success!", Toast.LENGTH_LONG).show();
                AppDatabase appDatabase = AppDatabase.getInstance(this.context);
                appDatabase.transactionDao().deleteTransaction(transaction.getTransactionId());
            }, err -> {
                Toast.makeText(context, "failure to decline", Toast.LENGTH_LONG).show();
                str.set( str + "failure ");
            });

            queue.add(stringRequest);
        }
        System.out.println("transaction " + str);
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
        public TextView sendingIdText, receivingIdText, amountText, sendingText, recievingText;
        public Button acceptB, declineB;
//        private TextView isFinalizedText;

        public SearchResultHolder(@NonNull View itemView) {
            super(itemView);

            sendingText = itemView.findViewById(R.id.SendingUser);
            sendingIdText = itemView.findViewById(R.id.userNameText);
            recievingText = itemView.findViewById(R.id.RecievingUser);
            receivingIdText = itemView.findViewById(R.id.RecievingUserText);
            amountText = itemView.findViewById(R.id.amountText);
//            isFinalizedText = itemView.findViewById(R.id.movie_poster);

            acceptB = itemView.findViewById(R.id.buttonAccept);
            declineB = itemView.findViewById(R.id.buttonDecline);
        }
    }
}
