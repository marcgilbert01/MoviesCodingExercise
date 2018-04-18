package com.example.marcgilbert.moviescodingexercise.model;


import io.reactivex.Observable;
import io.reactivex.Single;

public interface IMoviesAccess {

    Observable<MovieUi> searchMoviesFor(String searchQuery);

    Single<MovieUi> getMovieDetails(String movieId);

}
