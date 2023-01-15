package id.mobile.ilhamr.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import id.mobile.ilhamr.Activity.AboutUsActivity;
import id.mobile.ilhamr.Activity.LoginActivity;
import id.mobile.ilhamr.DBManager;
import id.mobile.ilhamr.Model.UserModel;
import id.mobile.ilhamr.R;


public class ProfileFragment extends Fragment {

    TextView tvUsername, tvPhoneNumber, tvEmail, tvAboutUs;
    String userName, email, phoneNumber;
    Button btnLogOut;
    DBManager dbManager;
    UserModel userModel;
    SharedPreferences sharedPreferences;
    int userID;
    public ProfileFragment(String userName,String email,String phoneNumber){
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tvUsername = view.findViewById(R.id.tv_username);
        tvPhoneNumber = view.findViewById(R.id.tv_phonenumber);
        tvEmail = view.findViewById(R.id.tv_emails);
        btnLogOut = view.findViewById(R.id.btn_logout);
        tvAboutUs = view.findViewById(R.id.tv_aboutus);
        dbManager = new DBManager(getActivity());
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.savedKey), MODE_PRIVATE);
        userID = sharedPreferences.getInt("userID", 0);
        userModel = dbManager.gettingUserDataByID(userID);
        tvUsername.setText(userModel.getUserName());
        tvPhoneNumber.setText(userModel.getUserPhoneNumber());
        tvEmail.setText(userModel.getUserEmail());
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setMessage("Are you sure you want to Log out ?");
                alertDialog.setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                sharedPreferences.edit().clear().commit();
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
        tvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

}