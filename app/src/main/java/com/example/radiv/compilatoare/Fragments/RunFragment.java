package com.example.radiv.compilatoare.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.radiv.compilatoare.CVar;
import com.example.radiv.compilatoare.R;

import bsh.Interpreter;


/**
 * A simple {@link Fragment} subclass.
 */
public class RunFragment extends Fragment {


    public RunFragment() {
        // Required empty public constructor
    }


    Button run;
    EditText editText;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_run, container, false);
        run = view.findViewById(R.id.run);
        run.setBackgroundResource(R.drawable.ic_racing_flag);
        editText = view.findViewById(R.id.editText);
        textView = view.findViewById(R.id.textView);

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CVar.input = new StringBuilder(editText.getText().toString());


                Interpreter interpreter = new Interpreter();
                try {
                    interpreter.set("context", this);//set any variable, you can refer to it directly from string
                    interpreter.set("input",new String(CVar.input.toString()));
                    interpreter.eval(CVar.code);//execute code
                    textView.setText((String)interpreter.get("input"));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        return view;
    }

}
