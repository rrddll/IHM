package com.h4404.trouvtonresto;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RestoFragment extends Fragment {

    int currentResto = 0;
    public RestoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_resto, container, false);

        Button goButton = (Button) result.findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("to", (int)currentResto);
                ((MainActivity)(getActivity())).displayView(R.id.route_fragment, bundle);
            }
        });
        return result;
    }
}
