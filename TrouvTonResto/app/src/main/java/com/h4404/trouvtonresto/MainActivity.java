package com.h4404.trouvtonresto;

import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.MenuItem;
import android.view.Menu;

import java.util.Stack;

public class  MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int mBackView;
    private Menu mMenuActionBar;
    private Stack<Pair<String, Boolean> > mFragmentStack = new Stack<Pair<String, Boolean> >();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayView(R.id.nav_restaurants, null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {//If the drawer is open, we close it
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (mFragmentStack.size() > 1)//If it is not the first one fragment, we go back
        {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment oldFragment = getFragmentManager().findFragmentByTag(mFragmentStack.peek().first);
            mFragmentStack.pop();
            Fragment fragment = getFragmentManager().findFragmentByTag(mFragmentStack.peek().first);

            ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
            ft.remove(oldFragment);
            ft.show(fragment);
            ft.commit();
            getFragmentManager().executePendingTransactions();

            // set the toolbar title
            if (getSupportActionBar() != null) {
                if (mMenuActionBar != null)//Show the search button
                    mMenuActionBar.findItem(R.id.search).setVisible(mFragmentStack.peek().second);
                setTitle(mFragmentStack.peek().first);
            }
        }
        else//Else we remove the last fragment and exit the app.
        {
            mFragmentStack.clear();//Clean the stack and show the new first fragment of the stack
            for (int i = 0 ; i < getFragmentManager().getBackStackEntryCount() ; ++i)
                getFragmentManager().popBackStack();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        mMenuActionBar = menu;
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mFragmentStack.clear();//Clean the stack and show the new first fragment of the stack
        for (int i = 0 ; i < getFragmentManager().getBackStackEntryCount() ; ++i)
            getFragmentManager().popBackStack();

        displayView(item.getItemId(), null);
        return true;
    }

    //Display the fragment with the given id
    public void displayView(int viewId, Bundle bundle) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);
        Boolean showSearch = false, animate = false;

        switch (viewId) {
            case R.id.nav_restaurants:
                fragment = new RestosFragment();
                title  = getString(R.string.titleRestos);
                showSearch = true;
                break;
            case R.id.nav_map:
                fragment = new CarteFragment();
                title  = getString(R.string.titleCarte);
                break;
            case R.id.nav_preferences:
                fragment = new PrefFragment();
                title  = getString(R.string.titlePref);
                break;
            case R.id.restos_pager:
                fragment = new RestoFragmentPager();
                title  = getString(R.string.titleRestoPager);
                animate = true;
                break;
            case R.id.route_fragment:
                fragment = new RouteFragment();
                title  = getString(R.string.titleRouteFragment);
                animate = true;
                break;
        }

        if (fragment != null) {
            fragment.setArguments(bundle);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (animate)
                ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
            else
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            if(mFragmentStack.size() > 0)//If it is not the first one fragment.
                ft.hide(getFragmentManager().findFragmentByTag(mFragmentStack.peek().first));
            ft.add(R.id.content_frame, fragment, title);
            ft.addToBackStack(title);
            mFragmentStack.add(Pair.create(title, showSearch));
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            if (mMenuActionBar != null)
                mMenuActionBar.findItem(R.id.search).setVisible(showSearch);
            setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }


    /**
     * Set the title of the action bar.
     * @param newTitle The new title from the strings resources file.
     */
    public void setTitle(int newTitle)
    {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(newTitle);
    }
}
