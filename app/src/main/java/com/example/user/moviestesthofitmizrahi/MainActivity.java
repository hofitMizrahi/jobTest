package com.example.user.moviestesthofitmizrahi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.user.moviestesthofitmizrahi.fragments.DetailsFragment;
import com.example.user.moviestesthofitmizrahi.fragments.ListFragment;
import com.example.user.moviestesthofitmizrahi.interfaces.ChangeFragmentService;
import com.example.user.moviestesthofitmizrahi.moviesDatabase.MoviesTable;

/**
 *
 *  movie API = https://api.androidhive.info/json/movies.json
 */

public class MainActivity extends AppCompatActivity implements ChangeFragmentService{

    ListFragment listFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ((ImageView)findViewById(R.id.go_IV)).setOnClickListener((View view)->{

            MoviesServiceProvider moviesServiceProvider = new MoviesServiceProvider(this);
            moviesServiceProvider.getMoviesData();

            //TODO go to next screen --> list fragment screen

            ((ImageView)findViewById(R.id.go_IV)).setVisibility(View.INVISIBLE);
        });
    }

    @Override
    public void changeFragments(MoviesTable movie) {

        DetailsFragment detailsFragment = new DetailsFragment();
        getFragmentManager().beginTransaction().replace(R.id.go_main_layout, detailsFragment).commit();
    }

    @Override
    public void changeToListFragment() {

        listFragment = new ListFragment();
        getFragmentManager().beginTransaction().replace(R.id.go_main_layout, listFragment).commit();
    }

    @Override
    public void changeToQRFragment() {

    }
}
