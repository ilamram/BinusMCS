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

public class LoginActivity extends AppCompatActivity {

    TextView tvRegister;
    UserModel userModel;
    UserModel userModel1;
    Button btnLogin;
    String userName, password, email, phoneNumber;
    EditText etLogin, etPassword;
    ArrayList<UserModel> userModelString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        tvRegister = findViewById(R.id.tv_register);
        etLogin = findViewById(R.id.et_login);
        btnLogin = findViewById(R.id.btn_login);
        etPassword = findViewById(R.id.et_password);
        userModelString = new ArrayList<>();
        userModel1 = new UserModel();
        userModel1.setUserName("Bobby");
        userModel1.setUserEmail("bobbyryan692@gmail.com");
        userModel1.setUserPassword("123456");
        userModel1.setUserPhoneNumber("085921150899");
        userModelString.add(userModel1);
        if(getIntent().getExtras() != null) {
                Toast.makeText(this, "Data has been added, UwU", Toast.LENGTH_SHORT).show();
                userName = getIntent().getExtras().getString("UserName");
                password = getIntent().getExtras().getString("Password");
                email = getIntent().getExtras().getString("Email");
                phoneNumber = getIntent().getExtras().getString("PhoneNumber");
                userModelString = new ArrayList<>();
                userModel = new UserModel();
                userModel.setUserName(userName);
                userModel.setUserEmail(email);
                userModel.setUserPassword(password);
                userModel.setUserPhoneNumber(phoneNumber);
                userModelString.add(userModel);
                Log.e("TAG", "onCreate: " + userModelString.size());
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for(int i = 0; i < userModelString.size() ; i++){
                            if(etLogin.getText().toString().equals(userModelString.get(i).getUserName()) && etPassword.getText().toString().equals(userModelString.get(i).getUserPassword())){
                                Toast.makeText(LoginActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, UtamaActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        }else{
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int i = 0; i < userModelString.size() ; i++){
                        if(etLogin.getText().toString().equals(userModelString.get(i).getUserName()) && etPassword.getText().toString().equals(userModelString.get(i).getUserPassword())){
                            Toast.makeText(LoginActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, UtamaActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            Toast.makeText(this, "Welcome to our Page, UwU", Toast.LENGTH_SHORT).show();
        }
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("TAG", "onResume: " + userModelString.size() );
        userModelString.add(userModel1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("TAG", "onPause: " + userModelString.size() );
//        userModelString.add(userModel1);
    }
}