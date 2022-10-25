package id.mobile.ilhamr.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import id.mobile.ilhamr.Activity.LoginActivity;
import id.mobile.ilhamr.Model.UserModel;
import id.mobile.ilhamr.R;


public class ProfileFragment extends Fragment {

    TextView tvUsername, tvPhoneNumber, tvEmail;
    ArrayList<UserModel> userModeArrayList;
    String userName, email, phoneNumber;
    UserModel userModel;
    Button btnLogOut;
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
        userModeArrayList = new ArrayList<>();
        userModel = new UserModel();
        Log.e("TAG", "onCreateView: test");
        tvUsername.setText(userName);
        tvPhoneNumber.setText(phoneNumber);
        tvEmail.setText(email);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}