package com.example.project3_androidapp.util;

public class StringAPIRequest {
    private String api, url = Constants.URL_BASE;

    public StringAPIRequest(String basePath){
        url += basePath;
    }

    public void createRequest(String params){

    }

    public boolean sendRequest(){
        // true if request sent
        return false;
    }
}
