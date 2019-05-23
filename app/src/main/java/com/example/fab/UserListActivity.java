package com.example.fab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.w3c.dom.Text;

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
        populateData();
    }

    public void populateData(){
        ArrayList<UserInfo> list = databaseHelper.getUserList();
        Log.d("UserList ======> ","ListSize : "+list.size());

        for(final UserInfo info:list) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
            TextView name = view.findViewById(R.id.name);
            TextView address = view.findViewById(R.id.address);
            name.setText(info.getUsername());
            address.setText(info.getAddress());
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
            container.addView(view);
        }
    }
}
