package com.pichangaexample.pichanga;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        return view;

    }


    public void setText(String text){

        TextView textView = (TextView) view.findViewById(R.id.perfil_textview);
        textView.setText(text);
    }



}