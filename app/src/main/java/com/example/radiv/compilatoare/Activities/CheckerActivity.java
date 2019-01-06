package com.example.radiv.compilatoare.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.radiv.compilatoare.AdaugaPFragment;
import com.example.radiv.compilatoare.CChecker;
import com.example.radiv.compilatoare.CGenerator;
import com.example.radiv.compilatoare.CUtil;
import com.example.radiv.compilatoare.CVar;
import com.example.radiv.compilatoare.Cherckers.CLL1GrammarChecker;
import com.example.radiv.compilatoare.Fragments.CodeFragment;
import com.example.radiv.compilatoare.Fragments.ReguliProductieFragment;
import com.example.radiv.compilatoare.Fragments.RunFragment;
import com.example.radiv.compilatoare.R;
import com.example.radiv.compilatoare.ReguliAdapter;

import java.util.ArrayList;

public class CheckerActivity extends AppCompatActivity {

    ReguliAdapter adapter;
    Button check, delete,run,compile;
    TextView logs;
    private String logsString = "";
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_reguli);

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new AdaugaPFragment()).commit();
            logs = findViewById(R.id.logs);
            logs.setMovementMethod(new ScrollingMovementMethod());
            run = findViewById(R.id.run);
            run.setBackgroundResource(R.drawable.ic_play);
            run.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logs.setText("S-a executat! Introduceti propozitia precedata de \"$\" si apasati pe flag!");
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new RunFragment()).addToBackStack(null).commit();

/*
                    Interpreter interpreter = new Interpreter();
                    try {
                        interpreter.set("context", this);//set any variable, you can refer to it directly from string
                        interpreter.eval(CVar.code);//execute code
                    } catch (Exception e) {//handle exception
                        e.printStackTrace();
                    }

*/
                }
            });
            compile= findViewById(R.id.compile);
            compile.setBackgroundResource(R.drawable.ic_gears);
            compile.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    logs.setText("Codul a fost generat si este gata sa fie executat!");
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new CodeFragment()).addToBackStack(null).commit();

                }
            });
            check = findViewById(R.id.check);
            check.setBackgroundResource(R.drawable.ic_list);

            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logs.setText("Gramatica s-a verificat si modificat unde a fost cazul!");
                    CVar.main_neterminale = new ArrayList<>();
                    CVar.main_neterminale.addAll(CVar.neterminale);
                    CVar.reguliProductie = CChecker.eliminareAcelasiInceput(CVar.reguli);


                    CLL1GrammarChecker.isOkay(CVar.reguliProductie);
                    CVar.reguli = CUtil.convertReguliProductie(CVar.reguliProductie);
                    CGenerator.init();
                    CVar.neterminale.clear();
                    CVar.neterminale.addAll(CVar.main_neterminale);
                    CVar.reguli = CUtil.toList(CVar.reguliProductie);

                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new ReguliProductieFragment()).addToBackStack(null).commit();
                   }
            });

            delete = findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logs.setText("");
                }
            });
            delete.setBackgroundResource(R.drawable.ic_delete);
        }
    }
}
