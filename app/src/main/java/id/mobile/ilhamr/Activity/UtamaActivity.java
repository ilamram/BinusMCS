package id.mobile.ilhamr.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import id.mobile.ilhamr.Fragment.HomeFragment;
import id.mobile.ilhamr.Fragment.ProfileFragment;
import id.mobile.ilhamr.Fragment.TransactionFragment;
import id.mobile.ilhamr.Model.UserModel;
import id.mobile.ilhamr.MovieListener;
import id.mobile.ilhamr.R;

public class UtamaActivity extends AppCompatActivity implements MovieListener {

    BottomNavigationView bottomNavigationView;
    ArrayList<UserModel> userModeArrayList;
    String userName, phoneNumber, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        getSupportActionBar().hide();
        userModeArrayList = new ArrayList<>();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        userModeArrayList = (ArrayList<UserModel>) getIntent().getBundleExtra("BUNDLE").getSerializable("ARRAYLIST");
        bottomNavigationView.setSelectedItemId(R.id.home);
        for(UserModel userModel : userModeArrayList){
            userName = userModel.getUserName();
            phoneNumber = userModel.getUserPhoneNumber();
            email = userModel.getUserEmail();
        }
        HomeFragment homeFragment = new HomeFragment();
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
        ft.addToBackStack((String)null);
        ft.commit();
    }

    @Override
    public void addMovie(String movieName, String moviePrice) {
        Log.e("TAG", "addMovie2: " );
        TransactionFragment fragmentB = new TransactionFragment();
        Bundle args = new Bundle();
        args.putString("movieName", movieName);
        args.putString("moviePrice", moviePrice);
        fragmentB.setArguments(args);
        Log.e("TAG", "addMovie: " +movieName );

    }
}