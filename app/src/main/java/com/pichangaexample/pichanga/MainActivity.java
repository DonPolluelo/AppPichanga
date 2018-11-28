package com.pichangaexample.pichanga;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TabLayout tabLayout;
    private ViewPager viewpager;

    public static final String user = "names";



    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_partidos:
                    mTextMessage.setText(R.string.title_partidos);
                    return true;
                case R.id.navigation_favoritos:
                    mTextMessage.setText(R.string.title_favoritos);
                    return true;
                case R.id.navigation_ligas:
                    mTextMessage.setText(R.string.title_ligas);
                    return true;
                case R.id.navigation_perfil:
                    mTextMessage.setText(R.string.title_perfil);
                    return true;
            }
            return false;
        }
    };*/



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_partidos:
                            selectedFragment = new PartidosFragment();
                            break;
                        case R.id.navigation_favoritos:
                            selectedFragment = new FavoritosFragment();
                            break;
                        case R.id.navigation_ligas:
                            selectedFragment = new LigasFragment();
                            break;
                        case R.id.navigation_perfil:
                            selectedFragment = new PerfilFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };






    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_main);
        String mis_ligas = "Mis Ligas";
        String ligas_seguidas = "Ligas Seguidas";


        mTextMessage = (TextView) findViewById(R.id.message);
       BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
       navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PartidosFragment()).commit();
        }
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                Intent logout = new Intent(this, Register.class);
                startActivity(logout);
                break;
            default:
                // esto no deberia pasar hehhehehe
        }
        return super.onOptionsItemSelected(item);
    }







       /* tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewpager = (ViewPager) findViewById(R.id.viewpager_id);
        //ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // adapter.AddFragment(new HorarioFragment(), horario);
        //adapter.AddFragment(new MisLigasFragment(), mis_ligas);
        /*adapter.AddFragment(new LigasSeguidasFragment(), ligas_seguidas);

        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);*/
    }
