package com.thewoollizard.android.spendingreview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.thewoollizard.android.spendingreview.lib.IHome;

/**
 * Created by @Brontomania on 06/06/2015.
 */
public class Home extends Base implements IHome {

    ListView mDrawerLeftList;
    String[] mDrawerListItems;
    ActionBarDrawerToggle mDrawerToggle;
    String mTitle, mDrawerTitle;
    DrawerLayout mDrawerLeftLayout;
    Toolbar mToolbarHome;

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

        mDrawerListItems=getResources().getStringArray(R.array.drawer_list_001);

        mDrawerLeftList =(ListView) findViewById(R.id.listview_drawer_left);
        mDrawerLeftList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerListItems));

        mTitle=mDrawerTitle=getTitle().toString();

        mDrawerToggle=new ActionBarDrawerToggle(this, mDrawerLeftLayout, R.string.on_open_drawer, R.string.on_close_drawer) {

            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLeftLayout.setDrawerListener(mDrawerToggle);
    }

    public void toolbarCreation(){

        mToolbarHome =(Toolbar) findViewById(R.id.toolbar_home);
        mToolbarHome.setTitle(R.string.app_name);
        mToolbarHome.setTitleTextColor(Color.WHITE);

        setSupportActionBar(mToolbarHome);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer_toggle_small);

        mToolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLeftLayout.openDrawer(Gravity.LEFT);
            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
}
