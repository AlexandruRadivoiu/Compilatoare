package com.example.radiv.compilatoare.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.radiv.compilatoare.CVar;
import com.example.radiv.compilatoare.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CodeFragment extends Fragment {


    public CodeFragment() {
        // Required empty public constructor
    }


    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_code, container, false);
        textView = view.findViewById(R.id.textView);
        textView.setText(CVar.code);
        textView.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }

}
