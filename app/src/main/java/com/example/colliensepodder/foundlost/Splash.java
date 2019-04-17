package com.example.colliensepodder.foundlost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.colliensepodder.foundlost.Activity.FirstActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    LinearLayout linearlayout_Splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        linearlayout_Splash = findViewById(R.id.linearlayout_Splash);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), FirstActivity.class));
            }
        },4000);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        linearlayout_Splash.startAnimation(myanim);
    }
}
