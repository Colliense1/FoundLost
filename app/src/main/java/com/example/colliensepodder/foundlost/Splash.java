package com.example.colliensepodder.foundlost;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
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

        if (isconnected()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), FirstActivity.class));
                }
            }, 4000);

            Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
            linearlayout_Splash.startAnimation(myanim);
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_signal_wifi_off_24dp)
                    .setTitle("Oops!No Internet connection")
                    .setMessage("Please Check Your Internet Connection...!")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .show();
        }
    }

    private boolean isconnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
