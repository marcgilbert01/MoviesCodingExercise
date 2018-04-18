package com.example.marcgilbert.moviescodingexercise.model;

import java.util.List;

public class MovieUi {

    private String mId;
    private String mTitle;
    private String mPosterUrl;
    private String mReleaseDate;
    private String mOverview;
    private MovieDetailsUi mMovieDetails;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPosterUrl() {
        return mPosterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        mPosterUrl = posterUrl;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public MovieDetailsUi getMovieDetails() {
        return mMovieDetails;
    }

    public void setMovieDetails(MovieDetailsUi movieDetails) {
        mMovieDetails = movieDetails;
    }

    static public class MovieDetailsUi {

        private List<String> genres;

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }
    }


}
