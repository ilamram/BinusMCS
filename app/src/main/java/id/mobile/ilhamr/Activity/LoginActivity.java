package id.mobile.ilhamr.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
    Button btnLogin;
    String userName, password, email, phoneNumber;
    EditText etLogin, etPassword;
    ArrayList<UserModel> userModelString;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        tvRegister = findViewById(R.id.tv_register);
        etLogin = findViewById(R.id.et_login);
        btnLogin = findViewById(R.id.btn_login);
        etPassword = findViewById(R.id.et_password);
        //Untuk kak Lucas, gua pakai shared preference sesuai dengan dokumentasi di https://developer.android.com/reference/android/content/SharedPreferences.
        //Karena setelah gua coba coba terus menerus. Android ga bisa simpen data deh waktu finish state atau yang gw maksud itu OnDestroy.
        sharedPreferences = getSharedPreferences(getString(R.string.savedKey), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(getIntent().getExtras() != null) {
                Toast.makeText(this, "Data has been added", Toast.LENGTH_SHORT).show();
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
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for(int i = 0; i < userModelString.size() ; i++){
                            if(etLogin.getText().toString().equals(userModelString.get(i).getUserName()) && etPassword.getText().toString().equals(userModelString.get(i).getUserPassword())){
                                Toast.makeText(LoginActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, UtamaActivity.class);
                                editor.putString("userName", userName);
                                editor.putString("email", email);
                                editor.putString("phoneNumber", phoneNumber);
                                editor.apply();
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        }else{
//            userModelString = new ArrayList<>();
//            userModel1 = new UserModel();
//            userModel1.setUserName("Ilham");
//            userModel1.setUserEmail("Ilham@gmail.com");
//            userModel1.setUserPassword("123456");
//            userModel1.setUserPhoneNumber("08254412250899");
//            userModelString.add(userModel1);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    for(int i = 0; i < userModelString.size() ; i++){
                        if(etLogin.getText().toString().equals("Ilham") && etPassword.getText().toString().equals("123456")){
                            Toast.makeText(LoginActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, UtamaActivity.class);
                            editor.putString("userName", "Ilham");
                            editor.putString("email", "Ilham@gmail.com");
                            editor.putString("phoneNumber", "08254412250899");
                            editor.apply();
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
//                    }
                }
            });
            Toast.makeText(this, "Welcome to our Page", Toast.LENGTH_SHORT).show();
        }
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }

}