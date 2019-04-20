package com.example.colliensepodder.foundlost.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.colliensepodder.foundlost.Adapter.AdminAddDataDetailsAdapter;
import com.example.colliensepodder.foundlost.Database.MyDatabase;
import com.example.colliensepodder.foundlost.Model.Data;
import com.example.colliensepodder.foundlost.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.colliensepodder.foundlost.Activity.AdminLogin.LOGGEDIN_OWNER_PHONE;

public class ShowAdminAddData extends AppCompatActivity {

    public static ArrayList<Data> data;
    public  static AdminAddDataDetailsAdapter adminAddDataDetailsAdapter;
    RecyclerView recyclerView_AdminAddData;
    ImageView imageView_back;
    public static  DatabaseReference databaseReference;
    SearchView searchView;
    Button email_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_admin_add_data);

        imageView_back = findViewById(R.id.imageView_back);
        recyclerView_AdminAddData = findViewById(R.id.recyclerView_AdminAddData);
        recyclerView_AdminAddData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        searchView = findViewById(R.id.searchView);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("data");

        getData();
        email_button = findViewById(R.id.email_button);

        email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                String[] s = {""};
                email.putExtra(Intent.EXTRA_EMAIL, s);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Send Email"));
                startActivity(email);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (databaseReference != null) {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        data = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            data.add(ds.getValue(Data.class));

                        }
                        AdminAddDataDetailsAdapter adminAddDataDetailsAdapter = new AdminAddDataDetailsAdapter(data);
                        recyclerView_AdminAddData.setAdapter(adminAddDataDetailsAdapter);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ShowAdminAddData.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }

    private void search(String string) {
        ArrayList<Data> mydata1 = new ArrayList<>();
        for (Data object : data) {
            if (object.getAddress().toLowerCase().contains(string.toLowerCase())) {
                mydata1.add(object);
            }
        }
        AdminAddDataDetailsAdapter adminAddDataDetailsAdapter = new AdminAddDataDetailsAdapter(mydata1);
        recyclerView_AdminAddData.setAdapter(adminAddDataDetailsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAllData();
    }

    private void setAllData() {
        final ArrayList<Data>mydata = new ArrayList<>();
        MyDatabase db = new MyDatabase();
        db.getAllData(this, new MyDatabase.AllData() {
            @Override
            public void getAllData(ArrayList<Data> data) {

                for (int i = 0; i < data.size(); i++){
                    //if (data.get(i).getPhoneNumber().equals(LOGGEDIN_OWNER_PHONE)) {
                        mydata.add(data.get(i));
                   // }

                }
                recyclerView_AdminAddData.setAdapter(new AdminAddDataDetailsAdapter(data));
            }
        });

    }

    public void addNewData(View view) {
        startActivity(new Intent(this, AdminAdding.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void clickBack(View view) {
        this.finish();
    }

    public static  void  getData(){
        databaseReference.child("data").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Data data1 = dataSnapshot.getValue(Data.class);
                data.add(data1);
                adminAddDataDetailsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adminAddDataDetailsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
