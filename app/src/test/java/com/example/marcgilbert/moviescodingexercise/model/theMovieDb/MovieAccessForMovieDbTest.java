package com.example.marcgilbert.moviescodingexercise.model.theMovieDb;

import com.example.marcgilbert.moviescodingexercise.model.MovieUi;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.Single;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;


public class MovieAccessForMovieDbTest {

    private MoviesAccessForMovieDb mMoviesAccessForMovieDb;

    @Before
    public void setUp() throws Exception {
        mMoviesAccessForMovieDb = new MoviesAccessForMovieDb();
    }

    @Test
    public void testSearchMoviesFor_givenSearchQuery_thenReturnMovieList() {
        Observable<MovieUi> movieUiObservable = mMoviesAccessForMovieDb.searchMoviesFor("matrix");
        Iterable<MovieUi> movieUiIterable = movieUiObservable.blockingNext();

        MovieUi movieUi = movieUiObservable.blockingFirst();
        assertThat(movieUi, is(notNullValue()));
        assertThat(movieUi.getTitle(), is(IsNull.<String>notNullValue()));
        movieUiObservable.test().assertSubscribed();
        movieUiObservable.test().onComplete();
        // TODO more checks
    }

    @Test
    public void testGet() {
        Single<MovieUi> movieUiSingle = mMoviesAccessForMovieDb.getMovieDetails("603");

        MovieUi movieUi = movieUiSingle.blockingGet();
        assertThat(movieUi, is(notNullValue()));
        assertThat(movieUi.getTitle(), is(IsNull.<String>notNullValue()));
        assertThat(movieUi.getMovieDetails(), is(IsNull.<MovieUi.MovieDetailsUi>notNullValue()));
    }

}
