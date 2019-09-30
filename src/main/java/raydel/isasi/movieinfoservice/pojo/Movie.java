package raydel.isasi.movieinfoservice.pojo;

public class Movie {

	private String MovieId;

	private String movieName;

	public String getMovieId() {
		return MovieId;
	}

	public void setMovieId(String movieId) {
		MovieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Movie(String movieId, String movieName) {
		super();
		MovieId = movieId;
		this.movieName = movieName;
	}
}
