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
import android.view.View;

import java.util.Stack;

public class  MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Menu mMenuActionBar;
    private Stack<Pair<String, Boolean> > mFragmentStack = new Stack<Pair<String, Boolean> >();
    private ActionBarDrawerToggle mToggle;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(mToggle);

        mToggle.setHomeAsUpIndicator(R.drawable.ic_back_arrow);

        mToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayView(R.id.nav_restaurants, null);

    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START))//If the drawer is open, we close it
            mDrawer.closeDrawer(GravityCompat.START);
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
                getFragmentManager().popBackStack();

            updateToolbarTitleAndArrow(mFragmentStack.peek().first, mFragmentStack.peek().second);
        }
        else//Else we remove the last fragment and exit the app.
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        mMenuActionBar = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displayView(item.getItemId(), null);
        return true;
    }

    //Display the fragment with the given id
    public void displayView(int viewId, Bundle bundle) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);
        Boolean showSearch = false, isTopFragment = true;

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
                isTopFragment = false;
                break;
            case R.id.resto:
                fragment = new RestoFragment();
                title  = getString(R.string.titleRestoPager);
                isTopFragment = false;
                break;
        }

        if (fragment != null) {
            fragment.setArguments(bundle);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (!isTopFragment) {
                ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
                ft.addToBackStack(title);

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                ft.hide(getFragmentManager().findFragmentByTag(mFragmentStack.peek().first));
            }
            else {
                if (mFragmentStack.size() > 0)
                    ft.hide(getFragmentManager().findFragmentByTag(mFragmentStack.peek().first));

                mFragmentStack.clear();
                for (int i = 0 ; i < getFragmentManager().getBackStackEntryCount() ; ++i)
                    getFragmentManager().popBackStack();

                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                mToggle.syncState();
            }
            ft.add(R.id.content_frame, fragment, title);
            mFragmentStack.add(Pair.create(title, showSearch));
            ft.commit();

            updateToolbarTitleAndArrow(title, showSearch);
        }

        mDrawer.closeDrawer(GravityCompat.START);
    }

    private void updateToolbarTitleAndArrow(String title, boolean showSearch)
    {
        if (mFragmentStack.size() > 1)
            mToggle.setDrawerIndicatorEnabled(false);
        else
            mToggle.setDrawerIndicatorEnabled(true);

        // set the toolbar title
        if (getSupportActionBar() != null) {
            if (mMenuActionBar != null)
                mMenuActionBar.findItem(R.id.search).setVisible(showSearch);
            setTitle(title);
        }
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
