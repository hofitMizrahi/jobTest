package com.example.user.moviestesthofitmizrahi;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Toast;

import com.example.user.moviestesthofitmizrahi.interfaces.ChangeFragmentService;
import com.example.user.moviestesthofitmizrahi.interfaces.GitHubService;
import com.example.user.moviestesthofitmizrahi.model.Movie;
import com.example.user.moviestesthofitmizrahi.moviesDatabase.MoviesTable;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 17/04/2018.
 */

public class MoviesServiceProvider {

    private final String BASE_URL = "https://api.androidhive.info";
    private Retrofit retrofit;
    Context context;
    List<Movie> allMovies;

    public MoviesServiceProvider(Context context) {
        this.context = context;
        allMovies = new ArrayList<>();
    }

    public void getMoviesData() {

        // Initialize a new instance of progress dialog
        ProgressDialog pd = ProgressDialog.show( context, null, null, false, true );
        // Set the progress dialog background color transparent
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pd.setContentView(R.layout.progressbar_layout);
        //show the progress dialog
        pd.show();

        // Call Api with Retrofit To get json data
        GitHubService service = getRetrofit().create(GitHubService.class);
        Call<List<Movie>> callWMoviesData = service.getMoviesFromJson();

        callWMoviesData.enqueue(new Callback<List<Movie>>() {

            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

                allMovies = response.body();
                insertDataToDB();

                pd.dismiss();

                // go to list fragment
                ChangeFragmentService service = (ChangeFragmentService) context;
                service.changeToListFragment();
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

                Toast.makeText(context, "wrong retrofit", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }

    private Retrofit getRetrofit() {

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return this.retrofit;
    }

    private void insertDataToDB(){

        ArrayList<MoviesTable> allMoviesData = (ArrayList<MoviesTable>) MoviesTable.listAll(MoviesTable.class);

        // Check if there is no data in the table, if the table is empty need to insert
        if(allMoviesData.size() == 0){

            for(int i=0; i < allMovies.size(); i++){

                Movie movie = allMovies.get(i);

                // insert the movie and add to my movie table db
                MoviesTable movieToInsert = new MoviesTable(movie.getTitle(), movie.getImage(), movie.getRating(), movie.getReleaseYear(), movie.getGenre());
                movieToInsert.save();

            }
        }
    }
}
