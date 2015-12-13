package com.h4404.trouvtonresto;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class RouteFragment extends Fragment {

    String[] restosName;
    int to;
    public RouteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route, container, false);
        Bundle bundle = getArguments();
        if (bundle != null)
            to = bundle.getInt("to");

        return view;
    }
}
