package com.example.radiv.compilatoare.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.radiv.compilatoare.CUtil;
import com.example.radiv.compilatoare.CVar;
import com.example.radiv.compilatoare.R;

public class MainActivity extends AppCompatActivity {

    EditText boxStart, boxTerminale, boxNeterminale, boxReguliDeProductie;
    Button adaugaReguli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boxStart = findViewById(R.id.boxStart);
        boxTerminale = findViewById(R.id.boxSigma);
        boxNeterminale = findViewById(R.id.boxVn);
        adaugaReguli = findViewById(R.id.adaugaBtn);
        boxReguliDeProductie = findViewById(R.id.boxNumarP);
        adaugaReguli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CVar.startSimbol = CUtil.getStart(boxStart.getText().toString());
                CVar.neterminale = CUtil.getList(boxNeterminale.getText().toString());
                CVar.terminale = CUtil.getList(boxTerminale.getText().toString());
                Intent intent = new Intent(MainActivity.this, CheckerActivity.class);
                intent.putExtra("NUMAR", Integer.parseInt(boxReguliDeProductie.getText().toString()));
                startActivity(intent);
            }
            //generator neterminale
        });
    }

}
