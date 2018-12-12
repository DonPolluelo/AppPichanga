package com.pichangaexample.pichanga;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class PerfilFragment extends Fragment {
    Integer i;
    String str="";
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        SharedPreferences prefs = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        String correo = prefs.getString("correo", "");

        for(i=0;i<correo.length();i++){
            if (correo.charAt(i)!='@'){
                str += correo.charAt(i);
            }
            else{break;}
        }
        setText(str);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tripsRef = rootRef.child("Ligas");
        final ArrayList<String> list = new ArrayList<>();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String id = ds.child("id").getValue(String.class);
                    String id_creador = ds.child("id_creador").getValue(String.class);
                    Log.d("TAG", id + " / " + id_creador  + " / ");
                    setText(id);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        tripsRef.addListenerForSingleValueEvent(valueEventListener);

        return view;



    }


    public void setText(String text){

        TextView textView = (TextView) view.findViewById(R.id.perfil_textview);
        textView.setText(text);
    }





}