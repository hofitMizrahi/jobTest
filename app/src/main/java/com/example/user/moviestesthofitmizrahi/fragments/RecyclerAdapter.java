package com.example.user.moviestesthofitmizrahi.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.user.moviestesthofitmizrahi.R;
import com.example.user.moviestesthofitmizrahi.interfaces.ChangeFragmentService;
import com.example.user.moviestesthofitmizrahi.moviesDatabase.MoviesTable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * RecyclerView Adapter
 */

class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.myViewHolder>{

    Context context;
    ArrayList<MoviesTable> moviesArray;

    public RecyclerAdapter(Context context) {

        this.context = context;
        moviesArray = (ArrayList<MoviesTable>) MoviesTable.listAll(MoviesTable.class);
    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.single_list_item, null);
        myViewHolder viewHolder = new myViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        MoviesTable movies = moviesArray.get(position);
        holder.bindMyCityData(movies);
    }

    @Override
    public int getItemCount() {
        return moviesArray.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        View myView;

        public myViewHolder(View itemView) {
            super(itemView);

            myView = itemView;
        }


        public void bindMyCityData(MoviesTable movie) {

            TextView title = myView.findViewById(R.id.title_TV);
            title.setText(movie.getTitle());

            //image resource
            ImageView imageView = myView.findViewById(R.id.list_image);

            //check if there is any image resource

                String url = movie.getImage();
                Picasso.with(context)
                        .load(url)
                        .resize(90, 90)
                        .centerCrop()
                        .into(imageView);


            myView.setOnClickListener((View view)->{


                AlertDialog.Builder customDialog
                        = new AlertDialog.Builder(context);
                customDialog.setTitle("Details Dialog");

                LayoutInflater layoutInflater
                        = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = layoutInflater.inflate(R.layout.details_dialog,null);

                customDialog.setView(v);
                customDialog.show();
                // go to list fragment
//                ChangeFragmentService service = (ChangeFragmentService) context;
//                service.changeFragments(movie);

            });

        }
    }
}
