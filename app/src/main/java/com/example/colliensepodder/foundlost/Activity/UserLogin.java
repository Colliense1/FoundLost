package com.example.colliensepodder.foundlost.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colliensepodder.foundlost.Database.MyDatabase;
import com.example.colliensepodder.foundlost.Model.User;
import com.example.colliensepodder.foundlost.R;

public class UserLogin extends AppCompatActivity {

    public ImageView imgview_ic_user_login;
    public EditText editTextPhoneNumber;
    public EditText editTextPassword;
    public Button button_signin;
    public TextView textView_signup;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        imgview_ic_user_login = findViewById(R.id.imgview_ic_user_login);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextPassword = findViewById(R.id.editTextPassword);
        button_signin = findViewById(R.id.button_signin);
        textView_signup = findViewById(R.id.textView_signup);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        final ProgressDialog progressDialog = new ProgressDialog(UserLogin.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...!");

        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextPhoneNumber.getText().toString().isEmpty()) {
                    editTextPhoneNumber.setError("invalid");
                    return;
                }
                if (editTextPassword.getText().toString().isEmpty()) {
                    editTextPassword.setError("Invalid");
                    return;
                }
                if (editTextPassword.length() < 8) {
                    editTextPassword.setError("Password length minimum 8 character");
                    return;
                }

                progressDialog.show();
                //progressBar.setVisibility(View.VISIBLE);
                User user = new User();
                user.setPhoneNumber(editTextPhoneNumber.getText().toString());
                user.setPassword(editTextPassword.getText().toString());
                MyDatabase myDatabase = new MyDatabase();
                myDatabase.userSignIn(UserLogin.this, user, new MyDatabase.UserSignin() {
                    @Override
                    public void issignin(Boolean IsSignIn) {
                        if (IsSignIn == true) {
                            Toast.makeText(getApplicationContext(), "Login succesfully", Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                            progressDialog.dismiss();
                            startActivity(new Intent(UserLogin.this, SelectFoundorLost.class));
                        } else {
                            //progressBar.setVisibility(View.GONE);
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

        textView_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserLogin.this, UserSignUp.class);
                startActivity(i);
            }
        });
    }
}
