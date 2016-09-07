package com.example.jhon.conexionxml.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jhon.conexionxml.Adapters.ListCitiesAdapter;
import com.example.jhon.conexionxml.Models.Cities;
import com.example.jhon.conexionxml.Net.SyncGetXml;
import com.example.jhon.conexionxml.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueryCitiesFragment extends Fragment implements SyncGetXml.OnSyncListener, View.OnClickListener {
    ListCitiesAdapter adapter;
    ListView list;
    Button button;
    EditText editText;


    public QueryCitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_query_cities, container, false);

        list = (ListView) v.findViewById(R.id.list);
        button = (Button) v.findViewById(R.id.buttonsearch);
        editText = (EditText) v.findViewById(R.id.edit_search);

        button.setOnClickListener(this);

        return v;
    }

    @Override
    public void OnPrepareConection(int state) {

    }

    @Override
    public void OnFinishedConection(int state, List<Cities> cities, String e) {
        if (state == SyncGetXml.SYNC_CORRECT){
            adapter = new ListCitiesAdapter(cities,getActivity());
            list.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View view) {
        String country = editText.getText().toString();
        SyncGetXml syncGetXml = new SyncGetXml("http://www.webservicex.net/globalweather.asmx/GetCitiesByCountry?CountryName="+country+"",null,this);
        syncGetXml.conectinoWithServer();
    }
}
