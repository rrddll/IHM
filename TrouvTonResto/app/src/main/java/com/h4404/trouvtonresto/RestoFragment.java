package com.h4404.trouvtonresto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RestoFragment extends Fragment {

    ExpandableMenuAdapter menuAdapter;
    List<String> menuDataHeader;
    HashMap<String, List<String>> menuDataChild;

    int mCurrentResto = 0;

    String[] entreesName;
    String[] platsName;
    String[] dessertsName;


    public RestoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_resto, container, false);

        Bundle bundle = getArguments();
        if (bundle != null)
            mCurrentResto = bundle.getInt("indexResto");

        int drawable = 0;
        switch (mCurrentResto)
        {
            case 0 :
                drawable = R.drawable.ic_l1l2beurk;
                break;
            case 1 :
                drawable = R.drawable.ic_olivier;
                break;
            case 2 :
                drawable = R.drawable.ic_grillon;
                break;
            case 3 :
                drawable = R.drawable.ic_prevert;
                break;
            case 4 :
                drawable = R.drawable.ic_ru;
                break;
            default :
                break;
        }


        ((ImageView)result.findViewById(R.id.idGraphe)).setImageResource(drawable);

        Button goButton = (Button) result.findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?mode=walking&daddr=" + CarteFragment.mRestosLat[mCurrentResto] + "," + CarteFragment.mRestosLon[mCurrentResto]));
                startActivity(intent);
            }
        });

        entreesName = getResources().getStringArray(R.array.restosEntrees);
        platsName = getResources().getStringArray(R.array.restosPlats);
        dessertsName = getResources().getStringArray(R.array.restosDesserts);

        ExpandableListView menuView = (ExpandableListView) result.findViewById(R.id.menuView);

        prepareListData();

        menuAdapter = new ExpandableMenuAdapter(getActivity(), menuDataHeader, menuDataChild);

        // setting list adapter
        menuView.setAdapter(menuAdapter);

        return result;
    }

    private void prepareListData() {
        menuDataHeader = new ArrayList<String>();
        menuDataChild = new HashMap<String, List<String>>();

        // Adding child data
        menuDataHeader.add(getString(R.string.entree));
        menuDataHeader.add(getString(R.string.plat));
        menuDataHeader.add(getString(R.string.dessert));

        // Adding child data
        List<String> entrees = new ArrayList<String>();
        for (int i = 0 ; i < 3 ; i++)
            entrees.add(entreesName[((mCurrentResto + 1) * i + 3) % entreesName.length]);

        List<String> plats = new ArrayList<String>();
        for (int i = 0 ; i < 3 ; i++)
            plats.add(platsName[((mCurrentResto + 1) * i + 3) % platsName.length]);

        List<String> desserts = new ArrayList<String>();
        for (int i = 0 ; i < 3 ; i++)
            desserts.add(dessertsName[((mCurrentResto + 1) * i + 3) % dessertsName.length]);

        menuDataChild.put(menuDataHeader.get(0), entrees);
        menuDataChild.put(menuDataHeader.get(1), plats);
        menuDataChild.put(menuDataHeader.get(2), desserts);

    }
}