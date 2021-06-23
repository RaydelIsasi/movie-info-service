package raydel.isasi.movieinfoservice.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import raydel.isasi.movieinfoservice.pojo.Movie;
import raydel.isasi.movieinfoservice.pojo.RequestMovieService;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

    @Autowired
    RestTemplate resttemplate;
    @Value("${api.key}")
    String apikey;

    @Value("${api.baseUrl}")
    String baseUrl;


    @PostMapping("/{movieId}")
    public Object getMovieInfo(@PathVariable("movieId") int movieid) {
        Map<String, Object> response = resttemplate
                .getForObject(baseUrl + "movie/" + movieid + "?api_key=" + apikey, Map.class);


        return new Movie(movieid, String.valueOf(response.get("overview")), String.valueOf(response.get("original_title")));


    }

    @PostMapping("/language/list/{listId}")
    public List<Movie> getMoviesByLanguage(@PathVariable("listId") int listId, @RequestBody RequestMovieService request) {
        Map<String, Object> response = resttemplate
                .getForObject(baseUrl + "list/" + listId + "?api_key=" + apikey, Map.class);


        List<Map> movies = (List<Map>) response.get("items");

        return movies.stream().filter(moviemap -> String.valueOf(moviemap.get("original_language")).equalsIgnoreCase(request.getLanguage())).map(moviemap -> {

            return new Movie(Integer.parseInt(String.valueOf(moviemap.get("id"))), String.valueOf(moviemap.get("overview")), String.valueOf(moviemap.get("original_title")));

        }).collect(Collectors.toList());


    }


    @PostMapping("/popularity/list/{listId}")
    public List<Movie> getMoviesByPopularity(@PathVariable("listId") int listId, @RequestBody RequestMovieService request) {
        Map<String, Object> response = resttemplate
                .getForObject(baseUrl + "list/" + listId + "?api_key=" + apikey, Map.class);


        List<Map> movies = (List<Map>) response.get("items");


        return movies.stream().filter(moviemap -> Double.parseDouble(String.valueOf(moviemap.get("popularity"))) > request.getPopularity()).map(moviemap -> {

            return new Movie(Integer.parseInt(String.valueOf(moviemap.get("id"))), String.valueOf(moviemap.get("overview")), String.valueOf(moviemap.get("original_title")));

        }).collect(Collectors.toList());


    }
}
