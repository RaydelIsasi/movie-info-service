package raydel.isasi.movieinfoservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import raydel.isasi.movieinfoservice.pojo.Movie;
import raydel.isasi.movieinfoservice.service.MovieInfoService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/movies")
public class MovieInfoController {

    @Autowired
    MovieInfoService movieInfoService;


    @GetMapping("/{movieId}")
    public EntityModel<Movie> getMovieInfo(@PathVariable("movieId") int movieId) {
        Movie movie = movieInfoService.getMovieInfo(movieId);


        return EntityModel.of(movie, //
                linkTo(methodOn(MovieInfoController.class).getMovieInfo(movie.getMovieId())).withSelfRel(),
                linkTo(methodOn(MovieInfoController.class).getMoviesByPopularity(movie.getMovieId(), 0.0)).withRel("popularity"),
                linkTo(methodOn(MovieInfoController.class).getMoviesByLanguage(movie.getMovieId(), new String(""))).withRel("language"));

    }

    @GetMapping("/list/{listId}/language/{language}")
    public CollectionModel<EntityModel<Movie>> getMoviesByLanguage(@PathVariable("listId") int listId, @PathVariable("language") String language) {

        List<Movie> movieList = movieInfoService.getMoviesByLanguage(listId, language);

        List<EntityModel<Movie>> movies = movieList.stream().map(movie -> EntityModel.of(movie,
                linkTo(methodOn(MovieInfoController.class).getMoviesByLanguage(listId, language)).withSelfRel(),
                linkTo(methodOn(MovieInfoController.class).getMoviesByPopularity(listId, 0.0)).withRel("popularity"),
                linkTo(methodOn(MovieInfoController.class).getMovieInfo(movie.getMovieId())).withRel("movieInfo")
        )).
                collect(Collectors.toList());

        return CollectionModel.of(movies, //
                linkTo(methodOn(MovieInfoController.class).getMoviesByLanguage(listId, language)).withSelfRel());

    }


    @GetMapping("/list/{listId}/popularity/{popularity}")
    public CollectionModel<EntityModel<Movie>> getMoviesByPopularity(@PathVariable("listId") int listId, @PathVariable("popularity") double popularity) {
        List<Movie> movieList = movieInfoService.getMoviesByPopularity(listId, popularity);

        List<EntityModel<Movie>> movies = movieList.stream().map(movie -> EntityModel.of(movie,
                linkTo(methodOn(MovieInfoController.class).getMoviesByPopularity(listId, popularity)).withSelfRel(),
                linkTo(methodOn(MovieInfoController.class).getMoviesByLanguage(listId, new String())).withRel("language"),
                linkTo(methodOn(MovieInfoController.class).getMovieInfo(movie.getMovieId())).withRel("movieInfo")
        )).
                collect(Collectors.toList());
        return CollectionModel.of(movies, //
                linkTo(methodOn(MovieInfoController.class).getMoviesByPopularity(listId, popularity)).withSelfRel());
    }
}
