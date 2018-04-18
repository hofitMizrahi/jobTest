package com.example.user.moviestesthofitmizrahi;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.user.moviestesthofitmizrahi.moviesDatabase.MoviesTable;

/**
 * Created by user on 18/04/2018.
 */

public class DetailsMovieDialog {

    Context context;
    MoviesTable movie;

    public DetailsMovieDialog(Context context, MoviesTable movie) {
        this.context = context;
        this.movie = movie;
    }

    public void setDialog() {

        AlertDialog.Builder customDialog
                = new AlertDialog.Builder(context);
        customDialog.setTitle("Details Dialog");

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.details_dialog, null);


        customDialog.setView(v);
        customDialog.show();
    }

}
