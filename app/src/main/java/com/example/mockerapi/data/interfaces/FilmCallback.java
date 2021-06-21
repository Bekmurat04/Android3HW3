package com.example.mockerapi.data.interfaces;

import com.example.mockerapi.data.models.FilmModel;

public interface FilmCallback extends BaseCallback<FilmModel> {

    @Override
    void onSuccess(FilmModel model);

    @Override
    void onFailure(Throwable throwable);
}
