package com.example.user.moviestesthofitmizrahi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.user.moviestesthofitmizrahi.service.MoviesServiceProvider;
import com.example.user.moviestesthofitmizrahi.R;
import com.example.user.moviestesthofitmizrahi.moviesDatabase.MoviesTable;

import java.util.ArrayList;

/**
 * Screen One - Start Activity just with one button
 *
 *  movie API = https://api.androidhive.info/json/movies.json
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            ((ImageView)findViewById(R.id.go_image)).setOnClickListener((View view)->{

            moveToSecondScreen();

        });
    }

    //open the second activity to show ListView of all the movies from the Json file to DB.
    private void moveToSecondScreen(){

        ArrayList<MoviesTable> allMoviesData = (ArrayList<MoviesTable>) MoviesTable.listAll(MoviesTable.class);

        // CHECK if my database empty
        if(allMoviesData.isEmpty()){

            //START call retrofitService and UPDATE the database
            MoviesServiceProvider moviesServiceProvider = new MoviesServiceProvider(this);
            moviesServiceProvider.getMoviesData();

        }else {

            // OPEN screen 2 (Movies list)
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        }
    }

}
