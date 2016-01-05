package com.h4404.trouvtonresto;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;


public class TutoPageFragment extends Fragment {

    public static final String ARG_PAGE = "page";

    private int mPageNumber;

    public static View myView;

    public static TutoPageFragment create(int pageNumber) {
        TutoPageFragment fragment = new TutoPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public TutoPageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        myView = getView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int frag = R.layout.fragment_tuto_page1;

        switch (mPageNumber) {
            case 1:
                frag = R.layout.fragment_tuto_page2;
                break;
            case 2:
                frag = R.layout.fragment_tuto_page3;
                break;
            case 3:
                frag = R.layout.fragment_tuto_page4;
                break;
        }

        ViewGroup rootView = (ViewGroup) inflater.inflate(frag, container, false);
        if (rootView != null && frag == R.layout.fragment_tuto_page3) {
            final LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.lltuto);

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

        return rootView;
    }

    View.OnClickListener onCheckboxClicked(final CheckBox cb) {
        return new View.OnClickListener() {

            public void onClick(View v) {
                TutoPageActivity.checkboxes[cb.getId()] = cb.isChecked();
            }
        };
    }

    public int getPageNumber() {
        return mPageNumber;
    }

}