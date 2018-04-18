package com.example.user.moviestesthofitmizrahi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.user.moviestesthofitmizrahi.fragments.RecyclerAdapter;
import com.example.user.moviestesthofitmizrahi.moviesDatabase.MoviesTable;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //setting txt adapter
        RecyclerAdapter Adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(Adapter);

        (findViewById(R.id.addNewQR)).setOnClickListener((View view)->{

            Log.i("test", "buttom test");
            Intent intent = new Intent(this, QRActivity.class);
            startActivityForResult(intent, 2); // Activity is started with requestCode 2
        });
    }

// TODO get the string from the sceener code and chech is it found on the list db, if it is; show hos movie detailes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2)
        {
            String message=data.getStringExtra("MESSAGE");
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
