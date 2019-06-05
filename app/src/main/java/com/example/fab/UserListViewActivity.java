package com.example.fab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class UserListViewActivity extends AppCompatActivity {
    ListView listView;
    DatabaseHelper databaseHelper;
    UserListAdapter userListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_view);
        databaseHelper = new DatabaseHelper(this);
        userListAdapter = new UserListAdapter(this, databaseHelper.getUserList());
        listView = findViewById(R.id.listview);
        populateList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateList();
    }

    public void populateList(){
        listView.setAdapter(userListAdapter);
    }
}
