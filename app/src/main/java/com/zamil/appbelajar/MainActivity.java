package com.zamil.appbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView register;
    Button button;
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Login");

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
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
                    Toast.makeText(MainActivity.this, "Username empty", Toast.LENGTH_SHORT).show();
                } else if (passwordText.equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "Password empty", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Success Login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
