package com.thewoollizard.android.spendingreview;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.thewoollizard.android.spendingreview.lib.IHome;

/**
 * Created by @Brontomania on 06/06/2015.
 */
public class Home extends Base implements IHome {

    ListView mDrawerLeftList;
    ActionBarDrawerToggle mDrawerToggle;
    String mTitle, mDrawerTitle;
    DrawerLayout mDrawerLeftLayout;
    Toolbar mToolBar;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);
        drawerLeftCreation();
        toolbarCreation();

    }

    //Left Drawer creation
    protected void drawerLeftCreation(){
        mDrawerLeftLayout =(DrawerLayout) findViewById(R.id.drawer_left);
        mDrawerLeftLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerLeftList =(ListView) findViewById(R.id.listview_drawer_left);

        mTitle=mDrawerTitle=getTitle().toString();

        mDrawerToggle=new ActionBarDrawerToggle(this, mDrawerLeftLayout, R.string.on_open_drawer, R.string.on_close_drawer) {

            public void onDrawerClosed(View view) {
                mToolBar.setTitle("Drawer Closed");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                mToolBar.setTitle("Drawer Open");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLeftLayout.setDrawerListener(mDrawerToggle);
    }

    //TODO: Togliere ToolBar e inserire un Linear Layout con bottoni e icone (+ gestibile)
    private void toolbarCreation() {
        mToolBar = (Toolbar) findViewById(R.id.home_toolbar);
        mToolBar.setTitle(R.string.app_name);
        mToolBar.setLogo(R.drawable.ic_drawer_toggle);
        mToolBar.setContentInsetsAbsolute(0,0);
        mToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawerLeftLayout.openDrawer(Gravity.LEFT);

            }
        });

        setSupportActionBar(mToolBar);
    }

    @Override
    public void showLastItem() {

    }

    @Override
    public void showTotIn() {

    }

    @Override
    public void showTotOut() {

    }

    //Manage drawer action requests
    private class LeftDrawerListener implements DrawerLayout.DrawerListener{

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(View drawerView) {

        }

        @Override
        public void onDrawerClosed(View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    }
}
