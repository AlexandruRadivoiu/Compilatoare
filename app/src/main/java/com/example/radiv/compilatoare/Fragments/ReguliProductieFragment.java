package com.example.radiv.compilatoare.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.radiv.compilatoare.Adapter.RecyclerAdapter;
import com.example.radiv.compilatoare.CVar;
import com.example.radiv.compilatoare.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReguliProductieFragment extends Fragment {


    public ReguliProductieFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reguli_productie, container, false);
        recyclerView = view.findViewById(R.id.reclyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(getContext(),CVar.reguli);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
