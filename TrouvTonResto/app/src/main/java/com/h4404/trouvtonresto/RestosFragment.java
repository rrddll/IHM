package com.h4404.trouvtonresto;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class RestosFragment extends Fragment implements ListView.OnItemClickListener {

    ListView mListView;

    String[] restos;

    public RestosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restos, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);

        restos = getResources().getStringArray(R.array.restosName);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, restos);

        mListView.setOnItemClickListener(this);
        mListView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        //A remplacer par id au cas où on implémente le tri avec id l'index du resto dans le fichier string.xml
        bundle.putInt("indexResto", (int)position);
        ((MainActivity)(getActivity())).displayView(R.id.restos_pager, bundle);
    }
}
