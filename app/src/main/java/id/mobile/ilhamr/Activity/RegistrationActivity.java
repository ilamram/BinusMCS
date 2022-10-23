package id.mobile.ilhamr.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import id.mobile.ilhamr.Model.UserModel;
import id.mobile.ilhamr.R;

public class RegistrationActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etUsername, etPassword, etEmail, etPhoneNumber;
    ArrayList<UserModel> userModels = new ArrayList<>();
    ArrayList<String> userString = new ArrayList<>();
    UserModel userModel;
    public ArrayList[] username = new ArrayList[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        btnRegister = findViewById(R.id.btn_register);
        etUsername = findViewById(R.id.et_regis_username);
        etPassword = findViewById(R.id.et_regis_password);
        etEmail = findViewById(R.id.et_regis_email);
        etPhoneNumber = findViewById(R.id.et_regis_phone_number);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasi(etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), etPhoneNumber.getText().toString());
            }
        });


    }

    private void validasi(String username, String password, String email, String phoneNumber) {
        if(username.equals("") || password.equals("") || email.equals("") || phoneNumber.equals("")) {
            Toast.makeText(this, "Please fill out the form!", Toast.LENGTH_SHORT).show();
        }else if(!email.contains(".com")){
            Toast.makeText(this, "Invalid email input!", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            intent.putExtra("From","Registration");
            intent.putExtra("UserName",username);
            intent.putExtra("Password",password);
            intent.putExtra("Email",email);
            intent.putExtra("PhoneNumber",phoneNumber);
            startActivity(intent);
        }
    }


}