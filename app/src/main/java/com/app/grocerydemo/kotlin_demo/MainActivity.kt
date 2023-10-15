package com.app.grocerydemo.kotlin_demo

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.os.Bundle
import com.app.grocerydemo.R
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.graphics.PorterDuffColorFilter
import android.graphics.PorterDuff
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.app.grocerydemo.kotlin_demo.fragments.HomeFragment
import com.app.grocerydemo.kotlin_demo.util.CurvedBottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    var customNavigation: CurvedBottomNavigationView? = null
    private var drawer: DrawerLayout? = null
    var navigationView: NavigationView? = null
    var toolbar: Toolbar? = null
    private var navMenu: Menu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customNavigation = findViewById(R.id.customBottomBar)
        customNavigation!!.inflateMenu(R.menu.bottom_menu)
        customNavigation!!.setSelectedItemId(R.id.navigation_home)
        customNavigation!!.setOnNavigationItemSelectedListener(this@MainActivity)
        navigationView = findViewById(R.id.nav_view)
        drawer = findViewById(R.id.drawer_layout)
        val menuSlider = findViewById<ImageView>(R.id.sliderr)
        menuSlider.setOnClickListener { v: View? -> drawer!!.openDrawer(GravityCompat.START) }
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer!!.addDrawerListener(toggle)
        toggle.syncState()
        navigationView!!.getBackground().colorFilter =
            PorterDuffColorFilter(-0x80000000, PorterDuff.Mode.MULTIPLY)
        navMenu = navigationView!!.getMenu()
        val header = navigationView!!.getHeaderView(0)
        navMenu!!.findItem(R.id.nav_logout).isVisible = true
        navMenu!!.findItem(R.id.nav_my_profile).isVisible = true
        navMenu!!.findItem(R.id.sign).isVisible = false
        navMenu!!.findItem(R.id.nav_powerd).isVisible = true
        switchFragment(HomeFragment(), false, "HomeeeFragment")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val fm: Fragment? = null
        val args = Bundle()
        if (id == R.id.sign) {
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_my_profile) {
            Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_aboutus) {
            Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_policy) {
            Toast.makeText(this, "policy", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "share", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_cart) {
            Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_order) {
            Toast.makeText(this, "order", Toast.LENGTH_SHORT).show()
        }
        if (fm != null) {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.contentPanel, fm).addToBackStack(null)
                .commit()
        }
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }

    fun switchFragment(fragment: Fragment?, isAddBackStack: Boolean, tag: String?) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentPanel, fragment!!, tag)
        if (isAddBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }
}