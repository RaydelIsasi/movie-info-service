package raydel.isasi.movieinfoservice.pojo;

public class RequestMovieService {

    private double popularity;

    public RequestMovieService(double popularity, String language) {
        this.popularity = popularity;
        this.language = language;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    private String language;
}
