package com.example.colliensepodder.foundlost.Activity;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colliensepodder.foundlost.Class.DatePickerFragment;
import com.example.colliensepodder.foundlost.Database.MyDatabase;
import com.example.colliensepodder.foundlost.Model.Data;
import com.example.colliensepodder.foundlost.R;

import java.text.DateFormat;
import java.util.Calendar;

import static com.example.colliensepodder.foundlost.Activity.AdminLogin.LOGGEDIN_OWNER_PHONE;

public class AdminAdding extends AppCompatActivity{

    public EditText editText_pickerDate;
    public EditText editText_date;
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
        setContentView(R.layout.activity_admin_adding);

        editText_yourdepartmentname = findViewById(R.id.editText_yourdepartmentname);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextId = findViewById(R.id.editTextId);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextDescription = findViewById(R.id.editTextDescription);
        //editText_pickerDate = findViewById(R.id.editText_pickerDate);
        editText_date = findViewById(R.id.editText_date);
        button_add = findViewById(R.id.button_add);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        //editTextPhoneNumber.setText(LOGGEDIN_OWNER_PHONE);


        /*editText_pickerDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });*/

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = new Data();
                data.setDatePick(editText_date.getText().toString());
                if (editText_date.getText().toString().isEmpty()){
                    editText_date.setError("Enter Date");
                    return;
                }
                if (editText_yourdepartmentname.getText().toString().isEmpty()) {
                    editText_yourdepartmentname.setError("Enter department name");
                    return;
                }
                data.setDepartmentName(editText_yourdepartmentname.getText().toString());

                //data.setPhoneNumber(LOGGEDIN_OWNER_PHONE);
                data.setYourId(editTextId.getText().toString());
                if (editTextId.getText().toString().isEmpty()) {
                    editTextId.setError("Enter Id");
                    return;
                }
                if (editTextEmail.getText().toString().contains("@gmail.com") || editTextEmail.getText().toString().contains("@diu.edu.bd")
                        || editTextEmail.getText().toString().contains("@yahoo.com")) {

                } else {
                    editTextEmail.setError("Invalid email");
                }
                data.setEmail(editTextEmail.getText().toString());
                if (editTextAddress.getText().toString().isEmpty()) {
                    editTextAddress.setError("Enter address");
                    return;
                }
                data.setAddress(editTextAddress.getText().toString());
                if (editTextDescription.getText().toString().isEmpty()) {
                    editTextDescription.setError("Enter description");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                data.setDescription(editTextDescription.getText().toString());

                MyDatabase myDatabase = new MyDatabase();
                myDatabase.dataAdd(AdminAdding.this, data, new MyDatabase.AdminDataAdd() {
                    @Override
                    public void isDataAdd(Boolean IsSignIn) {
                        if (IsSignIn == true) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

            }
        });

    }
/*
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayofmonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        editText_date.setText(currentDateString);

    }
    */
}
