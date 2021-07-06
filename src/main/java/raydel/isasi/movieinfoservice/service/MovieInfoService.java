package raydel.isasi.movieinfoservice.service;

import raydel.isasi.movieinfoservice.pojo.Movie;

import java.util.List;

public interface MovieInfoService {

    Movie getMovieInfo(int movieId);

    List<Movie> getMoviesByLanguage(int listId, String language);

    public List<Movie> getMoviesByPopularity(int listId, double popularity);
}
