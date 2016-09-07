package com.example.jhon.conexionxml.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.jhon.conexionxml.R;
import com.example.jhon.conexionxml.interfaces.OnFragmentWifiListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class WifiStateFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    OnFragmentWifiListener onFragmentWifiListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onFragmentWifiListener = (OnFragmentWifiListener) context;
    }

    public WifiStateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_wifi_state,container,false);
        SwitchCompat switchCompat = (SwitchCompat) v.findViewById(R.id.wifi_state);
        switchCompat.setOnCheckedChangeListener(this);
        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        onFragmentWifiListener.onWifiStateChange(b);
    }
}
