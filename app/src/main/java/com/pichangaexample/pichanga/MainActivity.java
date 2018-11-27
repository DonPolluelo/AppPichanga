package com.pichangaexample.pichanga;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

    public class MainActivity extends AppCompatActivity {

        private TabLayout tabLayout;
        private ViewPager viewpager;

        public static final String user = "names";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //Toolbar
            /*Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
            setSupportActionBar(myToolbar);

            String horario = getResources().getString(R.string.horario);
            String malla = getResources().getString(R.string.malla);
            String semestre = getResources().getString(R.string.semestre);*/

            String mis_ligas = "Mis Ligas";
            String ligas_seguidas = "Ligas Seguidas";


            tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
            viewpager = (ViewPager) findViewById(R.id.viewpager_id);
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            // adapter.AddFragment(new HorarioFragment(), horario);
            adapter.AddFragment(new MisLigasFragment(), mis_ligas);
            adapter.AddFragment(new LigasSeguidasFragment(), ligas_seguidas);

            viewpager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewpager);
        }
    }
