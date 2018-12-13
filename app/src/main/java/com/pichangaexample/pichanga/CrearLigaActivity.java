package com.pichangaexample.pichanga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pichangaexample.pichanga.Adapters.EquipoAdapter;
import com.pichangaexample.pichanga.Models.Equipo;
import com.pichangaexample.pichanga.Models.Liga;

import java.util.ArrayList;
import java.util.UUID;

public class CrearLigaActivity extends AppCompatActivity {

    private ArrayList<Equipo> listaEquipo;
    private EquipoAdapter adapter;
    private EditText txtInput;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_liga_activity);

        listaEquipo = new ArrayList<>();

        ListView listview = (ListView) findViewById(R.id.listViewCrearLiga);
        adapter = new EquipoAdapter(this, listaEquipo);
        listview.setAdapter(adapter);

        inicializarFirebase();

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

        Button btCrear = (Button) findViewById(R.id.buttonCrearLiga);
        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
                final String correo = prefs.getString("correo", "");
                EditText nombre = (EditText) findViewById(R.id.NombreLiga);
                String n = nombre.getText().toString();
                if (!n.equals("")){
                    if(listaEquipo.size()!=0){
                        Liga liga = new Liga();
                        liga.setNombre(n);
                        liga.setIdCreador(correo);
                        liga.setID(UUID.randomUUID().toString());
                        liga.setListaEquipos(listaEquipo);
                        databaseReference.child("Liga").child(liga.getID()).setValue(liga);

                        Intent intencion = new Intent(getApplication(), MainActivity.class);
                        startActivity(intencion);
                        finish();
                    } else
                            {
                                Toast.makeText(getApplicationContext(), "Ingrese Equipos",Toast.LENGTH_SHORT).show();}

                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese Nombre",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}