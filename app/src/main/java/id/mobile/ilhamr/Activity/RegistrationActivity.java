package id.mobile.ilhamr.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.mobile.ilhamr.Model.UserModel;
import id.mobile.ilhamr.R;

public class RegistrationActivity extends AppCompatActivity {

    Button btnRegister;
    TextView tvRegister;
    EditText etUsername, etPassword, etEmail, etPhoneNumber;

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
        tvRegister = findViewById(R.id.tv_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasi(etUsername.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), etPhoneNumber.getText().toString());
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    private void validasi(String username, String password, String email, String phoneNumber) {
        if (username.equals("") || password.equals("") || email.equals("") || phoneNumber.equals("")) {
            Toast.makeText(this, "Please fill out the form!", Toast.LENGTH_SHORT).show();
        }else if(username.equals("Ilham")){
            Toast.makeText(this, "Username must be unique", Toast.LENGTH_SHORT).show();
        }else if(!(password.length() > 5)){
            Toast.makeText(this, "Password must be longer", Toast.LENGTH_SHORT).show();
        }else if(!email.contains(".com")){
            Toast.makeText(this, "Invalid email input!", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            intent.putExtra("UserName",username);
            intent.putExtra("Password",password);
            intent.putExtra("Email",email);
            intent.putExtra("PhoneNumber",phoneNumber);
            startActivity(intent);
        }
    }


}