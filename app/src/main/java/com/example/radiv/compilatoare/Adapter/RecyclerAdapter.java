package com.example.radiv.compilatoare.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.radiv.compilatoare.R;
import com.example.radiv.compilatoare.Reguli;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Reguli> list;

    public RecyclerAdapter(Context context, List<Reguli> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = (View) LayoutInflater.from(context).inflate(R.layout.recycler_item, viewGroup, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.stanga.setText(list.get(i).getStanga());
        viewHolder.dreapta.setText(list.get(i).getDreapta());
    }

    @Override
    public int getItemCount() {
       return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView stanga, dreapta;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stanga = itemView.findViewById(R.id.recyclerStanga);
            dreapta = itemView.findViewById(R.id.recyclerDreapta);
        }
    }
}
