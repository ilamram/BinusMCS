package id.mobile.ilhamr.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.mobile.ilhamr.Model.MovieModel;
import id.mobile.ilhamr.MovieListener;
import id.mobile.ilhamr.R;

public class MovieDetailActivity extends AppCompatActivity {

    int imgMovie;
    String movieName, movieRating, movieCountry, moviePrice, movieDescription;
    ImageView ivMovieDetail;
    Button btnBuy;
    TextView tvMovieTitleDetails, tvMovieRatingDetails, tvMovieCountryDetails, tvMoviePriceDetails, tvMovieDescription;
    MovieListener movieListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ivMovieDetail = findViewById(R.id.iv_movies_details);
        tvMovieTitleDetails = findViewById(R.id.txt_movie_title_details);
        tvMovieRatingDetails = findViewById(R.id.tv_movie_rating);
        tvMovieCountryDetails = findViewById(R.id.tv_movie_country);
        tvMoviePriceDetails = findViewById(R.id.tv_movie_money);
        tvMovieDescription = findViewById(R.id.tv_description_movie);
        btnBuy = findViewById(R.id.btn_buy_movie);
        imgMovie = getIntent().getExtras().getInt("Movie Image");
        movieName = getIntent().getExtras().getString("Movie Title");
        movieDescription = getIntent().getExtras().getString("Movie Description");
        movieRating = getIntent().getExtras().getString("Movie Rating");
        moviePrice = getIntent().getExtras().getString("Movie Price");
        movieCountry = getIntent().getExtras().getString("Movie Country");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(movieName);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        tvMovieCountryDetails.setText(movieCountry);
        tvMovieTitleDetails.setText(movieName);
        tvMovieRatingDetails.setText(movieRating);
        tvMoviePriceDetails.setText(moviePrice);
        tvMovieDescription.setText(movieDescription);

        Picasso.Builder builder = new Picasso.Builder(this);
        Picasso picasso = builder.build();
        picasso.load(imgMovie)
                .into(ivMovieDetail, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                    }
                });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailActivity.this, UtamaActivity.class);
                intent.putExtra("movieName", movieName);
                intent.putExtra("moviePrice", moviePrice);
                intent.putExtra("movieCountry", movieCountry);
                intent.putExtra("movieRating", movieRating);
                intent.putExtra("movieImg", imgMovie);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


//    @Override
//    public void addMovie(String movieName, String moviePrice) {
//        Log.e("TAG", "addMovie: " + movieName + moviePrice);
//    }
}