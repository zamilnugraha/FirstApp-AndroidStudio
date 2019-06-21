package com.zamil.appbelajar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zamil.appbelajar.helper.InitRetrofit;
import com.zamil.appbelajar.R;
import com.zamil.appbelajar.model.ResponseRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button  button;
    EditText editTextEmail;
    EditText editTextUsername;
    EditText editTextPassword;
    EditText editTextRetypePassword;
    EditText editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextEmail = findViewById(R.id.email);
        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        editTextRetypePassword = findViewById(R.id.retypePassword);
        editTextPhone = findViewById(R.id.phone);
        button = findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailValidation = editTextEmail.getText().toString();
                String userNameValidation = editTextUsername.getText().toString();
                String passwordValidation = editTextPassword.getText().toString();
                String retypePasswordValidation = editTextRetypePassword.getText().toString();
                String phoneValidation = editTextPhone.getText().toString();

                if(emailValidation.equalsIgnoreCase("")){
                    Toast.makeText(RegisterActivity.this, "Email Empty", Toast.LENGTH_SHORT).show();
                } else if (userNameValidation.equalsIgnoreCase("")){
                    Toast.makeText(RegisterActivity.this, "Username Empty", Toast.LENGTH_SHORT).show();
                } else if (passwordValidation.equalsIgnoreCase("")){
                    Toast.makeText(RegisterActivity.this, "Password Empty", Toast.LENGTH_SHORT).show();
                } else if (retypePasswordValidation.equalsIgnoreCase("")){
                    Toast.makeText(RegisterActivity.this, "Retype Password Empty", Toast.LENGTH_SHORT).show();
                } else if (phoneValidation.equalsIgnoreCase("")){
                    Toast.makeText(RegisterActivity.this, "Phone Empty", Toast.LENGTH_SHORT).show();
                } else {

                    InitRetrofit.getInstance().registeruser(emailValidation, userNameValidation, passwordValidation, phoneValidation).enqueue(new Callback<ResponseRegister>() {
                        @Override
                        public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                            if(response.isSuccessful()){
                                String status = response.body().getStatus();

                                if(status.equalsIgnoreCase("success")){
                                    Toast.makeText(RegisterActivity.this, "Success Registrasi", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Gagal Registrasi", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseRegister> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Lost Connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
