package com.example.project3_androidapp.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class StringAPIRequest {
    private String url = Constants.URL_BASE;

    public StringAPIRequest(String basePath){
        url += basePath;
    }

    public StringAPIRequest() {

    }

    public void createRequest(String params){
        url += params;

    }

    public void sendRequest(){
        // true if request sent
        try {
            URL u = new URL(url);
            InputStream is = u.openStream();
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(is));
            String s = "";
            System.out.println(br);

        } catch (MalformedURLException murle) { murle.printStackTrace();
        } catch (IOException ioe) { ioe.printStackTrace();
        }
    }
}
