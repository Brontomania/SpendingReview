package com.thewoollizard.android.spendingreview;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.thewoollizard.android.spendingreview.lib.IHome;

/**
 * Created by @Brontomania on 06/06/2015.
 */
public class Home extends Base implements IHome, View.OnClickListener {

    ListView mDrawerLeftList;
    ActionBarDrawerToggle mDrawerToggle;
    String mTitle, mDrawerTitle;
    DrawerLayout mDrawerLeftLayout;
    ImageButton toolbarBtn;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);
        drawerLeftCreation();

    }

    //Left Drawer creation
    protected void drawerLeftCreation(){
        mDrawerLeftLayout =(DrawerLayout) findViewById(R.id.drawer_left);
        mDrawerLeftLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerLeftList =(ListView) findViewById(R.id.listview_drawer_left);

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

        ImageButton toolbarBtn=(ImageButton) findViewById(R.id.toolbar_drawer_btn);
        toolbarBtn.setOnClickListener(this);

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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_drawer_btn:
                mDrawerLeftLayout.openDrawer(Gravity.LEFT);
                break;
            default:break;
        }
    }


}
