package com.example.project3_androidapp.activities;

import static com.example.project3_androidapp.activities.LoginActivity.idValue;
import static com.example.project3_androidapp.util.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.db.AppDatabase;
import com.example.project3_androidapp.db.CardDao;
import com.example.project3_androidapp.db.CardEntity;
import com.example.project3_androidapp.db.CardListDao;
import com.example.project3_androidapp.db.CardListEntity;
import com.example.project3_androidapp.db.TransactionDao;
import com.example.project3_androidapp.db.TransactionEntity;
import com.example.project3_androidapp.db.TransactionListDao;
import com.example.project3_androidapp.db.TransactionListEntity;
import com.example.project3_androidapp.db.UserDao;
import com.example.project3_androidapp.db.UserEntity;
import com.example.project3_androidapp.db.UserListDao;
import com.example.project3_androidapp.db.UserListEntity;

public class LoadingActivity extends AppCompatActivity {

    AppDatabase database;
    CardDao cardDao;
    CardListDao cardListDao;
    TransactionDao transactionDao;
    TransactionListDao transactionListDao;
    UserDao userDao;
    UserListDao userListDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        getDatabase();
        setupTables();
    }


    // get instance of database and return user DAO
    private void getDatabase() {
        database = AppDatabase.getInstance(this.getApplicationContext());
        cardDao = database.cardDao();
        cardListDao = database.cardListDao();
        transactionDao = database.transactionDao();
        transactionListDao = database.transactionListDao();
        userDao = database.userDao();
        userListDao = database.userListDao();
    }

    private void setupTables() {
        setUpUsers();
        setUpTransactions();
        setUpCards();
        setUpUserLists();
        setUpTransactionLists();
        setUpCardLists();
    }

    private void setUpUsers() {
        String url = URL_BASE + "/retrieve_users";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.toString();
            String obj, var;
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == '{'){
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);

                        // get substring from end of ':' to ','
                        UserEntity t = new UserEntity();
                        if(obj.equals("user_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setUserId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("username")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setUsername(var);
                            i = str.indexOf(',', i);
                        } else if(obj.equals("password")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setPassword(var);
                            i = str.indexOf(',', i);
                        } else if(obj.equals("admin")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setAdmin(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("card_list_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setCardListId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("user_list_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setUserListId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("bank")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setBank(Double.parseDouble(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("transaction_list_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setTransactionListId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        // fill with item after import
                        //TODO check if user is in system

                        userDao.insertUser(t);


                        if(i >= str.length() || i < 0)
                            break;
                    }
                }
                if(i >= str.length() || i < 0)
                    break;
            }
        }, err -> {
            System.out.println("Error 1");
        });
        queue.add(stringRequest);
    }

    private void setUpTransactions() {
        String url = URL_BASE + "/retrieve_transactions_and/?uid=" + idValue;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.toString();
            String obj, var;

            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == '{'){
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);

                        // get substring from end of ':' to ','
                        TransactionEntity t = new TransactionEntity();
                        if(obj.equals("transaction_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setTransactionId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("amount")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setAmount(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("currency")) {
                            var = str.substring(i+1, str.indexOf(',', i)-1);
                            t.setCurrency(var);
                            i = str.indexOf(',', i);
                        } else if(obj.equals("is_finalized")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setIsFinalized(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("sending_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setSendingId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("receiving_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setReceivingId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("description")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setDescription(var);
                            i = str.indexOf(',', i);
                        }
                        // fill with item after import
                        transactionDao.insertTransaction(t);

                        if(i >= str.length() || i < 0)
                            break;
                    }
                }
                if(i >= str.length() || i < 0)
                    break;
            }
        }, err -> {
            System.out.println("Error 2");
        });
        queue.add(stringRequest);
    }

    private void setUpCards() {
        //TODO fix string values to match response
        String url = URL_BASE + "/retrieve_cards";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.toString();
            String obj, var;

            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == '{'){
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);

                        // get substring from end of ':' to ','
                        CardEntity t = new CardEntity();
                        if(obj.equals("card_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setCardId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("card_num")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setCardNum(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("expiration")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setCardNum(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("cvv")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setCvv(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("holder_name")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setHolderName(var);
                            i = str.indexOf(',', i);
                        } else if(obj.equals("zip")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setZip(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("card_nickname")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setCardNickname(var);
                            i = str.indexOf(',', i);
                        }
                        // fill with item after import
                        cardDao.insertCard(t);
                        System.out.println(t);


                        if(i >= str.length() || i < 0)
                            break;
                    }
                }
                if(i >= str.length() || i < 0)
                    break;
            }
        }, err -> {
            System.out.println("Error 3");
        });
        queue.add(stringRequest);
    }

    private void setUpUserLists() {
        String url = URL_BASE + "/retrieve_user_lists";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.toString();
            String obj, var;

            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == '{'){
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);

                        // get substring from end of ':' to ','
                        UserListEntity t = new UserListEntity();
                        if(obj.equals("owner_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setOwnerId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("other_user_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setOtherUserId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("is_accepted")) { // TODO add field to Entity
                            var = str.substring(i, str.indexOf(',', i));
                            t.setOtherUserId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        // fill with item after import
                        userListDao.insertUserList(t);
//                        System.out.println(t);


                        if(i >= str.length() || i < 0)
                            break;
                    }
                }
                if(i >= str.length() || i < 0)
                    break;
            }
        }, err -> {
            Toast.makeText(getApplicationContext(), "profile error", Toast.LENGTH_LONG).show();
            System.out.println("Error 4");
        });
        queue.add(stringRequest);
    }

    private void setUpTransactionLists() {
        String url = URL_BASE + "/retrieve_transaction_lists";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.toString();
            String obj, var;

            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == '{'){
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);

                        // get substring from end of ':' to ','
                        TransactionListEntity t = new TransactionListEntity();
                        if(obj.equals("transaction_list_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setTransactionListId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("user_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setUserId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("transaction_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setTransactionId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        // fill with item after import
                        transactionListDao.insertTransactionList(t);
                        System.out.println(t);


                        if(i >= str.length() || i < 0)
                            break;
                    }
                }
                if(i >= str.length() || i < 0)
                    break;
            }
        }, err -> {
            System.out.println("Error 5");
        });
        queue.add(stringRequest);
    }

    private void setUpCardLists() {
        //TODO fix string values to match response
        String url = URL_BASE + "/retrieve_card_lists";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.toString();
            String obj, var;

            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == '{'){
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);

                        // get substring from end of ':' to ','
                        CardListEntity t = new CardListEntity();
                        if(obj.equals("card_list_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setCardListId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("card_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setCardId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        // fill with item after import
                        cardListDao.insertCardList(t);
                        System.out.println(t);

                    if(i >= str.length() || i < 0)
                        break;
                }
            }
            if(i >= str.length() || i < 0)
                break;
        }
        }, err -> {
            Toast.makeText(getApplicationContext(), "profile error", Toast.LENGTH_LONG).show();
            System.out.println("Error 6");
        });
        queue.add(stringRequest);
    }
}