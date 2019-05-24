package com.example.fab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText username, password;
    Button login, register;
    SharedPreferences preferences;
    CheckBox rememberme;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.lusername);
        password = findViewById(R.id.lpassword);
        login = findViewById(R.id.llogin);
        register = findViewById(R.id.lregister);
        rememberme = findViewById(R.id.rememberme);
        preferences = getSharedPreferences("UserInfo", 0);

        databaseHelper = new DatabaseHelper(this);

        if (preferences.getBoolean("rememberme", false)) {
            startActivity(new Intent(LoginActivity.this, FbDesign.class));
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameVal = username.getText().toString().trim();
                String passwordVal = password.getText().toString().trim();

//                Toast.makeText(LoginActivity.this, "username:"+usernameVal+" password: "+passwordVal, Toast.LENGTH_LONG).show();
//                Log.i("this","username:"+usernameVal+" password: "+passwordVal);
//                String registeredUsername = preferences.getString("username", "");
//                String registeredPass = preferences.getString("password", "");
                if (isfieldEmpty(username) && isfieldEmpty(password)) {
                    if (databaseHelper.isLoginSuccessful(usernameVal, passwordVal)) {
//                    if (usernameVal.equals(registeredUsername) && passwordVal.equals(registeredPass) && usernameVal.length() != 0 && passwordVal.length() != 0) {
                        Toast.makeText(LoginActivity.this, "Login Succeed", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, FbDesign.class));
                        finish();
                        if (rememberme.isChecked()) {
                            preferences.edit().putBoolean("rememberme", true).apply();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, UserListActivity.class));
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.exit:
                finishAffinity();
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isfieldEmpty(TextInputEditText view) {
        String value = view.getText().toString();
        if (value.length() > 0) {
            return true;
        } else {
            view.setError("Enter value");
            return false;
        }
    }

    public boolean isValidEmailAddress(TextInputEditText view) {
        if (isfieldEmpty(view)) {
            String value = view.getText().toString();
            if (Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
                return true;
            } else {
                view.setError("Invalid Email address");
                return false;
            }
        }
        return false;
    }
}
