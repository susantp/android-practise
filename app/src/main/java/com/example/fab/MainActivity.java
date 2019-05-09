package com.example.fab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_design);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        gender = findViewById(R.id.gender);
        submit = findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel);
    }

    public void submitClick(View view) {
        String nameVal = name.getText().toString();
        String emailVal = email.getText().toString();
        String addressVal = address.getText().toString();
        String passwordVal = password.getText().toString();
        RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());
        String genderVal = checkedBtn.getText().toString();
        Toast.makeText(this, "name: "+nameVal
                +"\nemail: "+emailVal
                +"\naddress: "+addressVal
                +"\npassword: "+passwordVal
                +"\ngender: "+genderVal
                , Toast.LENGTH_LONG).show();
    }
}