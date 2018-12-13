package com.pichangaexample.pichanga;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TablaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla_posiciones);
        TextView textView = (TextView) findViewById(R.id.textViewTablaPosiciones);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            textView.setText(bundle.getString("nombre"));
        }
    }
}
