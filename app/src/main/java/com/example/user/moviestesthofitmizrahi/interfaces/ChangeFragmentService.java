package com.example.user.moviestesthofitmizrahi.interfaces;

import com.example.user.moviestesthofitmizrahi.moviesDatabase.MoviesTable;

/**
 * Created by user on 18/04/2018.
 */

public interface ChangeFragmentService {

    void changeFragments(MoviesTable movie);

    void changeToListFragment();

    void changeToQRFragment();
}
