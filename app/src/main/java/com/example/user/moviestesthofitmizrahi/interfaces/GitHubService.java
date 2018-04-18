package com.example.user.moviestesthofitmizrahi.interfaces;

import com.example.user.moviestesthofitmizrahi.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 17/04/2018.
 */

public interface GitHubService {

    @GET("/json/movies.json")
    Call<List<Movie>> getMoviesFromJson();
}
