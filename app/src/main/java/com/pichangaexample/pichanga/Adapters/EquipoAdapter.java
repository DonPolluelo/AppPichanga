package com.pichangaexample.pichanga.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pichangaexample.pichanga.Models.Equipo;
import com.pichangaexample.pichanga.R;

import java.util.ArrayList;

public class EquipoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Equipo> listaEquipo;

    public EquipoAdapter(Context context, ArrayList<Equipo> listaEquipo){
        this.context = context;
        this.listaEquipo = listaEquipo;
    }

    @Override
    public int getCount() {
        return listaEquipo.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEquipo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.lista_equipos,null);
        }
        TextView nombre = (TextView) convertView.findViewById(R.id.textViewEquipos);
        nombre.setText(listaEquipo.get(position).getNombre());
        return convertView;
    }
}
