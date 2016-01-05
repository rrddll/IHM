package com.h4404.trouvtonresto;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


public class PrefFragment extends Fragment {

    public PrefFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pref, container, false);

        CheckBox cb = (CheckBox) rootView.findViewById(R.id.checkBox1);
        cb.setChecked(TutoPageActivity.checkboxes[0]);
        return rootView;
    }
}
