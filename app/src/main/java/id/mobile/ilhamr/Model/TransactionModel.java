package id.mobile.ilhamr.Model;

public class TransactionModel {
    
    String movieName, moviePrice, moviesRating, moviesCountry, movieQty;
    int imgMovie;

    public String getMovieQty() {
        return movieQty;
    }

    public void setMovieQty(String movieQty) {
        this.movieQty = movieQty;
    }

    public int getImgMovie() {
        return imgMovie;
    }

    public void setImgMovie(int imgMovie) {
        this.imgMovie = imgMovie;
    }

    public String getMoviesRating() {
        return moviesRating;
    }

    public void setMoviesRating(String moviesRating) {
        this.moviesRating = moviesRating;
    }

    public String getMoviesCountry() {
        return moviesCountry;
    }

    public void setMoviesCountry(String moviesCountry) {
        this.moviesCountry = moviesCountry;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMoviePrice() {
        return moviePrice;
    }

    public void setMoviePrice(String moviePrice) {
        this.moviePrice = moviePrice;
    }
}
