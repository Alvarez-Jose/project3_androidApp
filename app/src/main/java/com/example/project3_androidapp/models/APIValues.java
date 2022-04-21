package com.example.project3_androidapp.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIValues {
    @SerializedName("Search")
    @Expose
    private List<Search> search = null;

    @SerializedName("totalResults")
    @Expose
    private String totalResults;

    @SerializedName("Response")
    @Expose
    private String response;
    public final static Parcelable.Creator<APIValues> CREATOR = new Parcelable.Creator<APIValues>() {


        @SuppressWarnings({
                "unchecked"
        })
        public APIValues createFromParcel(android.os.Parcel in) {
            return new APIValues(in);
        }

        public APIValues[] newArray(int size) {
            return (new APIValues[size]);
        }

    };

    protected APIValues(android.os.Parcel in) {
        in.readList(this.search, (com.example.project3_androidapp.models.Search.class.getClassLoader()));
        this.totalResults = ((String) in.readValue((String.class.getClassLoader())));
        this.response = ((String) in.readValue((String.class.getClassLoader())));
    }

    public APIValues() {
    }

    public APIValues(List<Search> search) {
        this.search = search;
    }

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeList(search);
        dest.writeValue(totalResults);
        dest.writeValue(response);
    }

    public int describeContents() {
        return  0;
    }
}
