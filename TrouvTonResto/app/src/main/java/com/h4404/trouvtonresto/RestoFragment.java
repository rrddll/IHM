package com.h4404.trouvtonresto;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

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

      /*  Button goButton = (Button) result.findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("to", (int) mCurrentResto);
                ((MainActivity) (getActivity())).displayView(R.id.route_fragment, bundle);
            }
        });*/

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