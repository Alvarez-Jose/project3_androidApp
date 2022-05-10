package com.example.project3_androidapp.activities;

import static com.example.project3_androidapp.util.Constants.URL_BASE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project3_androidapp.R;
import com.example.project3_androidapp.db.*;

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

        database = AppDatabase.getInstance(this);
//        cardDao = database.cardDao();
//        cardListDao = database.cardListDao();
        transactionDao = database.transactionDao();
//        transactionListDao = database.transactionListDao();
        userDao = database.userDao();
//        userListDao = database.userListDao();

        setUpUsers();
        setUpTransactions();
//        setUpCards();
//        setUpUserLists();
//        setUpTransactionLists();
//        setUpCardLists();

        switchToTransactions();
    }

    private void setUpUsers() {
        System.out.println("-=-\tretrieve_users\t-=-");
        String url = URL_BASE + "/retrieve_users";

        RequestQueue queue = Volley.newRequestQueue(LoadingActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            // loop all items in []
            String str = response.replaceAll("&#34;", "\"");
//            System.out.println(str);
            String obj, var;
            UserEntity t = new UserEntity();
            for(int i = 0; i < str.length(); i++){
                t = new UserEntity();
                if(str.charAt(i) == '{'){
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);

                        // get substring from end of ':' to ','
                        i = str.indexOf(':', i)+1;
                        if(obj.equals("user_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setUserId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("username")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setUsername(var);
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("password")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setPassword(var);
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("admin")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setAdmin(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("card_list_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setCardListId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("user_list_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setUserListId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("bank")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setBank(Double.parseDouble(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("transaction_list_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setTransactionListId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }

                        System.out.println(t + "\t-=-");
                        if(i >= str.length() || i < 0)
                            break;
                    }
                }
                System.out.println(t);
                // fill with item after import
                if(!t.equals(null)){
                    if(!userDao.userExists(t.getUserId())) {
                        System.out.println("\t-=- added");
                        userDao.insertUser(t);
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
        System.out.println("-=-\tretrieve_transactions\t-=-");
        String url = URL_BASE + "/retrieve_transactions";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.replaceAll("&#34;", "\"");
            System.out.println(str);
            String obj, var;
            TransactionEntity t;

            for(int i = 0; i < str.length(); i++){
                t = new TransactionEntity();
                if(str.charAt(i) == '{'){
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);

                        System.out.println(obj);
                        // get substring from end of ':' to ','
                        i = str.indexOf(':', i)+1;
                        if(obj.equals("transaction_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setTransactionId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
//                            System.out.println(var);
                        }
                        if(obj.equals("amount")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setAmount(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("currency")) {
                            var = str.substring(i+1, str.indexOf(',', i)-1);
                            System.out.println(var);
                            t.setCurrency(var);
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("is_finalized")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setIsFinalized(Integer.parseInt(var));
                        }
                        if(obj.equals("sending_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setSendingId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("receiving_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setReceivingId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }
                        if(obj.equals("description")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
                            t.setDescription(var);
                            i = str.indexOf(',', i);
                        }
                        // fill with item after import
                        System.out.println(t.getTransactionId() + "\t-=-");

                        if(i >= str.length() || i < 0)
                            break;
                    }
                }
                System.out.println(t);
                if(i >= str.length() || i < 0)
                    break;

                if(!t.equals(null))
                if(!transactionDao.transactionExists(t.getTransactionId())) {
                    System.out.println("\t-=- added");
                    transactionDao.insertTransaction(t);
                }
            }
        }, err -> {
            System.out.println("Error 2");
        });
        queue.add(stringRequest);
    }

    private void setUpCards() {
        System.out.println("-=-\tretrieve_cards\t-=-");
        //TODO fix string values to match response
        String url = URL_BASE + "/retrieve_cards";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.replaceAll("&#34;", "\"");
            System.out.println(str);
            String obj, var;
            CardEntity t;

            for(int i = 0; i < str.length(); i++){
                t = new CardEntity();
                if(i >= str.length() || i < 0)
                    break;
                if(str.charAt(i) == '{'){
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);

                        // get substring from end of ':' to ','
                        i = str.indexOf(':', i)+1;
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
                        if(i >= str.length() || i < 0)
                            break;
                    }
                }
                // fill with item after import
                System.out.println(t.getCardId() + "\t-=-");
                if(!cardDao.cardExists(t.getCardId())) {
                    System.out.println("\t-=- added");
                    cardDao.insertCard(t);
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
        System.out.println("-=-\tretrieve_user_lists\t-=-");
        String url = URL_BASE + "/retrieve_user_lists";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.replaceAll("&#34;", "\"");
            System.out.println(str);
            String obj, var;
            UserListEntity t;

            for(int i = 0; i < str.length(); i++){
                t = new UserListEntity();
                if(str.charAt(i) == '{'){
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);

                        // get substring from end of ':' to ','
                        i = str.indexOf(':', i)+1;
                        if(obj.equals("owner_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            System.out.println(var);
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

                        if(i >= str.length() || i < 0)
                            break;
                    }
                }
                // fill with item after import
                System.out.println(t.getOwnerId() + "\t-=-");
                if(!userListDao.userListExists(t.getOwnerId())) {
                    System.out.println("\t-=- added");
                    userListDao.insertUserList(t);
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
        System.out.println("-=-\tretrieve_transaction_lists\t-=-");
        String url = URL_BASE + "/retrieve_transaction_lists";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.replaceAll("&#34;", "\"");
            String obj, var;
            TransactionListEntity t;

            for(int i = 0; i < str.length(); i++){
                t = new TransactionListEntity();
                if(str.charAt(i) == '{'){
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);

                        // get substring from end of ':' to ','
                        i = str.indexOf(':', i)+1;
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

                        if(i >= str.length() || i < 0)
                            break;
                    }
                }
                // fill with item after import
                System.out.println(t.getTransactionListId() + "\t-=-");
                if(!transactionListDao.transactionListExists(t.getTransactionListId())) {
                    System.out.println("\t-=- added");
                    transactionListDao.insertTransactionList(t);
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
        System.out.println("-=-\tretrieve_card_lists\t-=-");
        //TODO fix string values to match response
        String url = URL_BASE + "/retrieve_card_lists";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Setup making into dao objects
            //Toast.makeText(getApplicationContext(), "profile success!", Toast.LENGTH_LONG).show();
            //System.out.println(response);
            // loop all items in []
            String str = response.replaceAll("&#34;", "\"");
            System.out.println(str);
            String obj, var;
            CardListEntity t;

            for(int i = 0; i < str.length(); i++){
                t = new CardListEntity();
                if(str.charAt(i) == '{'){
                    while(str.charAt(i) != '}'){
                        obj = str.substring(i+2, str.indexOf(':', i)-1);

                        // get substring from end of ':' to ','
                        i = str.indexOf(':', i)+1;
                        if(obj.equals("card_list_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setCardListId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        } else if(obj.equals("card_id")) {
                            var = str.substring(i, str.indexOf(',', i));
                            t.setCardId(Integer.parseInt(var));
                            i = str.indexOf(',', i);
                        }

                    if(i >= str.length() || i < 0)
                        break;
                }
            }
                // fill with item after import
                System.out.println(t.getCardListId() + "\t-=-");
                if(!cardListDao.cardListExists(t.getCardListId())) {
                    System.out.println("\t-=- added");
                    cardListDao.insertCardList(t);
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

    private void switchToTransactions() {
        Intent switchActivityIntent = new Intent(LoadingActivity.this, TransactionsActivity.class);
        startActivity(switchActivityIntent);
    }
}