package raydel.isasi.movieinfoservice.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import raydel.isasi.movieinfoservice.pojo.Movie;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieid) {
		return new Movie(movieid, "SCIFi");

	}
}
