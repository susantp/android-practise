package com.example.fab;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login, cancel;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.lusername);
        password = findViewById(R.id.lpassword);
        login = findViewById(R.id.llogin);
        cancel = findViewById(R.id.lcancel);
        preferences = getSharedPreferences("UserInfo", 0);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameVal = username.getText().toString();
                String passwordVal = password.getText().toString();
                Toast.makeText(LoginActivity.this, "Username: "+usernameVal, Toast.LENGTH_LONG).show();
            }
        });
    }
}
