package com.example.fab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class FbDesign extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_design);
        preferences = getSharedPreferences("UserInfo", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.menu1:
                Toast.makeText(this, "You've clicked Menu 1", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu2:
                Toast.makeText(this, "You've clicked Menu 2", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu3:
                Toast.makeText(this, "You've clicked Menu 3", Toast.LENGTH_LONG).show();
                break;
            case R.id.logout:
                preferences.edit().putBoolean("rememberme", false).apply();
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                Toast.makeText(this, "Logout Succeed", Toast.LENGTH_LONG).show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
