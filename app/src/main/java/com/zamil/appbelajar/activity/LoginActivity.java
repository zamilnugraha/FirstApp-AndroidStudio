package com.zamil.appbelajar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zamil.appbelajar.helper.InitRetrofit;
import com.zamil.appbelajar.R;
import com.zamil.appbelajar.helper.SharedPrefManager;
import com.zamil.appbelajar.model.DataLogin;
import com.zamil.appbelajar.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView register;
    Button button;
    EditText userName;
    EditText password;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefManager = new SharedPrefManager(this);
        setTitle("Login");

        if(sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        button = findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameText = userName.getText().toString();
                String passwordText = password.getText().toString();

                if (userNameText.equalsIgnoreCase("")){
                    Toast.makeText(LoginActivity.this, "Username empty", Toast.LENGTH_SHORT).show();
                } else if (passwordText.equalsIgnoreCase("")){
                    Toast.makeText(LoginActivity.this, "Password empty", Toast.LENGTH_SHORT).show();
                } else {

                    InitRetrofit.getInstance().loginuser(userNameText, passwordText).enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            if(response.isSuccessful()){
                                String status = response.body().getStatus();

                                if(status.equalsIgnoreCase("success")){
                                    DataLogin dataLogin = response.body().getData();
                                    String token = dataLogin.getToken();
                                    String userId = dataLogin.getUserId();
                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, token);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_USERID, userId);
                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                                    Toast.makeText(LoginActivity.this, "Success Login", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Lost Connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
