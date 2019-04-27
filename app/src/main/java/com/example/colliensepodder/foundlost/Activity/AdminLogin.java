package com.example.colliensepodder.foundlost.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colliensepodder.foundlost.Database.MyDatabase;
import com.example.colliensepodder.foundlost.Model.Admin;
import com.example.colliensepodder.foundlost.R;
import com.google.firebase.database.core.utilities.Validation;

public class AdminLogin extends AppCompatActivity {

    public static String LOGGEDIN_OWNER_PHONE;
    public ImageView imgview_ic_owner_login;
    public EditText editText_firstname;
    public EditText editText_lastname;
    public EditText editTextPhoneNumber;
    public EditText editTextPassword;
    public Button button_signin;
    public TextView textView_signup;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        imgview_ic_owner_login = findViewById(R.id.imgview_ic_owner_login);
        editText_firstname = findViewById(R.id.editText_firstname);
        editText_lastname = findViewById(R.id.editText_lastname);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextPassword = findViewById(R.id.editTextPassword);
        button_signin = findViewById(R.id.button_signin);
        textView_signup = findViewById(R.id.textView_signup);
        //progressBar = findViewById(R.id.progressBar);
        //progressBar.setVisibility(View.GONE);

        final ProgressDialog progressDialog = new ProgressDialog(AdminLogin.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...!");

        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (editTextPhoneNumber.getText().toString().isEmpty()) {
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
                */
                //Validation(editTextPhoneNumber.getText().toString(), editTextPassword.getText().toString());
                //progressBar.setVisibility(View.VISIBLE);

                progressDialog.show();
                final Admin admin = new Admin();
                admin.setPhoneNumber(editTextPhoneNumber.getText().toString());
                admin.setPassword(editTextPassword.getText().toString());
                MyDatabase myDatabase = new MyDatabase();
                myDatabase.adminSignIn(AdminLogin.this, admin, new MyDatabase.AdminSignin() {
                    @Override
                    public void issignin(Boolean IsSignIn) {
                        if (IsSignIn == true) {
                            Toast.makeText(getApplicationContext(), "Login succesfull", Toast.LENGTH_SHORT).show();
                            LOGGEDIN_OWNER_PHONE = admin.getPhoneNumber().toString();
                            //progressBar.setVisibility(View.GONE);
                            progressDialog.dismiss();
                            startActivity(new Intent(AdminLogin.this, ShowAdminAddData.class));
                        } else {
                            //progressBar.setVisibility(View.GONE);
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Wrong Phonenumber or Password", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

        /*textView_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminLogin.this, AdminSignUp.class);
                startActivity(i);
            }
        });*/
    }

    private void Validation (String Phone, String UserPassword){
        if ((Phone == "01852075058") && (UserPassword == "12345678")){
            Toast.makeText(getApplicationContext(), "Login succesfull", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            startActivity(new Intent(AdminLogin.this, ShowAdminAddData.class));
        }else{
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Wrong phonenumber or password", Toast.LENGTH_SHORT).show();

        }
    }
}
