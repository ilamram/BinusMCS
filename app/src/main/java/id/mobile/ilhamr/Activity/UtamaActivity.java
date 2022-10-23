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
import id.mobile.ilhamr.R;

public class UtamaActivity extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;
    ArrayList<UserModel> userModeArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        getSupportActionBar().hide();
        userModeArrayList = new ArrayList<>();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        userModeArrayList = (ArrayList<UserModel>) getIntent().getSerializableExtra("UserDataArrayList");
//        Log.e("TAG", "onCreate: " + userModeArrayList.toString() );
        bottomNavigationView.setSelectedItemId(R.id.home);
        HomeFragment homeFragment = new HomeFragment();
        TransactionFragment transactionFragment = new TransactionFragment();
        ProfileFragment profileFragment = new ProfileFragment();
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