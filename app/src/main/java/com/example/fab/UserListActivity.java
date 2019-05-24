package com.example.fab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    LinearLayout container;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        databaseHelper = new DatabaseHelper(this);
        container = findViewById(R.id.container);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateData();
    }

    public void populateData() {
        ArrayList<UserInfo> list = databaseHelper.getUserList();
//

        container.removeAllViews();
        if (list.size() != 0) {
            for (final UserInfo info : list) {
                View view = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
                TextView name = view.findViewById(R.id.name);
                TextView address = view.findViewById(R.id.address);
                ImageView imageView = view.findViewById(R.id.image);
                name.setText(info.getUsername());
                address.setText(info.getAddress());
                if (info.getImage() != null)
                    imageView.setImageBitmap(GeneralUtil.getBitmap(info.getImage()));

//            TextView textView = new TextView(this);
//            LinearLayout.LayoutParams params = new
//                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.MATCH_PARENT);
//
//            textView.setText("UserName:" + info.getUsername());
//            textView.setLayoutParams(params);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UserListActivity.this, UserDetailActivity.class);
                        intent.putExtra("id", info.getId());
                        startActivity(intent);
                    }
                });
                view.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                    Toast.makeText(UserListActivity.this, "Edit", Toast.LENGTH_LONG ).show();
                        Intent intent = new Intent(UserListActivity.this, MainActivity.class);
                        intent.putExtra("id", Integer.parseInt(info.getId()));
                        startActivity(intent);
                    }
                });
                container.addView(view);
            }
        } else {
            View view = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
            TextView name = view.findViewById(R.id.name);
            TextView address = view.findViewById(R.id.address);
            Button edit = view.findViewById(R.id.edit);
            edit.setVisibility(View.GONE);
            address.setVisibility(View.GONE);

            name.setText("No user available");

            container.addView(view);
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
