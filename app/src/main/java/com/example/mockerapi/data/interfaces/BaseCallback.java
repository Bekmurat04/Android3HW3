package com.example.mockerapi.data.interfaces;

public interface BaseCallback<T> {

    void onSuccess(T model);

    void onFailure(Throwable throwable);

}
