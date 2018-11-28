package com.pichangaexample.pichanga;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.pichangaexample.pichanga.Adapters.EquipoAdapter;
import com.pichangaexample.pichanga.Models.Equipo;

import java.util.ArrayList;

public class CrearLigaActivity extends AppCompatActivity {

    private ArrayList<Equipo> listaEquipo;
    private EquipoAdapter adapter;
    private EditText txtInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_liga_activity);

        listaEquipo = new ArrayList<>();

        ListView listview = (ListView) findViewById(R.id.listViewCrearLiga);
        adapter = new EquipoAdapter(this, listaEquipo);
        listview.setAdapter(adapter);

        txtInput = (EditText) findViewById(R.id.NombreEquipo);
        Button btAdd= (Button) findViewById(R.id.buttonAgregarEquipo);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreEquipo = txtInput.getText().toString();
                if (!nombreEquipo.equals("")){
                    Equipo equipo = new Equipo(nombreEquipo);
                    listaEquipo.add(equipo);
                    adapter.notifyDataSetChanged();
                    txtInput.setText("");
                }
            }
        });
    }
}