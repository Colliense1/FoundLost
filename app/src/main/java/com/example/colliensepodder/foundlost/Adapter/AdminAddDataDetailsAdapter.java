package com.example.colliensepodder.foundlost.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colliensepodder.foundlost.Model.Data;
import com.example.colliensepodder.foundlost.R;

import java.util.ArrayList;

public class AdminAddDataDetailsAdapter extends RecyclerView.Adapter<AdminAddDataDetailsAdapter.ViewHolder> {

    private ArrayList<Data> data;

    public AdminAddDataDetailsAdapter(ArrayList<Data> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.admin_data_details_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.DepartmentNameTV.setText(data.get(position).getDepartmentName().toString());
        holder.LostAddressTV.setText(data.get(position).getAddress().toString());
        holder.textView_phoneNumber.setText(data.get(position).getEmail().toString());
        holder.textView_description.setText(data.get(position).getDescription().toString());
        holder.textView_Id.setText(data.get(position).getYourId().toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView DepartmentNameTV;
        TextView LostAddressTV;
        TextView textView_phoneNumber;
        TextView textView_description;
        TextView textView_edit;
        TextView textView_delete;
        TextView textView_Id;


        public ViewHolder(View itemView) {
            super(itemView);
            DepartmentNameTV = itemView.findViewById(R.id.DepartmentNameTV);
            LostAddressTV = itemView.findViewById(R.id.LostAddressTV);
            textView_phoneNumber = itemView.findViewById(R.id.textView_phoneNumber);
            textView_description = itemView.findViewById(R.id.textView_description);
            textView_edit = itemView.findViewById(R.id.textView_edit);
            textView_delete = itemView.findViewById(R.id.textView_delete);
            textView_Id = itemView.findViewById(R.id.textView_Id);

        }
    }
}
