package com.example.marcgilbert.moviescodingexercise.model.theMovieDb.retrofit;


import com.example.marcgilbert.moviescodingexercise.model.theMovieDb.pojos.MovieDetails;
import com.example.marcgilbert.moviescodingexercise.model.theMovieDb.pojos.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("/3/search/movie?")
    Call<MovieListResponse> getMovieList(@Query("query") String searchQuery, @Query("api_key") String apiKey);

    @GET("/3/movie/{id}?")
    Call<MovieDetails> getMovieDetails(@Path("id") String movieId, @Query("api_key") String apiKey);

}
