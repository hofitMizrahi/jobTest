package com.example.user.moviestesthofitmizrahi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 *
 *  movie API = https://api.androidhive.info/json/movies.json
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){

            setContentView(R.layout.activity_main);
        }else {

            changeFragments();
        }

        ((ImageView)findViewById(R.id.go_IV)).setOnClickListener((View view)->{

            MoviesServiceProvider moviesServiceProvider = new MoviesServiceProvider(this);
            moviesServiceProvider.getMoviesData();

            //TODO go to next screen --> list fragment screen
            changeFragments();

            ((ImageView)findViewById(R.id.go_IV)).setVisibility(View.INVISIBLE);
        });
    }


    private void changeFragments() {

        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
