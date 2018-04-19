package com.example.user.moviestesthofitmizrahi.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.user.moviestesthofitmizrahi.R;
import com.example.user.moviestesthofitmizrahi.adapter.RecyclerAdapter;
import com.example.user.moviestesthofitmizrahi.moviesDatabase.MoviesTable;

import java.util.ArrayList;

/**
 * Screen Two - Movies ListView Activity
 *
 *  use RecyclerView and Adapter
 */

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter Adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(Adapter);

        //add movie with QReader activity
        (findViewById(R.id.addNewQR)).setOnClickListener((View view)->{

            //OPEN QReader that user screen sum barcode and it will return String result.
            Intent intent = new Intent(this, QRActivity.class);
            startActivityForResult(intent, 2); // Activity is started with requestCode 2
        });
    }

    //onActivityResult to get the string that returned from the QReader.
    // check in the String already inside on the DB, if it is show movie details.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2)
        {
            String message = data.getStringExtra("MESSAGE");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            ArrayList<MoviesTable> myMoviesDB = (ArrayList<MoviesTable>) MoviesTable.listAll(MoviesTable.class);

            for(int i = 0; i < myMoviesDB.size(); i++){

                MoviesTable movie = myMoviesDB.get(i);
                if((movie.getTitle()).equals(message)) {

                DetailsMovieDialog movieDialog = new DetailsMovieDialog(this, movie);
                movieDialog.setDialog();

                }
            }
        }

    }
}
