package com.h4404.trouvtonresto;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class RestosFragment extends Fragment implements ListView.OnItemClickListener {

    ListView mListView;

    List<Restaurant> restos;

    public RestosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restos, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);

        restos = allRestos();

        ArrayAdapter<Restaurant> adapter = new RestaurantListeAdapter(getActivity(), restos);

        mListView.setOnItemClickListener(this);
        mListView.setAdapter(adapter);

        return view;
    }

    private List<Restaurant> allRestos() {
        String[] noms = getResources().getStringArray(R.array.restosName);
        String[] specialites = getResources().getStringArray(R.array.restosSpecialite);
        int[] distances = getResources().getIntArray(R.array.restosDistances);
        int[] tempsAttente = getResources().getIntArray(R.array.restosTpsAttente);

        List<Restaurant> retour = new ArrayList<>(noms.length);
        for (int i = 0 ; i < noms.length ; i++)
            retour.add(new Restaurant(noms[i], specialites[i], tempsAttente[i], distances[i]));
        return retour;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        //A remplacer par id au cas où on implémente le tri avec id l'index du resto dans le fichier string.xml
        bundle.putInt("indexResto", (int)position);
        ((MainActivity)(getActivity())).displayView(R.id.restos_pager, bundle);
    }
}
