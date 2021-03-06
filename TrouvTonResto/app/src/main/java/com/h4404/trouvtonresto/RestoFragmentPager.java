package com.h4404.trouvtonresto;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestoFragmentPager extends Fragment {

    String[] restosName;
    Integer mCurrentResto = 0;
    ArrayList<Integer> mSortedRestos = new ArrayList<>();

    public RestoFragmentPager() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_resto_pager, container, false);

        restosName = getResources().getStringArray(R.array.restosName);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mCurrentResto = bundle.getInt("indexResto");
            mSortedRestos = bundle.getIntegerArrayList("sortedRestos");
        }

        setCurrentResto(mCurrentResto, false);

        //Link the on left and on right button
        ImageButton leftButton = (ImageButton) result.findViewById(R.id.leftButton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLeft();
            }
        });

        ImageButton rightButton = (ImageButton) result.findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRight();
            }
        });

        result.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            @Override
            public void onSwipeLeft() {
                showRight();
            }

            @Override
            public void onSwipeRight() {
                showLeft();
            }
        });
        return result;
    }

    //Affiche le resto à gauche
    public void showLeft(){
        setCurrentResto((mCurrentResto == 0) ? restosName.length - 1 : mCurrentResto - 1, true);
    }

    //Affiche le resto à droite
    public void showRight(){
        setCurrentResto((mCurrentResto == restosName.length - 1) ? 0 : mCurrentResto + 1, false);
    }

    //Modifie le resto a afficher
    private void setCurrentResto(Integer nextResto, boolean leftToRight){

        RestoFragment fragment = new RestoFragment();
        fragment.setPager(this);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (leftToRight)
            ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        else
            ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);

        Bundle bundle = new Bundle();
        bundle.putInt("indexResto", (int) mSortedRestos.get(nextResto));
        fragment.setArguments(bundle);

        ft.replace(R.id.resto_frame, fragment);
        ft.commit();

        mCurrentResto = nextResto;
    }
}
