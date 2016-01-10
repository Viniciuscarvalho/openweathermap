package com.br.openweatherabacomm.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.openweatherabacomm.R;
import com.br.openweatherabacomm.adapters.TabsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabsFragment extends Fragment {

    private View v;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public TabsFragment() {

    }

    public static TabsFragment newInstance(){
        TabsFragment fragment = new TabsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tabs, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabsAdapter(getChildFragmentManager()));

        tabLayout = (TabLayout) v.findViewById(R.id.tabsWeather);
        tabLayout.setupWithViewPager(viewPager);

        return v;

    }

}
