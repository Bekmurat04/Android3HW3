package com.example.mockerapi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mockerapi.App;
import com.example.mockerapi.R;
import com.example.mockerapi.data.interfaces.FilmCallback;
import com.example.mockerapi.data.interfaces.FilmsCallback;
import com.example.mockerapi.data.models.FilmModel;

import java.util.List;

public class FilmActivity extends AppCompatActivity {
    EditText title, content, group, user;
    Button add;
    FilmModel filmModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        title = findViewById(R.id.title_et);
        content = findViewById(R.id.content_et);
        group = findViewById(R.id.group_et);
        user = findViewById(R.id.user_et);
        add = findViewById(R.id.add_btn_et);

        filmModel = (FilmModel) getIntent().getSerializableExtra(MainActivity.FILM_MODEL);

        if (filmModel != null) {
            title.setText(filmModel.getTitle());
            content.setText(filmModel.getContent());
            group.setText(String.valueOf(filmModel.getGroup()));
            user.setText(String.valueOf(filmModel.getUser()));
            add.setText("Изменить");
        }

        add.setOnClickListener(v -> {
            FilmModel f = new FilmModel(title.getText().toString(),
                    content.getText().toString(),
                    Integer.parseInt(user.getText().toString()),
                    Integer.parseInt(group.getText().toString()));

            if (filmModel != null) {
                App.service.updatePost(filmModel.getFilmId(), f, new FilmCallback() {
                    @Override
                    public void onSuccess(FilmModel model) {
                        Intent intent = new Intent(FilmActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.d("errorFilm",throwable.getMessage());
                    }
                });
            } else {
                App.service.addFilm(f, new FilmCallback() {
                    @Override
                    public void onSuccess(FilmModel film) {
                        Intent intent = new Intent(FilmActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Log.d("errorFilm",e.getMessage());
                    }
                });
            }
        });
    }
}