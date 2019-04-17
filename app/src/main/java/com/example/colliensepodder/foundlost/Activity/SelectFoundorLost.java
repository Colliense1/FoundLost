package com.example.colliensepodder.foundlost.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.colliensepodder.foundlost.R;

public class SelectFoundorLost extends AppCompatActivity {

    Button btnlost;
    Button btnfound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_foundor_lost);

        btnlost = findViewById(R.id.btnlost);
        btnfound = findViewById(R.id.btnfound);

        btnlost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectFoundorLost.this,UserShowing.class));

            }
        });

        btnfound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectFoundorLost.this,UserShowing.class));

            }
        });

    }
}
