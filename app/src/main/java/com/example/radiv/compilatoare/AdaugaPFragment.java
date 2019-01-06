package com.example.radiv.compilatoare;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdaugaPFragment extends Fragment {


    public AdaugaPFragment() {
        // Required empty public constructor
    }

    ReguliAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_adauga, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list);
        Bundle extras = getActivity().getIntent().getExtras();
      //  Bundle extras = getArguments();
        ArrayList<Reguli> x = new ArrayList<Reguli>();
        for (int i = 0; i < extras.getInt("NUMAR"); i++)
            x.add(new Reguli());
        adapter = new ReguliAdapter(getContext(), x);
        listView.setAdapter(adapter);
        return view;
    }

}
