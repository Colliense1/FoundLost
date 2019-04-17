package com.example.colliensepodder.foundlost.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colliensepodder.foundlost.Database.MyDatabase;
import com.example.colliensepodder.foundlost.Model.Data;
import com.example.colliensepodder.foundlost.Model.LostData;
import com.example.colliensepodder.foundlost.R;

import static com.example.colliensepodder.foundlost.Activity.AdminLogin.LOGGEDIN_OWNER_PHONE;

public class UserLostAdding extends AppCompatActivity {

    public EditText editText_yourdepartmentname;
    public TextView editTextPhoneNumber;
    public TextView editTextId;
    public EditText editTextEmail;
    public EditText editTextAddress;
    public EditText editTextDescription;
    public Button button_add;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_lost_adding);
        editText_yourdepartmentname = findViewById(R.id.editText_yourdepartmentname);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextId = findViewById(R.id.editTextId);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextDescription = findViewById(R.id.editTextDescription);
        button_add = findViewById(R.id.button_add);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        editTextPhoneNumber.setText(LOGGEDIN_OWNER_PHONE);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LostData lostData = new LostData();
                if (editText_yourdepartmentname.getText().toString().isEmpty()) {
                    editText_yourdepartmentname.setError("Enter department name");
                    return;
                }
                lostData.setDepartmentName(editText_yourdepartmentname.getText().toString());
                lostData.setPhoneNumber(LOGGEDIN_OWNER_PHONE);
                lostData.setYourId(editTextId.getText().toString());
                if (editTextId.getText().toString().isEmpty()){
                    editTextId.setError("Enter Id");
                    return;
                }
                if (editTextEmail.getText().toString().contains("@gmail.com") || editTextEmail.getText().toString().contains("@diu.edu.bd")
                        || editTextEmail.getText().toString().contains("@yahoo.com")) {

                } else {
                    editTextEmail.setError("Invalid email");
                }
                lostData.setEmail(editTextEmail.getText().toString());
                if (editTextAddress.getText().toString().isEmpty()) {
                    editTextAddress.setError("Enter address");
                    return;
                }
                lostData.setAddress(editTextAddress.getText().toString());
                if (editTextDescription.getText().toString().isEmpty()) {
                    editTextDescription.setError("Enter description");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                lostData.setDescription(editTextDescription.getText().toString());

                MyDatabase myDatabase = new MyDatabase();
                myDatabase.lostdataAdd(UserLostAdding.this, lostData, new MyDatabase.LostAddData() {
                    @Override
                    public void isLostDataAdd(Boolean IsSignIn) {
                        if(IsSignIn==true){
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

            }
        });

    }
}