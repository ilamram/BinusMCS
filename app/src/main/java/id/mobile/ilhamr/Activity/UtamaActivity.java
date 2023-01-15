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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.mobile.ilhamr.DBManager;
import id.mobile.ilhamr.Fragment.HomeFragment;
import id.mobile.ilhamr.Fragment.ProfileFragment;
import id.mobile.ilhamr.Fragment.TransactionFragment;
import id.mobile.ilhamr.Model.MovieModel;
import id.mobile.ilhamr.Model.MovieVolleyModel.FilmsItem;
import id.mobile.ilhamr.Model.TransactionModel;
import id.mobile.ilhamr.Model.UserModel;
import id.mobile.ilhamr.R;

public class UtamaActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ArrayList<FilmsItem> filmsModeArrayList = new ArrayList<>();
    String userName, phoneNumber, email;
    String movieName, moviePrice, moviesRating, moviesCountry, movieTickets;
    SharedPreferences sharedPreferences;
    int movieImg;
    RequestQueue queue;
    JsonObjectRequest jsonArrayRequest;
    DBManager dbManager;
    String url = "https://mocki.io/v1/ce4395c2-d593-45ae-b392-78fe2238369c";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        getSupportActionBar().hide();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        sharedPreferences = getSharedPreferences(getString(R.string.savedKey), MODE_PRIVATE);
        filmsModeArrayList = new ArrayList<>();
        dbManager = new DBManager(UtamaActivity.this);
        setAdapter(dbManager.retrieveMovieList());
    }
    //Modifier private karena kita cuman pake methodnya untuk di kelas ini aja.
    private void setAdapter(ArrayList<FilmsItem> filmsModeArrayList) {
        bottomNavigationView.setSelectedItemId(R.id.home);
        HomeFragment homeFragment = new HomeFragment(filmsModeArrayList);
//        TransactionFragment transactionFragment = new TransactionFragment(movieName, moviePrice, moviesCountry, moviesRating, movieImg, movieTickets);
        TransactionFragment transactionFragment = new TransactionFragment();
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
        ft.addToBackStack((String) null);
        ft.commit();
    }
}