package com.example.fab;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UserDetailActivity extends AppCompatActivity {

    String id;
    TextView username, password, email, address, phone, gender;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        id = getIntent().getStringExtra("id");
        databaseHelper = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        gender = findViewById(R.id.gender);

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetailActivity.this, MainActivity.class);
                intent.putExtra("id", Integer.parseInt(id));
                startActivity(intent);
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                databaseHelper.deleteUser(id);
                showAlertDialog();
//                startActivity(new Intent(UserDetailActivity.this, UserListActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateData();
    }

    public void populateData() {
        UserInfo info = databaseHelper.getUserInfo(id);
        username.setText(info.getUsername());
        email.setText(info.getEmail());
        address.setText(info.getAddress());
        phone.setText(info.getPhone());
        gender.setText(info.getGender());
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete User");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteUser(id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
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
