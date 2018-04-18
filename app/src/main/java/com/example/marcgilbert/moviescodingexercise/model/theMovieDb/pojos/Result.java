package com.example.marcgilbert.moviescodingexercise.model.theMovieDb.pojos;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private Integer voteCount;
    private Integer id;
    private Boolean video;
    private Integer voteAverage;
    private String title;
    private Double popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    private List<Integer> genreIds = null;
    private Object backdropPath;
    private Boolean adult;
    private String overview;
    private String releaseDate;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getVoteCount() {
        return voteCount;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getVideo() {
        return video;
    }

    public Integer getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public Object getBackdropPath() {
        return backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}

