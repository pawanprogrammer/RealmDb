package com.trishasofttech.realmdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
Context context;
List<MyData> list;

    public MyAdapter(Context context, List<MyData> list) {
    this.context =context;
    this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design, parent, false);
        MyHolder myHolder = new MyHolder(v);
        return myHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MyData myData = list.get(position);
        holder.tvname.setText(myData.getName());
        holder.tvmobile.setText(myData.getMobile());
        holder.tvemail.setText(myData.getEmail());
        holder.tvaddress.setText(myData.getAddress());
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvname,tvemail,tvmobile,tvaddress;
        public MyHolder(@NonNull View v) {
            super(v);
            tvaddress = v.findViewById(R.id.tvaddress);
            tvemail = v.findViewById(R.id.tvemail);
            tvmobile = v.findViewById(R.id.tvmobile);
            tvname = v.findViewById(R.id.tvname);
        }
    }
}
