package com.example.colliensepodder.foundlost.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.colliensepodder.foundlost.Adapter.LostUserShowingAdapter;
import com.example.colliensepodder.foundlost.Adapter.UserShowingAdapter;
import com.example.colliensepodder.foundlost.Database.MyDatabase;
import com.example.colliensepodder.foundlost.Model.Data;
import com.example.colliensepodder.foundlost.Model.LostData;
import com.example.colliensepodder.foundlost.R;

import java.util.ArrayList;

public class UserShowing extends AppCompatActivity {

    public RecyclerView recyclerview_showData;
    public RecyclerView recyclerview_showlostData;


    ArrayList<Data> data = new ArrayList<>();
    ArrayList<LostData> lostData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_showing);

        recyclerview_showData = findViewById(R.id.recyclerview_showData);
        recyclerview_showlostData = findViewById(R.id.recyclerview_showlostData);
        recyclerview_showData.setLayoutManager(new LinearLayoutManager(this));
        recyclerview_showlostData.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        data.clear();
        lostData.clear();
        setAllData();
        setAlllostData();

    }

    private void setAllData() {
        MyDatabase myDatabase = new MyDatabase();
        myDatabase.allData(this, new MyDatabase.AdminAllData() {
            @Override
            public void getAllData(ArrayList<Data> data) {
                recyclerview_showData.setAdapter(new UserShowingAdapter(data));
            }
        });

    }

    private void setAlllostData() {
        MyDatabase myDatabase = new MyDatabase();
        myDatabase.lostallData(this, new MyDatabase.LostAllData() {
            @Override
            public void getLostAllData(ArrayList<LostData> lostData) {
                recyclerview_showlostData.setAdapter(new LostUserShowingAdapter(lostData));
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
