package com.h4404.trouvtonresto;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class RestosFragment extends Fragment implements ListView.OnItemClickListener {

    ArrayAdapter<Restaurant> mAdapter;
    ListView mListView;
    TextView horairesLabel;
    int timestampDebut;
    List<Restaurant> restos;
    int curTimestamp;

    public RestosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restos, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);

        horairesLabel = (TextView) view.findViewById(R.id.horairePrevision);

        timestampDebut = getResources().getInteger(R.integer.heureDebut) * 60 + getResources().getInteger(R.integer.minuteDebut);
        int timestampFin = getResources().getInteger(R.integer.heureFin) * 60 + getResources().getInteger(R.integer.minuteFin);
        afficheHeure(timestampDebut);

        final SeekBar sk = (SeekBar) view.findViewById(R.id.seekBar);
        sk.setMax(timestampFin - timestampDebut);
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int valeur;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                afficheHeure(i + timestampDebut);
                valeur = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mListView.setEnabled(false);
                mListView.setAlpha(0.25f);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(timestampDebut+valeur != curTimestamp) {
                    mListView.setAlpha(0f);
                    curTimestamp = timestampDebut+valeur;
                    for(Restaurant resto : restos) {
                        resto.shuffleWaitingTime();
                    }
                    afficheRestaurants();
                    try {
                        TimeUnit.MILLISECONDS.sleep(400);
                    } catch (InterruptedException e) {}
                }
                mListView.setAlpha(1.0f);
                mListView.setEnabled(true);
            }
        });

        restos = allRestos();
        afficheRestaurants();

        return view;
    }

    private void afficheHeure(int timestamp) {
        int heure = timestamp / 60;
        int minute = timestamp % 60;
        String text = String.format("%02d:%02d", heure, minute);
        horairesLabel.setText(text);
    }

    private void afficheRestaurants() {
        Collections.sort(restos, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant restaurant, Restaurant t1) {
                return restaurant.getTempsAttente() - t1.getTempsAttente();
            }
        });

        mAdapter = new RestaurantListeAdapter(getActivity(), restos);

        mListView.setOnItemClickListener(this);
        mListView.setAdapter(mAdapter);
    }

    private List<Restaurant> allRestos() {
        String[] noms = getResources().getStringArray(R.array.restosName);
        String[] specialites = getResources().getStringArray(R.array.restosSpecialite);
        int[] distances = getResources().getIntArray(R.array.restosDistances);
        int[] tempsAttente = getResources().getIntArray(R.array.restosTpsAttente);

        List<Restaurant> retour = new ArrayList<>(noms.length);
        for (int i = 0 ; i < noms.length ; i++)
            retour.add(new Restaurant(i, noms[i], specialites[i], tempsAttente[i], distances[i]));
        return retour;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putInt("indexResto", (int) position);

        ArrayList<Integer> sortedRestos = new ArrayList<>();
        for ( Restaurant r : restos) {
            sortedRestos.add(r.getId());
        }
        bundle.putIntegerArrayList("sortedRestos", sortedRestos);

        ((MainActivity) (getActivity())).displayView(R.id.restos_pager, bundle);
    }
}
