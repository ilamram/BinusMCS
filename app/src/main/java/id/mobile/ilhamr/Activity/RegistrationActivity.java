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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.mobile.ilhamr.DBManager;
import id.mobile.ilhamr.Model.UserModel;
import id.mobile.ilhamr.R;

public class RegistrationActivity extends AppCompatActivity {
    JsonObjectRequest jsonArrayRequest;
    Button btnRegister;
    TextView tvRegister;
    EditText etUsername, etPassword, etEmail, etPhoneNumber;
    DBManager dbManager;
    RequestQueue queue;

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
        dbManager = new DBManager(RegistrationActivity.this);
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
        if (username.equals("") || username.length() == 0) {
            Toast.makeText(this, "Please fill the Username!", Toast.LENGTH_SHORT).show();
        }else if(password.equals("") || password.length() == 0){
            Toast.makeText(this, "Please fill the Password!", Toast.LENGTH_SHORT).show();
        }else if(email.equals("") || email.length() == 0){
            Toast.makeText(this, "Please fill the Email!", Toast.LENGTH_SHORT).show();
        }else if(phoneNumber.equals("") || phoneNumber.length() == 0){
            Toast.makeText(this, "Please fill the Phone Number!", Toast.LENGTH_SHORT).show();
        }else if(!(password.length() > 5)){
            Toast.makeText(this, "Password must be longer than 5 words", Toast.LENGTH_SHORT).show();
        }else if(!email.contains(".com")){
            Toast.makeText(this, "Invalid email input!", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            dbManager.registerUser(username, password, email, phoneNumber);
            startActivity(intent);
        }
    }


}