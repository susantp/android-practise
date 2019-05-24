package com.example.fab;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    EditText username, email, address, password, phone;
    RadioGroup gender;
    Button submit, cancel;
    //    SharedPreferences preferences;
    DatabaseHelper databaseHelper;
    Bitmap bitmap;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_design);
        databaseHelper = new DatabaseHelper(this);

//        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        imageView = findViewById(R.id.image);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        gender = findViewById(R.id.gender);
        submit = findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel);

        id = getIntent().getIntExtra("id", 0);

        if (id != 0) {
            UserInfo info = databaseHelper.getUserInfo(id + "");
            username.setText(info.getUsername());
            email.setText(info.getEmail());
            address.setText(info.getAddress());
            phone.setText(info.getPhone());
//            gender.setText(info.getGender());
            if (info.getGender().equals("Male")) {
                ((RadioButton) findViewById(R.id.male)).setChecked(true);
            } else {
                ((RadioButton) findViewById(R.id.female)).setChecked(true);
            }
            submit.setText("Update");
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });
    }

    /**
     * Came from ImageView clicklistener, click image and hit this method with result, request code and intent data
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    public void cancelClick(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void submitClick(View view) {
        if (view.getId() == R.id.submit) {
            String usernameVal = username.getText().toString();
            String emailVal = email.getText().toString();
            String addressVal = address.getText().toString();
            String phoneVal = phone.getText().toString();
            String passwordVal = password.getText().toString();
            RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());
            String genderVal = checkedBtn.getText().toString();

//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("username", usernameVal);
//            editor.putString("email", emailVal);
//            editor.putString("password", passwordVal);
//            editor.putString("gender", genderVal);
//            editor.putString("address", addressVal);
//            editor.putString("phone", phoneVal);
//            editor.apply();

            ContentValues contentValues = new ContentValues();
            contentValues.put("username", usernameVal);
            contentValues.put("email", emailVal);
            contentValues.put("password", passwordVal);
            contentValues.put("gender", genderVal);
            contentValues.put("address", addressVal);
            contentValues.put("phone", phoneVal);
            contentValues.put("image", GeneralUtil.getblob(bitmap));
            if (id == 0) {
                databaseHelper.insertUser(contentValues);
            } else {
                databaseHelper.updateUser(String.valueOf(id), contentValues);
            }
            Toast.makeText(this, "UserInfo Saved", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
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
}
