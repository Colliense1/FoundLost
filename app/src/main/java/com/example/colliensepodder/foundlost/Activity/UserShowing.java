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

import static com.example.colliensepodder.foundlost.Activity.AdminLogin.LOGGEDIN_OWNER_PHONE;

public class UserShowing extends AppCompatActivity {

    public RecyclerView recyclerview_showData;
    public RecyclerView recyclerview_showlostData;

    ArrayList<Data> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_showing);

        recyclerview_showData = findViewById(R.id.recyclerview_showData);
        //recyclerview_showlostData = findViewById(R.id.recyclerview_showlostData);
        recyclerview_showData.setLayoutManager(new LinearLayoutManager(this));
        //recyclerview_showlostData.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        data.clear();
        setMyData();
    }

    private void setMyData() {
       // final ArrayList<Data> myData = new ArrayList<>();
        MyDatabase db = new MyDatabase();
        db.getAllData(this, new MyDatabase.AllData() {
            @Override
            public void getAllData(ArrayList<Data> data) {
               //for (int i = 0; i < data.size(); i++) {
                   // if (data.get(i).getPhoneNumber().equals(LOGGEDIN_OWNER_PHONE)) {
                       // myData.add(data.get(i));
                    //
                   //}

                //}

                recyclerview_showData.setAdapter(new UserShowingAdapter(data));

            }
        });
    }

   /* private void setAlllostData() {
        MyDatabase myDatabase = new MyDatabase();
        myDatabase.lostallData(this, new MyDatabase.LostAllData() {
            @Override
            public void getLostAllData(ArrayList<LostData> lostData) {
                recyclerview_showlostData.setAdapter(new LostUserShowingAdapter(lostData));
            }
        });

    }*/

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
