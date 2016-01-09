package com.h4404.trouvtonresto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RestoFragment extends Fragment {

    ExpandableMenuAdapter mMenuAdapter;
    List<String> mMenuDataHeader;
    HashMap<String, List<String>> mMenuDataChild;

    int mCurrentResto = 0;

    String[] mEntreesName;
    String[] mPlatsName;
    String[] mDessertsName;
    String[] mRestosName;

    RestoFragmentPager mPager = null;


    public RestoFragment() {
    }

    public void setPager(RestoFragmentPager pager){
        mPager = pager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_resto, container, false);

        mRestosName = getResources().getStringArray(R.array.restosName);


        //Recover the current resto
        Bundle bundle = getArguments();
        if (bundle != null)
            mCurrentResto = bundle.getInt("indexResto");

        ((MainActivity)(getActivity())).setTitle(mRestosName[mCurrentResto]);

        //Recover the graph associated to the current resto
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
            case 5 :
                drawable = R.drawable.ic_beurklc;
                break;
            default :
                break;
        }
        ((ImageView)result.findViewById(R.id.idGraphe)).setImageResource(drawable);

        //Link the go button to the google map activity
        Button goButton = (Button) result.findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?mode=walking&daddr=" + CarteFragment.mRestosLat[mCurrentResto] + "," + CarteFragment.mRestosLon[mCurrentResto]));
                startActivity(intent);
            }
        });

        //Create the expandable menu
        mEntreesName = getResources().getStringArray(R.array.restosEntrees);
        mPlatsName = getResources().getStringArray(R.array.restosPlats);
        mDessertsName = getResources().getStringArray(R.array.restosDesserts);

        ExpandableListView menuView = (ExpandableListView) result.findViewById(R.id.menuView);
        prepareListData();
        mMenuAdapter = new ExpandableMenuAdapter(getActivity(), mMenuDataHeader, mMenuDataChild);

        menuView.setAdapter(mMenuAdapter);// setting list adapter

        //To update the height of the view (in order to be able to scroll inside the scroll view)
        menuView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });

        if (mPager != null) {
            //Active the on left and on right gesture on the expandable list view
            OnSwipeTouchListener gestureListener = new OnSwipeTouchListener(getActivity()) {
                @Override
                public void onSwipeLeft() {
                    mPager.showRight();
                }

                @Override
                public void onSwipeRight() {
                    mPager.showLeft();
                }
            };
            result.findViewById(R.id.scrollView).setOnTouchListener(gestureListener);
            menuView.setOnTouchListener(gestureListener);
        }

        return result;
    }

    private void prepareListData() {
        mMenuDataHeader = new ArrayList<String>();
        mMenuDataChild = new HashMap<String, List<String>>();

        // Adding child data
        mMenuDataHeader.add(getString(R.string.entree));
        mMenuDataHeader.add(getString(R.string.plat));
        mMenuDataHeader.add(getString(R.string.dessert));

        // Adding child data
        List<String> entrees = new ArrayList<String>();
        for (int i = 0 ; i < 3 ; i++)
            entrees.add(mEntreesName[((mCurrentResto + 1) * i + 3) % mEntreesName.length]);

        List<String> plats = new ArrayList<String>();
        for (int i = 0 ; i < 3 ; i++)
            plats.add(mPlatsName[((mCurrentResto + 1) * i + 3) % mPlatsName.length]);

        List<String> desserts = new ArrayList<String>();
        for (int i = 0 ; i < 3 ; i++)
            desserts.add(mDessertsName[((mCurrentResto + 1) * i + 3) % mDessertsName.length]);

        mMenuDataChild.put(mMenuDataHeader.get(0), entrees);
        mMenuDataChild.put(mMenuDataHeader.get(1), plats);
        mMenuDataChild.put(mMenuDataHeader.get(2), desserts);
    }

    private void setListViewHeight(ExpandableListView listView, int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }
}