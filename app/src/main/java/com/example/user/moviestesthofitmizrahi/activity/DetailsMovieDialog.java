package com.example.user.moviestesthofitmizrahi.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.user.moviestesthofitmizrahi.R;
import com.example.user.moviestesthofitmizrahi.moviesDatabase.MoviesTable;
import com.squareup.picasso.Picasso;

/**
 * Screen Three - Details Dialog
 */

public class DetailsMovieDialog {

    Context context;
    MoviesTable movie;

    public DetailsMovieDialog(Context context, MoviesTable movie) {
        this.context = context;
        this.movie = movie;
    }

    public void setDialog() {

        Dialog customDialog
                = new Dialog(context);

        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.details_dialog, null);

        ((TextView) v.findViewById(R.id.year)).setText(String.valueOf("Release year: " + movie.getReleaseYear()));

        ((TextView) v.findViewById(R.id.dialog_title)).setText(String.valueOf(movie.getTitle()));

        //image resource
        ImageView imageView = v.findViewById(R.id.dialog_image);

        //check if there is any image resource
        String url = movie.getImage();
        Picasso.with(context)
                .load(url)
                .resize(90, 90)
                .centerCrop()
                .into(imageView);

        ((RatingBar)v.findViewById(R.id.ratingBar)).setRating((float)(movie.getRating() / 2));

        customDialog.setContentView(v);
        customDialog.show();
    }

}
