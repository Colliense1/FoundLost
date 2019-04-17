package com.example.colliensepodder.foundlost.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.colliensepodder.foundlost.Adapter.AdminAddDataDetailsAdapter;
import com.example.colliensepodder.foundlost.Database.MyDatabase;
import com.example.colliensepodder.foundlost.Model.Data;
import com.example.colliensepodder.foundlost.R;

import java.util.ArrayList;

import static com.example.colliensepodder.foundlost.Activity.AdminLogin.LOGGEDIN_OWNER_PHONE;

public class ShowAdminAddData extends AppCompatActivity {

    RecyclerView recyclerView_AdminAddData;
    ImageView imageView_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_admin_add_data);

        imageView_back = findViewById(R.id.imageView_back);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView_AdminAddData = findViewById(R.id.recyclerView_AdminAddData);
        recyclerView_AdminAddData.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        setMyData();

    }

    private void setMyData() {
        final ArrayList<Data> myData= new ArrayList<>();
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
        startActivity(new Intent(this,AdminAdding.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void clickBack(View view) {
        this.finish();
    }
}
