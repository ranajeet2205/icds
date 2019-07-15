package com.ranajeetbarik2205.icds;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.ranajeetbarik2205.icds.activity.LoginActivity;
import com.ranajeetbarik2205.icds.fragment.BNFFragment;
import com.ranajeetbarik2205.icds.util.AppConstants;
import com.ranajeetbarik2205.icds.util.SharedPrefManager;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.Menu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavController navController;
    int currentDestination;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        sharedPrefManager = new SharedPrefManager(this);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        currentDestination = navController.getCurrentDestination().getId();
        if (id == R.id.nav_home && R.id.homeFragment != currentDestination) {
            navController.navigate(R.id.homeFragment);

        } else if (id == R.id.nav_mpr && R.id.mprListFragment != currentDestination) {
            navController.navigate(R.id.mprListFragment);
        } else if (id == R.id.nav_immunize && R.id.immunizListFragment != currentDestination) {

            navController.navigate(R.id.immunizListFragment);
        } else if (id == R.id.nav_thr && R.id.thrListFragment != currentDestination) {

            navController.navigate(R.id.thrListFragment);
        } else if (id == R.id.nav_bnf && R.id.bnfListFragment != currentDestination) {
            navController.navigate(R.id.bnfListFragment);
        } else if (id == R.id.nav_about && R.id.aboutFragment != currentDestination) {
            navController.navigate(R.id.aboutFragment);

        } else if (id == R.id.nav_logout) {
            sharedPrefManager.setBool(AppConstants.IS_FIRST_TIME_LOGIN,false);
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
