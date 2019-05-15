package com.example.fab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //    private static final int pic_id = 123;
    //    private static ImageView imageView;
    EditText name, email, address, password;
    RadioGroup gender;
    Button submit, cancel;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_design);

        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        gender = findViewById(R.id.gender);
        submit = findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel);
    }

    public void cancelClick(View view){
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void submitClick(View view) {
        String nameVal = name.getText().toString();
        String emailVal = email.getText().toString();
        String addressVal = address.getText().toString();
        String passwordVal = password.getText().toString();
        RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());
        String genderVal = checkedBtn.getText().toString();

         SharedPreferences.Editor editor = preferences.edit();
         editor.putString("name",nameVal);
         editor.putString("email",emailVal);
         editor.putString("password",passwordVal);
         editor.putString("gender",genderVal);
         editor.putString("address", addressVal);
         editor.putString("gender", genderVal);
         editor.apply();
        Toast.makeText(this, "UserInfo Saved", Toast.LENGTH_LONG).show();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));

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
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
