package id.mobile.ilhamr.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import id.mobile.ilhamr.Fragment.HomeFragment;
import id.mobile.ilhamr.Fragment.ProfileFragment;
import id.mobile.ilhamr.Fragment.TransactionFragment;
import id.mobile.ilhamr.Model.MovieModel;
import id.mobile.ilhamr.Model.TransactionModel;
import id.mobile.ilhamr.Model.UserModel;
import id.mobile.ilhamr.MovieListener;
import id.mobile.ilhamr.R;

public class UtamaActivity extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;
    ArrayList<UserModel> userModeArrayList;
    String userName, phoneNumber, email;
    ArrayList<MovieModel> movieModelsArrayList;
    MovieModel movieModel, movieModel1, movieModel2;
    String movieName, moviePrice, moviesRating, moviesCountry;
    SharedPreferences sharedPreferences;
    int movieImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        getSupportActionBar().hide();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        sharedPreferences = getSharedPreferences(getString(R.string.savedKey), MODE_PRIVATE);
        userName = sharedPreferences.getString("userName","");
        phoneNumber = sharedPreferences.getString("phoneNumber","");
        email = sharedPreferences.getString("email","");
        if(getIntent().getExtras() != null){
            movieName = getIntent().getExtras().getString("movieName");
            moviePrice = getIntent().getExtras().getString("moviePrice");
            moviesCountry = getIntent().getExtras().getString("movieCountry");
            moviesRating = getIntent().getExtras().getString("movieRating");
            movieImg = getIntent().getExtras().getInt("movieImg");
            Log.e("TAG", "Movie Rating: " + moviesRating + "Movie Country: " + moviesCountry);
        }
//        userModeArrayList = (ArrayList<UserModel>) getIntent().getBundleExtra("bundle").getSerializable("userModelArray");
        bottomNavigationView.setSelectedItemId(R.id.home);
        userModeArrayList = new ArrayList<>();
//        for(UserModel userModel : userModeArrayList){
//            userName = userModel.getUserName();
//            phoneNumber = userModel.getUserPhoneNumber();
//            email = userModel.getUserEmail();
//        }
        movieModelsArrayList = new ArrayList<>();
        movieModel = new MovieModel();
        movieModel.setImgDrawable(R.drawable.breakingbad);
        movieModel.setMovieName("Breaking Bad");
        movieModel.setMoviePrice("Rp. 500.000,00");
        movieModel.setMovieCountry("America");
        movieModel.setMovieRating("5.0");
        movieModel.setMovieDescription("Walter White is on the hustle");
        movieModelsArrayList.add(movieModel);

        movieModel1 = new MovieModel();
        movieModel1.setImgDrawable(R.drawable.megan);
        movieModel1.setMovieName("MEGAN");
        movieModel1.setMoviePrice("Rp. 500.000,00");
        movieModel1.setMovieCountry("America");
        movieModel1.setMovieRating("4.5");
        movieModel1.setMovieDescription("The doll that slays");
        movieModelsArrayList.add(movieModel1);

        movieModel2 = new MovieModel();
        movieModel2.setImgDrawable(R.drawable.jav);
        movieModel2.setMovieName("MEYD - 678 Wife Swap");
        movieModel2.setMoviePrice("Rp. 500.000,00");
        movieModel2.setMovieCountry("Japan");
        movieModel2.setMovieRating("4.5");
        movieModel2.setMovieDescription("Mayuka Suzuki");
        movieModelsArrayList.add(movieModel2);

        HomeFragment homeFragment = new HomeFragment(movieModelsArrayList);
        TransactionFragment transactionFragment = new TransactionFragment( movieName, moviePrice, moviesCountry, moviesRating, movieImg);
        ProfileFragment profileFragment = new ProfileFragment(userName, phoneNumber, email);
        selectedCurrentFragment(homeFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home: {
                        selectedCurrentFragment(homeFragment);
                    }
                    break;
                    case R.id.transaction: {
                        selectedCurrentFragment(transactionFragment);
                    }
                    break;
                    case R.id.profile: {
                        selectedCurrentFragment(profileFragment);
                    }
                    break;

                }
                return true;
            }
        });
    }


    private void selectedCurrentFragment(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_fragment, fragment);
        ft.addToBackStack((String)null);
        ft.commit();
    }


}