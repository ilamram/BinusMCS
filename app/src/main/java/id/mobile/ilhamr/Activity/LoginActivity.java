package id.mobile.ilhamr.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
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

public class LoginActivity extends AppCompatActivity {

    TextView tvRegister;
    Button btnLogin;
    EditText etLogin, etPassword;
    ArrayList<UserModel> userModelString;
    SharedPreferences sharedPreferences;
    DBManager dbManager;
    SQLiteDatabase sqLiteDatabase;
    SharedPreferences.Editor editor;
    RequestQueue queue;
    JsonObjectRequest jsonArrayRequest;
    String url = "https://mocki.io/v1/ce4395c2-d593-45ae-b392-78fe2238369c";
    UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        tvRegister = findViewById(R.id.tv_register);
        etLogin = findViewById(R.id.et_login);
        btnLogin = findViewById(R.id.btn_login);
        etPassword = findViewById(R.id.et_password);
        dbManager = new DBManager(LoginActivity.this);
        sharedPreferences = getSharedPreferences(getString(R.string.savedKey), MODE_PRIVATE);
        dbManager = new DBManager(LoginActivity.this);
        sqLiteDatabase = dbManager.getReadableDatabase();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validasi()){
                    jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("films");
                                for(int i = 0 ; i < jsonArray.length() ; i++){
                                    JSONObject jsonFilmObject = jsonArray.getJSONObject(i);
                                    if(!dbManager.checkedValueList(jsonFilmObject.optString("title"))) {
                                        dbManager.saveMovie(jsonFilmObject.optString("title"),
                                                jsonFilmObject.optString("price"),
                                                jsonFilmObject.optString("rating"),
                                                jsonFilmObject.optString("country"),
                                                jsonFilmObject.optString("description"),
                                                jsonFilmObject.optString("image"));
                                    }
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(jsonArrayRequest);
                    userModel = dbManager.gettinguserID(etLogin.getText().toString(), etPassword.getText().toString());
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(LoginActivity.this, UtamaActivity.class);
                            editor = sharedPreferences.edit();
                            editor.putInt("userID", userModel.getId());
                            editor.apply();
                            startActivity(intent);
                        }
                    }, 3000);
                }
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
    //validasi ketika user input login information mereka
    private boolean validasi() {
        if (etLogin.getText().equals("") || etLogin.length() == 0) {
            Toast.makeText(this, "Username must be filled", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPassword.getText().equals("") || etPassword.length() == 0) {
            Toast.makeText(this, "Password must be filled", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!dbManager.checkUsername(etLogin.getText().toString(), etPassword.getText().toString())) {
            Toast.makeText(this, "Please check your password and username", Toast.LENGTH_SHORT).show();
            return false;
        }
            return true;
        }

    }