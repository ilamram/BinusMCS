package id.mobile.ilhamr.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.mobile.ilhamr.R;


public class ProfileFragment extends Fragment {

    TextView tvUsername, tvPhoneNumber, tvEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tvUsername = view.findViewById(R.id.tv_username);
        tvPhoneNumber = view.findViewById(R.id.tv_phonenumber);
        tvEmail = view.findViewById(R.id.tv_emails);


        return view;
    }
}