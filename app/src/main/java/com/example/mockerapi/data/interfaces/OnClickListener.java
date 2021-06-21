package com.example.mockerapi.data.interfaces;

import com.example.mockerapi.data.models.FilmModel;

public interface OnClickListener {

    void onClick(FilmModel m,int position);

    void deleteFilm(FilmModel m,int position);

}
