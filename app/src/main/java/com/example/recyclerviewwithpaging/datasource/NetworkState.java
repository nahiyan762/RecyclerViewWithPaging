package com.example.recyclerviewwithpaging.datasource;

public class NetworkState {

    private String status;
    private static NetworkState networkState;

    public static NetworkState getInstance(){
        if (networkState != null){
            return networkState;
        } else {
            return networkState = new NetworkState();
        }
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
