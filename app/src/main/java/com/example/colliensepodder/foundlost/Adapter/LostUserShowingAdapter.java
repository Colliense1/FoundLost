package com.example.colliensepodder.foundlost.Adapter;

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
    private ArrayList<LostData> lostData;

    public LostUserShowingAdapter(ArrayList<LostData> lostData) {
        this.lostData = lostData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_show_data_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.DepartmentNameTV.setText(lostData.get(position).getDepartmentName().toString());
        holder.LostAddressTV.setText(lostData.get(position).getAddress().toString());
        holder.textView_phoneNumber.setText(lostData.get(position).getEmail().toString());
        holder.textView_description.setText(lostData.get(position).getDescription().toString());
        holder.textView_Id.setText(lostData.get(position).getYourId().toString());
    }

    @Override
    public int getItemCount() {
        return lostData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView DepartmentNameTV;
        TextView LostAddressTV;
        TextView textView_phoneNumber;
        TextView textView_description;
        TextView textView_Id;

        public ViewHolder(View itemView) {
            super(itemView);
            DepartmentNameTV = itemView.findViewById(R.id.DepartmentNameTV);
            LostAddressTV = itemView.findViewById(R.id.LostAddressTV);
            textView_phoneNumber = itemView.findViewById(R.id.textView_phoneNumber);
            textView_description = itemView.findViewById(R.id.textView_description);
            textView_Id = itemView.findViewById(R.id.textView_Id);
        }
    }
}
