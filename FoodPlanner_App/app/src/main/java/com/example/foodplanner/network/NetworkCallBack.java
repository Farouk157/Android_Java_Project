package com.example.foodplanner.network;

public interface NetworkCallBack<T> {
    void onSuccess(T data);
    void onFailure(String errorMessage);
}

