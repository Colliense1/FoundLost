package com.example.colliensepodder.foundlost.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.colliensepodder.foundlost.R;

public class FirstActivity extends AppCompatActivity {

    public Button btnuser;
    public Button btnadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btnuser = findViewById(R.id.btnuser);
        btnadmin = findViewById(R.id.btnadmin);

        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FirstActivity.this, UserLogin.class);
                startActivity(i);

            }
        });
        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FirstActivity.this, AdminLogin.class);
                startActivity(i);

            }
        });
    }

}
