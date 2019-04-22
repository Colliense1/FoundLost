package com.example.colliensepodder.foundlost.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colliensepodder.foundlost.Activity.ShowAdminAddData;
import com.example.colliensepodder.foundlost.Database.MyDatabase;
import com.example.colliensepodder.foundlost.Model.Data;
import com.example.colliensepodder.foundlost.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminAddDataDetailsAdapter extends RecyclerView.Adapter<AdminAddDataDetailsAdapter.ViewHolder> {

    private ArrayList<Data> data;
    DatabaseReference databaseReference;

    public AdminAddDataDetailsAdapter(ArrayList<Data> data) {
        this.data = data;

        databaseReference = FirebaseDatabase.getInstance().getReference().child("data");
        databaseReference.addChildEventListener(new GetKey());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.admin_data_details_adapter, parent, false);
        return new ViewHolder(view);
    }

    List<String> keylist = new ArrayList<>();

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.DepartmentNameTV.setText(data.get(position).getDepartmentName().toString());
        holder.LostAddressTV.setText(data.get(position).getAddress().toString());
        //holder.textView_phoneNumber.setText(data.get(position).getPhoneNumber().toString());
        holder.textView_email.setText(data.get(position).getEmail().toString());
        holder.textView_description.setText(data.get(position).getDescription().toString());
        holder.textView_Id.setText(data.get(position).getYourId().toString());
        holder.textView_Date.setText(data.get(position).getDatePick().toString());

        holder.textView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(view.getContext())
                        .setMessage("Do you really want to delete?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                databaseReference.child(keylist.get(position)).removeValue();
                                ShowAdminAddData.getData();

                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        holder.textView_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PhoneCall = String.valueOf(data.get(position).getYourId());
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", PhoneCall, null));
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView DepartmentNameTV;
        TextView LostAddressTV;
        //TextView textView_phoneNumber;
        TextView textView_description;
        TextView textView_phone;
        TextView textView_delete;
        TextView textView_email;
        TextView textView_Id;
        TextView textView_Date;


        public ViewHolder(View itemView) {
            super(itemView);
            DepartmentNameTV = itemView.findViewById(R.id.DepartmentNameTV);
            LostAddressTV = itemView.findViewById(R.id.LostAddressTV);
            //textView_phoneNumber = itemView.findViewById(R.id.textView_phoneNumber);
            textView_description = itemView.findViewById(R.id.textView_description);
            textView_phone = itemView.findViewById(R.id.textView_phone);
            textView_delete = itemView.findViewById(R.id.textView_delete);
            textView_email = itemView.findViewById(R.id.textView_email);
            textView_Id = itemView.findViewById(R.id.textView_Id);
            textView_Date = itemView.findViewById(R.id.textView_Date);

        }
    }

    public class GetKey implements ChildEventListener{

        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            String key = dataSnapshot.getKey();
            keylist.add(key);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
