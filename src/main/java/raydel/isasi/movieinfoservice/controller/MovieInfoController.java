package raydel.isasi.movieinfoservice.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import raydel.isasi.movieinfoservice.pojo.Movie;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

	@Autowired
	RestTemplate resttemplate;
	@Value("${api.key}")
	String apikey;

	@SuppressWarnings("unchecked")
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") int movieid) {
		Map<String, Object> response = resttemplate
				.getForObject("https://api.themoviedb.org/3/movie/" + movieid + "?api_key=" + apikey, Map.class);

		String description = response.get("overview").toString();

		String originaltitle = response.get("original_title").toString();

		return new Movie(movieid, description, originaltitle);

	}
}
