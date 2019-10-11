package raydel.isasi.movieinfoservice.pojo;

public class Movie {

	private int  MovieId;

	private String movieName;
	

	private String description;

	

	

	public Movie(int movieId, String movieName, String description) {
		super();
		MovieId = movieId;
		this.movieName = movieName;
		this.description = description;
	}

	public int getMovieId() {
		return MovieId;
	}

	public void setMovieId(int movieId) {
		MovieId = movieId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	
}
