package com.example.colliensepodder.foundlost.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.colliensepodder.foundlost.Database.MyDatabase;
import com.example.colliensepodder.foundlost.Model.User;
import com.example.colliensepodder.foundlost.R;

public class UserSignUp extends AppCompatActivity {

    public ImageView imgview_ic_user_signup;
    public EditText editText_firstname;
    public EditText editText_lastname;
    public EditText editTextPhoneNumber;
    public EditText editTextEmail;
    public EditText editTextAddress;
    public EditText editTextPassword;
    public EditText editTextConfirmPassword;
    public Button button_signup;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        imgview_ic_user_signup = findViewById(R.id.imgview_ic_user_signup);
        editText_firstname = findViewById(R.id.editText_firstname);
        editText_lastname = findViewById(R.id.editText_lastname);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        button_signup = findViewById(R.id.button_signup);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText_firstname.getText().toString().isEmpty()) {
                    editText_firstname.setError("Enter first name");
                    return;
                }
                if (editText_lastname.getText().toString().isEmpty()) {
                    editText_lastname.setError("Enter last name");
                    return;
                }
                if (editTextPhoneNumber.getText().toString().isEmpty()) {
                    editTextPhoneNumber.setError("Enter phone number");
                    return;
                }
                if (editTextPhoneNumber.getText().toString().length() != 11) {
                    editTextPhoneNumber.setError("The Number is not Valid");
                    return;
                }
                if (!editTextPhoneNumber.getText().toString().substring(0, 2).equals("01")) {
                    editTextPhoneNumber.setError("The Number is not Valid");
                    return;
                }
                if (editTextEmail.getText().toString().isEmpty()) {
                    editTextEmail.setError("Enter email");
                    return;
                }
                if (editTextEmail.getText().toString().contains("@gmail.com") || editTextEmail.getText().toString().contains("@diu.edu.bd")
                        || editTextEmail.getText().toString().contains("@yahoo.com")) {

                } else {
                    editTextEmail.setError("Invalid email");
                }
                if (editTextAddress.getText().toString().isEmpty()) {
                    editTextAddress.setError("Enter address");
                    return;
                }
                if (editTextPassword.getText().toString().isEmpty()) {
                    editTextPassword.setError("Enter Password");
                    return;
                }
                if (editTextConfirmPassword.getText().toString().isEmpty()) {
                    editTextConfirmPassword.setError("Enter confirm Password");
                    return;
                }
                if (editTextPassword.length() < 8) {
                    editTextPassword.setError("Password length minimum 8 character");
                    return;
                }
                if (!editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())) {
                    editTextConfirmPassword.setError("Password Not Match");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

               User user = new User(
                        editText_firstname.getText().toString()
                        , editText_lastname.getText().toString(),
                        editTextPhoneNumber.getText().toString(),
                        editTextEmail.getText().toString(),
                        editTextAddress.getText().toString(),
                        editTextPassword.getText().toString());

                MyDatabase myDatabase = new MyDatabase();
                myDatabase.userSignUp(UserSignUp.this, user, new MyDatabase.UserSignup() {
                    @Override
                    public void issignup(Boolean IsSignUp) {

                        Toast.makeText(getApplicationContext(), "Signup Succesfully", Toast.LENGTH_SHORT).show();

                        progressBar.setVisibility(View.GONE);

                        startActivity(new Intent(UserSignUp.this, UserLogin.class));
                    }
                });

            }
        });
    }
}
