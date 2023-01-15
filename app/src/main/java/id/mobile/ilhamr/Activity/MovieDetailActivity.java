package id.mobile.ilhamr.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.mobile.ilhamr.DBManager;
import id.mobile.ilhamr.Model.MovieModel;
import id.mobile.ilhamr.Model.MovieVolleyModel.FilmsItem;
import id.mobile.ilhamr.R;

public class MovieDetailActivity extends AppCompatActivity {

    String imgMovie, movieId;
    String movieName, movieRating, movieCountry, moviePrice, movieDescription;
    ImageView ivMovieDetail, add, remove;
    Button btnBuy;
    TextView tvMovieTitleDetails, tvQuantityTickets, tvMovieRatingDetails, tvMovieCountryDetails, tvMoviePriceDetails, tvMovieDescription;
    DBManager dbManager;
    SharedPreferences sharedPreferences;
    int userID;
    FilmsItem filmsItem;
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
        tvQuantityTickets = findViewById(R.id.tv_quantity_movie_detail);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        sharedPreferences = getSharedPreferences(getString(R.string.savedKey), MODE_PRIVATE);
        userID = sharedPreferences.getInt("userID", 0);
        dbManager = new DBManager(MovieDetailActivity.this);
        movieId = getIntent().getExtras().getString("Movie ID");
        filmsItem = dbManager.getFilmsDataById(movieId);
//        imgMovie = getIntent().getExtras().getString("Movie Image");
//        movieName = getIntent().getExtras().getString("Movie Title");
//        movieDescription = getIntent().getExtras().getString("Movie Description");
//        movieRating = getIntent().getExtras().getString("Movie Rating");
//        moviePrice = getIntent().getExtras().getString("Movie Price");
//        movieCountry = getIntent().getExtras().getString("Movie Country");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(filmsItem.getTitle());
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        tvMovieCountryDetails.setText(filmsItem.getCountry());
        tvMovieTitleDetails.setText(filmsItem.getTitle());
        tvMovieRatingDetails.setText(filmsItem.getRating());
        tvMoviePriceDetails.setText("$ " + filmsItem.getPrice());
        tvMovieDescription.setText(filmsItem.getDescription());
        Picasso.Builder builder = new Picasso.Builder(this);
        Picasso picasso = builder.build();
        picasso.load(filmsItem.getImage())
                .into(ivMovieDetail, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                    }
                });
       add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringQuantity = tvQuantityTickets.getText().toString();
                Integer quantity = Integer.parseInt(stringQuantity);
                quantity++;
                tvQuantityTickets.setText(quantity.toString());
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringQuantity = tvQuantityTickets.getText().toString();
                Integer quantity = Integer.parseInt(stringQuantity);
                if(quantity <= 1){
                    Toast.makeText(MovieDetailActivity.this, "You can't order below 0", Toast.LENGTH_SHORT).show();
                }else{
                    quantity--;
                }
                tvQuantityTickets.setText(quantity.toString());
            }
        });
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailActivity.this, UtamaActivity.class);
                dbManager.saveTransaction(tvQuantityTickets.getText().toString(), String.valueOf(userID), movieId);
//                intent.putExtra("movieName", movieName);
//                intent.putExtra("moviePrice", moviePrice);
//                intent.putExtra("movieCountry", movieCountry);
//                intent.putExtra("movieRating", movieRating);
//                intent.putExtra("movieImg", imgMovie);
//                Log.e("TAG", "onClick: " + ticketQuantity );
//                intent.putExtra("movieQuantity", tvQuantityTickets.getText().toString());
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