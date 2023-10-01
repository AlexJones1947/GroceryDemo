package com.app.grocerydemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.grocerydemo.fragments.HomeFragment;
import com.app.grocerydemo.util.CurvedBottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    CurvedBottomNavigationView customNavigation;
    private DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;
    private Menu navMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customNavigation = findViewById(R.id.customBottomBar);
        customNavigation.inflateMenu(R.menu.bottom_menu);
        customNavigation.setSelectedItemId(R.id.navigation_home);
        customNavigation.setOnNavigationItemSelectedListener(MainActivity.this);

        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

        ImageView menuSlider = findViewById(R.id.sliderr);
        menuSlider.setOnClickListener(v -> drawer.openDrawer(GravityCompat.START));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.getBackground().setColorFilter(new PorterDuffColorFilter(0x80000000, PorterDuff.Mode.MULTIPLY));
        navMenu = navigationView.getMenu();
        View header = navigationView.getHeaderView(0);

        navMenu.findItem(R.id.nav_logout).setVisible(true);
        navMenu.findItem(R.id.nav_my_profile).setVisible(true);
        navMenu.findItem(R.id.sign).setVisible(false);
        navMenu.findItem(R.id.nav_powerd).setVisible(true);

        switchFragment(new HomeFragment(), false, "HomeeeFragment");

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fm = null;
        Bundle args = new Bundle();
        if (id == R.id.sign) {
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_my_profile) {
            Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_aboutus) {
            Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_policy) {
            Toast.makeText(this, "policy", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
        }  else if (id == R.id.nav_cart) {
            Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_order) {
            Toast.makeText(this, "order", Toast.LENGTH_SHORT).show();
        }
        if (fm != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contentPanel, fm).addToBackStack(null).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void switchFragment(Fragment fragment, boolean isAddBackStack, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentPanel, fragment, tag);
        if (isAddBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }
}