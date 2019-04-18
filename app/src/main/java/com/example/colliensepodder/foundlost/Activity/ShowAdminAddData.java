package com.example.colliensepodder.foundlost.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.colliensepodder.foundlost.Adapter.AdminAddDataDetailsAdapter;
import com.example.colliensepodder.foundlost.Database.MyDatabase;
import com.example.colliensepodder.foundlost.Model.Data;
import com.example.colliensepodder.foundlost.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.colliensepodder.foundlost.Activity.AdminLogin.LOGGEDIN_OWNER_PHONE;

public class ShowAdminAddData extends AppCompatActivity {

    ArrayList<Data> data;
    RecyclerView recyclerView_AdminAddData;
    ImageView imageView_back;
    DatabaseReference databaseReference;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_admin_add_data);

        imageView_back = findViewById(R.id.imageView_back);
        recyclerView_AdminAddData = findViewById(R.id.recyclerView_AdminAddData);
        recyclerView_AdminAddData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        searchView = findViewById(R.id.searchView);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("data");
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
        setMyData();

    }

    private void setMyData() {
        final ArrayList<Data> myData = new ArrayList<>();
        MyDatabase myDatabase = new MyDatabase();
        myDatabase.allData(this, new MyDatabase.AdminAllData() {
            @Override
            public void getAllData(ArrayList<Data> data) {
                for (int i = 0; i < data.size(); i++) {
                    //if (data.get(i).getPhoneNumber().equals(LOGGEDIN_OWNER_PHONE)) {
                    myData.add(data.get(i));
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
}
