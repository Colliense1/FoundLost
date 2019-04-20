package com.example.colliensepodder.foundlost.Database;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.colliensepodder.foundlost.Adapter.AdminAddDataDetailsAdapter;
import com.example.colliensepodder.foundlost.Model.Admin;
import com.example.colliensepodder.foundlost.Model.Data;
import com.example.colliensepodder.foundlost.Model.LostData;
import com.example.colliensepodder.foundlost.Model.User;
import com.example.colliensepodder.foundlost.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference userRef = databaseReference.child("user");
    DatabaseReference adminRef = databaseReference.child("admin");
    DatabaseReference dataRef = databaseReference.child("data");

    //User Login & SignUp
    public interface UserSignup {
        public void issignup(Boolean IsSignUp);
    }

    public interface UserSignin {
        public void issignin(Boolean IsSignIn);
    }

    public void userSignUp(Context context, final User user, final UserSignup userSignup) {
        userRef.push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                userSignup.issignup(true);
            }
        });
    }

    public void userSignIn(Context context, final User user, final UserSignin userSignin) {
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean flag = false;
                for (DataSnapshot dsnp : dataSnapshot.getChildren()) {
                    User dbUser = dsnp.getValue(User.class);
                    if (dbUser.getPhoneNumber().toString().equals(user.getPhoneNumber())
                            && dbUser.getPassword().toString().equals(user.getPassword())) {
                        flag = true;
                    }
                }

                userSignin.issignin(flag);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Admin Login & SignUp
    public interface AdminSignup {
        public void issignup(Boolean IsSignUp);
    }

    public interface AdminSignin {
        public void issignin(Boolean IsSignIn);
    }

    public void adminSignUp(Context context, final Admin admin, final AdminSignup adminSignup) {
        adminRef.push().setValue(admin).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                adminSignup.issignup(true);
            }
        });
    }

    public void adminSignIn(Context context, final Admin admin, final AdminSignin adminSignin) {
        adminRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean flag = false;
                for (DataSnapshot dsnp : dataSnapshot.getChildren()) {
                    Admin dbAdmin = dsnp.getValue(Admin.class);
                    if (dbAdmin.getPhoneNumber().toString().equals(admin.getPhoneNumber())
                            && dbAdmin.getPassword().toString().equals(admin.getPassword())) {
                        flag = true;
                    }
                }

                adminSignin.issignin(flag);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Add Data
    public interface AdminDataAdd {
        public void isDataAdd(Boolean IsSignIn);
    }

    public interface AllData {
        public void getAllData(ArrayList<Data> data);
    }

    public void dataAdd(Context context, final Data data, final AdminDataAdd adminDataAdd) {
        dataRef.push().setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                adminDataAdd.isDataAdd(true);
            }
        });
    }

    public void getAllData(Context context, final AllData allData) {
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean flag = false;
                ArrayList<Data> data = new ArrayList<>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    data.add(dsp.getValue(Data.class));
                }
                allData.getAllData(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
