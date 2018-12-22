package com.example.bruno.kljvissenakenapp.activities

import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.bruno.kljvissenakenapp.fragments.Contact.ContactFragment
import com.example.bruno.kljvissenakenapp.fragments.Dranken.DrankenlijstFragment
import com.example.bruno.kljvissenakenapp.fragments.Home.HomeFragment
import com.example.bruno.kljvissenakenapp.fragments.Kalender.CalendarFragment
import com.example.bruno.kljvissenakenapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.net.NetworkInfo
import android.net.ConnectivityManager



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //We roepen deze methode op zodat hij standaard naar HomeFragment zal gaan bij het opstarten van de MainActivity
        displaySelectedScreen(-1)
    }

    override fun onDestroy() {
        super.onDestroy()

        val sharedPrefs = getSharedPreferences("weerPref", Context.MODE_PRIVATE)
        var edit = sharedPrefs?.edit()
        edit?.remove("weerOmschrijving")
        edit?.remove("weerTemperatuur")
        edit?.remove("weerLuchtvochtigheid")
        edit?.remove("weerIcon")
        edit?.apply()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        displaySelectedScreen(item.itemId)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun displaySelectedScreen(id: Int){
        val fragment = when(id){
            R.id.nav_home -> {
                HomeFragment()
            }
            R.id.nav_calendar -> {
                CalendarFragment()
            }

            R.id.nav_drankenLijst -> {
                DrankenlijstFragment()
            }
            R.id.nav_contact -> {
                ContactFragment()
            }
            else -> {
                //By default display HomeFragment
                HomeFragment()
            }
        }

        //Hier zal de correcte fragment gepusht worden naar de fragmentLayout in content_main.xml
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, fragment)
            .commit()

    }

}
