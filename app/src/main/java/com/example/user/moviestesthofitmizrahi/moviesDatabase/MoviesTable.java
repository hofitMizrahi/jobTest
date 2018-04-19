package com.example.user.moviestesthofitmizrahi.moviesDatabase;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Database
 *
 * Class Model - Movies Table Class using SugarOrm Library
 */

public class MoviesTable extends SugarRecord {

    private String title;
    private String image;
    private double rating;
    private int releaseYear;
    private List<String> genre;

    public MoviesTable() {
    }

    public MoviesTable(String title, String image, double rating, int releaseYear, List<String> genre) {
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public double getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public List<String> getGenre() {
        return genre;
    }
}
