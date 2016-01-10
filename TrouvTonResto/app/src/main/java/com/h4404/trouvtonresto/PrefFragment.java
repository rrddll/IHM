package com.h4404.trouvtonresto;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;


public class PrefFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    public PrefFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pref, container, false);

        /*Switch s1 = (Switch) rootView.findViewById(R.id.switch1);
        if (s1 != null) {
            s1.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        }*/

        Switch s2 = (Switch) rootView.findViewById(R.id.switch2);
        if (s2 != null) {
            s2.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        }

        RadioButton rb = (RadioButton) rootView.findViewById(R.id.rb1);
        rb.setChecked(true);

        if (rootView != null) {
            final LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.llparams);

            Context context = getActivity().getApplicationContext();
            String[] strRestos = getResources().getStringArray(R.array.restosName);
            for (int i = 0 ; i < strRestos.length ; i++) {
                CheckBox cb = new CheckBox(context);
                cb.setText(strRestos[i]);
                cb.setId(i);
                cb.setOnClickListener(onCheckboxClicked(cb));
                cb.setButtonDrawable(ContextCompat.getDrawable(context, R.xml.custom_checkbox));
                cb.setTextColor(Color.BLACK);
                ll.addView(cb);
            }
        }


        /*ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pref, container, false);

        CheckBox cb = (CheckBox) rootView.findViewById(R.id.checkBox1);
        cb.setChecked(TutoPageActivity.checkboxes[0]);*/

        return rootView;
    }

    View.OnClickListener onCheckboxClicked(final CheckBox cb) {
        return new View.OnClickListener() {

            public void onClick(View v) {
                TutoPageActivity.checkboxes[cb.getId()] = cb.isChecked();
            }
        };
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        /*Toast.makeText(this, "The Switch is " + (isChecked ? "on" : "off"),
                Toast.LENGTH_SHORT).show();*/
        /*if(buttonView.getId() == R.id.switch1) {
            if (isChecked) {
                getActivity().findViewById(R.id.llparams).setVisibility(View.VISIBLE);
            } else {

                getActivity().findViewById(R.id.llparams).setVisibility(View.GONE);
            }
        }else{*/
            if (isChecked) {
                getActivity().findViewById(R.id.llparams2).setVisibility(View.VISIBLE);
            } else {
                getActivity().findViewById(R.id.llparams2).setVisibility(View.GONE);
            }
        //}
    }
}
