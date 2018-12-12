package com.pichangaexample.pichanga;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PartidosFragment extends Fragment {
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    public PartidosFragment(){

    }
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_partidos, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.AddFragment(new HorariosFragment(),"Horarios");
        adapter.AddFragment(new TablaPosFragment(),"Tabla de Posiciones");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}