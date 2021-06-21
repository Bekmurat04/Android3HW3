package com.example.mockerapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mockerapi.App;
import com.example.mockerapi.R;
import com.example.mockerapi.data.interfaces.FilmCallback;
import com.example.mockerapi.data.interfaces.FilmsCallback;
import com.example.mockerapi.data.interfaces.OnClickListener;
import com.example.mockerapi.data.models.FilmModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    FilmAdapter adapter;
    RecyclerView recyclerView;
    Button addBtn;

    public static final String FILM_MODEL = "film";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new FilmAdapter();
        adapter.setListener(this);
        recyclerView = findViewById(R.id.films_recycler);
        addBtn = findViewById(R.id.add);

        recyclerView.setAdapter(adapter);

        App.service.getFilms(new FilmsCallback() {
            @Override
            public void onSuccess(List<FilmModel> films) {
                adapter.setFilms(films);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.d("filmError",e.getMessage());
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,FilmActivity.class);
                startActivity(in);
            }
        });
    }


    @Override
    public void onClick(FilmModel m,int position) {
        Intent i = new Intent(this,FilmActivity.class);
        i.putExtra(FILM_MODEL,m);
        startActivity(i);
    }

    @Override
    public void deleteFilm(FilmModel m,int position) {
        App.service.deleteFilm(m.getFilmId(), new FilmCallback() {
            @Override
            public void onSuccess(FilmModel model) {
                Toast.makeText(MainActivity.this, "Пост удален!", Toast.LENGTH_SHORT).show();
                adapter.deleteFilm(position);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d("errorFilm",throwable.getMessage());
            }
        });
    }
}