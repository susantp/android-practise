package com.example.fab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login, register;
    SharedPreferences preferences;
    CheckBox rememberme;


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
        if(preferences.getBoolean("rememberme",false)){
            startActivity(new Intent(LoginActivity.this, FbDesign.class));
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameVal = username.getText().toString();
                String passwordVal = password.getText().toString();

                String registeredUser = preferences.getString("name","");
                String registeredPass = preferences.getString("password", "");
                if(usernameVal.equals(registeredUser) && passwordVal.equals(registeredPass) && usernameVal.length()!=0 && passwordVal.length() !=0  ){
                    Toast.makeText(LoginActivity.this, "Login Succeed", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this,FbDesign.class));
                    finish();
                    if(rememberme.isChecked()){
                        preferences.edit().putBoolean("rememberme",true).apply();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
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
        switch (id){
            case R.id.exit:
                finishAffinity();
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
