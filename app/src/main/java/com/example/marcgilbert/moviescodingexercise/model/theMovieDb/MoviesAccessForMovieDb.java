package com.example.marcgilbert.moviescodingexercise.model.theMovieDb;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.marcgilbert.moviescodingexercise.model.IMoviesAccess;
import com.example.marcgilbert.moviescodingexercise.model.MovieUi;
import com.example.marcgilbert.moviescodingexercise.model.theMovieDb.pojos.Genre;
import com.example.marcgilbert.moviescodingexercise.model.theMovieDb.pojos.MovieDetails;
import com.example.marcgilbert.moviescodingexercise.model.theMovieDb.pojos.MovieListResponse;
import com.example.marcgilbert.moviescodingexercise.model.theMovieDb.pojos.Result;
import com.example.marcgilbert.moviescodingexercise.model.theMovieDb.retrofit.MovieService;
import com.example.marcgilbert.moviescodingexercise.model.theMovieDb.retrofit.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import retrofit2.Response;

public class MoviesAccessForMovieDb implements IMoviesAccess {

    private final String BASE_URL = "https://api.themoviedb.org";
    private final String API_KEY = "4cb1eeab94f45affe2536f2c684a5c9e"; // TODO SHOULD BE ENCRYPTED
    private final int SEARCH_QUERY_MIN_SIZE = 3;

    @Override
    public Observable<MovieUi> searchMoviesFor(final String searchQuery) {
        return Observable.create(new ObservableOnSubscribe<MovieUi>() {
            @Override
            public void subscribe(ObservableEmitter<MovieUi> emitter) throws Exception {
                if (searchQuery != null && searchQuery.length() >= SEARCH_QUERY_MIN_SIZE) {
                    List<Result> results = searchMovies(searchQuery);
                    for (Result result : results) {
                        MovieUi movieUi = createMovieUi(result);
                        if (movieUi != null) {
                            emitter.onNext(movieUi);
                        }
                    }
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception("Search query should not be null or empty"));
                }
            }
        });
    }

    @Override
    public Single<MovieUi> getMovieDetails(final String movieId) {
        return Single.fromCallable(new Callable<MovieUi>() {
            @Override
            public MovieUi call() throws Exception {
                if (movieId != null) {
                    MovieDetails movieDetails = getMovieDetailsFor(movieId);
                    MovieUi movieUi = createMovieUi(movieDetails);
                    if (movieUi != null) {
                        return movieUi;
                    }
                }
                throw new NullPointerException();
            }
        });
    }

    @Nullable
    private MovieUi createMovieUi(@Nullable MovieDetails movieDetails) {
        if (movieDetails != null) {
            MovieUi movieUi = new MovieUi();
            movieUi.setId(movieDetails.getId().toString());
            movieUi.setTitle(movieDetails.getTitle());
            movieUi.setOverview(movieDetails.getOverview());
            movieUi.setReleaseDate(movieDetails.getReleaseDate());
            movieUi.setPosterUrl(BASE_URL + movieDetails.getPosterPath());

            MovieUi.MovieDetailsUi movieDetailsUi = new MovieUi.MovieDetailsUi();
            List<String> genres = getGenres(movieDetails.getGenres());
            if (genres != null) {
                movieDetailsUi.setGenres(genres);
            }
            movieUi.setMovieDetails(movieDetailsUi);

            return movieUi;
        }
        return null;
    }

    @Nullable
    private List<String> getGenres(List<Genre> genreList) {
        if (genreList != null) {
            List<String> genres = new ArrayList<>();
            for (Genre genre : genreList) {
                genres.add(genre.getName());
            }
            return genres;
        }
        return null;
    }

    @Nullable
    private List<Result> searchMovies(@NonNull String searchQuery) throws IOException {
        MovieService movieService = RetrofitClient.getClient(BASE_URL).create(MovieService.class);
        Response<MovieListResponse> response = movieService.getMovieList(searchQuery, API_KEY).execute();
        MovieListResponse movieListResponse = response.body();

        return movieListResponse.getResults();
    }

    @Nullable
    private MovieDetails getMovieDetailsFor(@NonNull String movieId) throws IOException {
        MovieService movieService = RetrofitClient.getClient(BASE_URL).create(MovieService.class);
        Response<MovieDetails> response = movieService.getMovieDetails(movieId, API_KEY).execute();
        MovieDetails movieDetails = response.body();

        return movieDetails;
    }

    @Nullable
    private MovieUi createMovieUi(@Nullable Result result) {
        if (result != null) {
            MovieUi movieUi = new MovieUi();
            movieUi.setId(result.getId().toString());
            movieUi.setTitle(result.getTitle());
            movieUi.setPosterUrl(BASE_URL + result.getPosterPath());
            movieUi.setReleaseDate(result.getReleaseDate());
            movieUi.setOverview(result.getOverview());

            return movieUi;
        }
        return null;
    }


}
