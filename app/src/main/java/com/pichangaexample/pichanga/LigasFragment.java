package com.pichangaexample.pichanga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class LigasFragment extends Fragment {
    FloatingActionMenu actionMenu;
    FloatingActionButton button1,button2;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ligas, container, false);



        SharedPreferences prefs = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        final String correo = prefs.getString("correo", "");


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference scoreRef = rootRef.child("Liga");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> list = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String nombre = ds.child("nombre").getValue(String.class);
                    String creador = ds.child("idCreador").getValue(String.class);
                    if(correo.equals(creador)) {
                        list.add(nombre);
                    }


                }
                listView = (ListView) getActivity().findViewById(R.id.mis_ligas_listview);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), TablaActivity.class);
                        intent.putExtra("nombre", listView.getItemAtPosition(position).toString());
                        startActivity(intent);
                    }
                });

                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        scoreRef.addListenerForSingleValueEvent(eventListener);










        FloatingActionMenu actionMenu = (FloatingActionMenu) view.findViewById(R.id.menu_flotante);

        com.github.clans.fab.FloatingActionButton button1 = (com.github.clans.fab.FloatingActionButton)
                view.findViewById(R.id.EditarLiga);
        com.github.clans.fab.FloatingActionButton button2 = (com.github.clans.fab.FloatingActionButton)
                view.findViewById(R.id.CrearLiga);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(),"Editar Liga aún no está disponible",Toast.LENGTH_SHORT).show();
               // Intent i = new Intent(getActivity(),CrearLigaActivity.class);
               // startActivity(i);
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Toast.makeText(getActivity(),"Eliminar Ramo",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),CrearLigaActivity.class);
                startActivity(i);

            }
        });

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),CrearLigaActivity.class);
                startActivity(i);
            }
        });*/










        //SharedPreferences prefs = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        //final String correo = prefs.getString("correo", "");
        Query query = FirebaseDatabase.getInstance().getReference("Liga")
                .orderByChild("idCreador")
                .equalTo(correo);
        Log.i("Liga Fragment",query.toString());
        return view;
    }
}