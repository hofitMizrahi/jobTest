
package com.example.user.moviestesthofitmizrahi.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Json Movie Model Class
 */

public class Movie {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("rating")
    @Expose
    private double rating;

    @SerializedName("releaseYear")
    @Expose
    private int releaseYear;

    @SerializedName("genre")
    @Expose
    private List<String> genre = null;

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
