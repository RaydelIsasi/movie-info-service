package raydel.isasi.movieinfoservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import raydel.isasi.movieinfoservice.client.ClientRequestor;
import raydel.isasi.movieinfoservice.pojo.Movie;
import raydel.isasi.movieinfoservice.service.MovieInfoService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {

    @Autowired
    ClientRequestor clientRequestor;
    @Value("${api.key}")
    String apikey;

    @Value("${api.baseUrl}")
    String baseUrl;

    @Override
    public Movie getMovieInfo(int movieId) {
        Map<String, Object> response = clientRequestor.executeRequest(baseUrl + "movie/" + movieId + "?api_key=" + apikey);


        return new Movie(movieId, String.valueOf(response.get("overview")), String.valueOf(response.get("original_title")));


    }

    @Override
    public List<Movie> getMoviesByLanguage(int listId, String language) {
        Map<String, Object> response = clientRequestor.executeRequest(baseUrl + "list/" + listId + "?api_key=" + apikey);


        List<Map> movies = (List<Map>) response.get("items");

        return movies.stream().filter(moviemap -> String.valueOf(moviemap.get("original_language")).equalsIgnoreCase(language)).map(moviemap -> {

            return new Movie(Integer.parseInt(String.valueOf(moviemap.get("id"))), String.valueOf(moviemap.get("overview")), String.valueOf(moviemap.get("original_title")));

        }).collect(Collectors.toList());

    }

    @Override
    public List<Movie> getMoviesByPopularity(int listId, double popularity) {
        Map<String, Object> response = clientRequestor.executeRequest(baseUrl + "list/" + listId + "?api_key=" + apikey);


        List<Map> movies = (List<Map>) response.get("items");


        return movies.stream().filter(moviemap -> Double.parseDouble(String.valueOf(moviemap.get("popularity"))) > popularity).map(moviemap -> {

            return new Movie(Integer.parseInt(String.valueOf(moviemap.get("id"))), String.valueOf(moviemap.get("overview")), String.valueOf(moviemap.get("original_title")));

        }).collect(Collectors.toList());
    }


}
