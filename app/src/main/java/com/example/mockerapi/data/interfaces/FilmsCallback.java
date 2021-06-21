package com.example.mockerapi.data.interfaces;

import com.example.mockerapi.data.models.FilmModel;

import java.util.List;

public interface FilmsCallback extends BaseCallback<List<FilmModel>> {

    @Override
    void onSuccess(List<FilmModel> films);

    @Override
    void onFailure(Throwable e);

}
