package com.example.radiv.compilatoare;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter implements ListAdapter {
    Spinner spinner;
    private Context context;
    public static List<Reguli> reguli;

    public ItemAdapter(@NonNull Context context, ArrayList<Reguli> list) {
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
        if (listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        final Reguli regula = reguli.get(position);
        // EditText dreapta = (EditText) listItem.findViewById(R.id.parteaDreapta);
        TextView dreapta2= (TextView) listItem.findViewById(R.id.parteaDreapta2);
        spinner = listItem.findViewById(R.id.parteaStanga);
        ArrayList<String> list = new ArrayList<>();
        if (position == 0) {
            list.add(CVar.startSimbol.nume);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context,
                    android.R.layout.simple_spinner_dropdown_item, list);

        } else {//aici baga s din nou
            for (String item : CVar.neterminale) {
                list.add(item);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                    android.R.layout.simple_spinner_dropdown_item, list);

        }

        CVar.reguli = reguli;
        return listItem;
    }


}
