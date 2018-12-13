package com.pichangaexample.pichanga;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class PerfilFragment extends Fragment {
    Integer i;
    String str="";

    int listSize;
    TextView t;

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil, container, false);

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

                listSize = list.size();
                t=(TextView) getActivity().findViewById(R.id.textView3);
                t.setText("Ligas Creadas por el Usuario: " + String.valueOf(listSize));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        scoreRef.addListenerForSingleValueEvent(eventListener);







        for(i=0;i<correo.length();i++){
            if (correo.charAt(i)!='@'){
                str += correo.charAt(i);
            }
            else{break;}
        }
        setText(str);

        return view;

    }


    public void setText(String text){

        TextView textView = (TextView) view.findViewById(R.id.perfil_textview);
        textView.setText(text);
    }



}