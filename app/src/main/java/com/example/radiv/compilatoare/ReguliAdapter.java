package com.example.radiv.compilatoare;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ReguliAdapter extends BaseAdapter implements ListAdapter {
    Spinner spinner;
    private Context context;
    public static List<Reguli> reguli;

    public ReguliAdapter(@NonNull Context context, ArrayList<Reguli> list) {
        this.context = context;
        reguli = list;
    }


    @Override
    public int getCount() {
        return reguli.size();
    }

    @Override
    public Object getItem(int position) {
        return reguli.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        final ViewHolder holder;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);


            final Reguli regula = reguli.get(position);
            EditText dreapta = (EditText) listItem.findViewById(R.id.parteaDreapta);
            TextView count = listItem.findViewById(R.id.count);
            /* TextView dreapta2= (TextView) listItem.findViewById(R.id.parteaDreapta2); */
            spinner = listItem.findViewById(R.id.parteaStanga);
            ArrayList<String> list = new ArrayList<>();
            count.setText(String.valueOf(position));

            if (position == 0) {
                list.add(CVar.startSimbol.nume);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context,
                        android.R.layout.simple_spinner_dropdown_item, list);
                spinner.setAdapter(adapter2);


            } else {//aici baga s din nou
                for (String item : CVar.neterminale) {
                    list.add(item);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_spinner_dropdown_item, list);
                spinner.setAdapter(adapter);

            }

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    reguli.get(position).stanga = adapterView.getSelectedItem().toString();

                    if (position == 0)
                        reguli.get(position).stanga = CVar.startSimbol.nume;
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    reguli.get(position).stanga = adapterView.getSelectedItem().toString();

                    if (position == 0)
                        reguli.get(position).stanga = CVar.startSimbol.nume;

                }
            });
//aici
            dreapta.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence cs, int s, int b, int c) {
                    reguli.get(position).dreapta = cs.toString();

                }

                public void afterTextChanged(Editable editable) {
                    int succes = 1;
                    //reguli.get(position).dreapta= editable.toString();

                }

                public void beforeTextChanged(CharSequence cs, int i, int j, int
                        k) {
                    reguli.get(position).dreapta = cs.toString();
                }
            });
        }
        /*
         dreapta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ChooseActivity.class);
              v.getContext().startActivity(intent);


            }
        });
        */
        CVar.reguli = reguli;
        return listItem;
    }
    class ViewHolder {
        EditText dreapta;
        TextView count ;
        Spinner spinner;



    }

}
