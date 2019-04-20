package com.example.colliensepodder.foundlost.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colliensepodder.foundlost.Model.Data;
import com.example.colliensepodder.foundlost.Model.LostData;
import com.example.colliensepodder.foundlost.R;

import java.util.ArrayList;

public class LostUserShowingAdapter extends RecyclerView.Adapter<LostUserShowingAdapter.ViewHolder> {

    private ArrayList<Data> data;


    public LostUserShowingAdapter(ArrayList<Data> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lost_user_show_data_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.DepartmentNameTV.setText(data.get(position).getDepartmentName().toString());
        holder.LostAddressTV.setText(data.get(position).getAddress().toString());
        //holder.textView_phoneNumber.setText(data.get(position).getPhoneNumber().toString());
        holder.textView_email.setText(data.get(position).getEmail().toString());
        holder.textView_description.setText(data.get(position).getDescription().toString());
        holder.textView_Id.setText(data.get(position).getYourId().toString());

        holder.textView_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PhoneCall = String.valueOf(data.get(position).getYourId());
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",PhoneCall,null));
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
        TextView textView_phoneNumber;
        TextView textView_email;
        TextView textView_description;
        TextView textView_Id;
        TextView textView_phone;

        public ViewHolder(View itemView) {
            super(itemView);
            DepartmentNameTV = itemView.findViewById(R.id.DepartmentNameTV);
            LostAddressTV = itemView.findViewById(R.id.LostAddressTV);
            //textView_phoneNumber = itemView.findViewById(R.id.textView_phoneNumber);
            textView_email = itemView.findViewById(R.id.textView_email);
            textView_description = itemView.findViewById(R.id.textView_description);
            textView_Id = itemView.findViewById(R.id.textView_Id);
            textView_phone = itemView.findViewById(R.id.textView_phone);
        }
    }
}
