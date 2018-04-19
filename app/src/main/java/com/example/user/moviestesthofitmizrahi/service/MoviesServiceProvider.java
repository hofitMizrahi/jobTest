package com.example.user.moviestesthofitmizrahi.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.Toast;

import com.example.user.moviestesthofitmizrahi.R;
import com.example.user.moviestesthofitmizrahi.activity.ListActivity;
import com.example.user.moviestesthofitmizrahi.interfaces.GitHubService;
import com.example.user.moviestesthofitmizrahi.model.Movie;
import com.example.user.moviestesthofitmizrahi.moviesDatabase.MoviesTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Helper Class - that tack care on the Retrofit Call with the Api
 *
 * this object will open just if the TableMovie database is empty
 *
 * Insert the result from the Json file To the DataBase
 */

public class MoviesServiceProvider {

    private final String BASE_URL = "https://api.androidhive.info";

    private Retrofit retrofit;
    private ProgressDialog dialog;
    private Context context;
    private List<Movie> allMovies;

    // Class Constructor
    public MoviesServiceProvider(Context context) {
        this.context = context;
        allMovies = new ArrayList<>();
    }

    // Main method that call retrofit and insert the json response to movie database table
    public void getMoviesData() {

        //open progress
        openProgressDialog();

        GitHubService service = getRetrofit().create(GitHubService.class);
        Call<List<Movie>> callWMoviesData = service.getMoviesFromJson();

        // Call Api with Retrofit To get json data body
        callWMoviesData.enqueue(new Callback<List<Movie>>() {

        @Override
        public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

            //get List response from the Sync Http Retrofit call
            allMovies = response.body();

            //insert the data from the json to my database Movies table
            insertDataToDB();

            //close progress
            closeProgressDialog();

            // go to list fragment
            Intent intent = new Intent(context, ListActivity.class);
            context.startActivity(intent);
        }

        @Override
        public void onFailure(Call<List<Movie>> call, Throwable t) {

            // fail call, maybe don't have internet connection
            Toast.makeText(context, "Retrofit Dismiss", Toast.LENGTH_SHORT).show();
            closeProgressDialog();
            }
        });
    }

//------------------------------------------------------------------------------------------------------

    // init Retrofit Object

    private Retrofit getRetrofit() {

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return this.retrofit;
    }

//-----------------------------------------------------------------------------------------------------

    // progress Dialog open end close methods

    private void closeProgressDialog() {

        dialog.dismiss();
    }

    private void openProgressDialog() {

        // Initialize a new instance of progress dialog
        dialog = ProgressDialog.show( context, null, null, false, true );
        // Set the progress dialog background color transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.progressbar_layout);
        //show the progress dialog
        dialog.show();
    }

//-------------------------------------------------------------------------------------------------------

    //this method insert the result array to the database

    private void insertDataToDB(){

        ArrayList<MoviesTable> allMoviesData = (ArrayList<MoviesTable>) MoviesTable.listAll(MoviesTable.class);

        // this array get ordenaize array that order from new to old by parse() method
        int[] newToOldArray = orderReleaseYearFromNewToOld();

        // go over newToOldArray array
        // add the Json movie to the database in order from new to old

        for(int i = newToOldArray.length-1 ; i >= 0 ; i--){

            for(int j = 0; j < allMovies.size() ; j++) {

                //if the current new year equals to movie object year ->
                //Insert it to the database
                if (newToOldArray[i] == allMovies.get(j).getReleaseYear()) {

                    Movie movie = allMovies.get(j);

                    // insert the movie and add to my movie table db
                    MoviesTable movieToInsert = new MoviesTable(movie.getTitle(),
                                                    movie.getImage(), movie.getRating(),
                                                    movie.getReleaseYear(), movie.getGenre());

                    // SAVE the movie to database
                    movieToInsert.save();

                    //delete the saved movie from the array
                    allMovies.remove(j);
                }
            }
        }
    }

//---------------------------------------------------------------------------------------------------

    //TODO
    // this method return orgenaize array of release year from new to old

    private int[]  orderReleaseYearFromNewToOld(){

        int[] newToOldArray = new int[allMovies.size()];

        // get array of all the Movies years
        for(int i = 0; i < allMovies.size(); i++) {

            //inset the year
            newToOldArray[i] = allMovies.get(i).getReleaseYear();
        }

        // order Integer method from min value to max value
        Arrays.sort(newToOldArray);

        return newToOldArray;
    }
}
