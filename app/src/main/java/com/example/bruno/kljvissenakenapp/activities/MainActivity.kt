package com.example.bruno.kljvissenakenapp.activities

import android.content.Context
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.bruno.kljvissenakenapp.fragments.Contact.ContactFragment
import com.example.bruno.kljvissenakenapp.fragments.Dranken.DrankenlijstFragment
import com.example.bruno.kljvissenakenapp.fragments.Home.HomeFragment
import com.example.bruno.kljvissenakenapp.fragments.Kalender.CalendarFragment
import com.example.bruno.kljvissenakenapp.R
import com.example.bruno.kljvissenakenapp.dialog.AddLidDialog
import com.example.bruno.kljvissenakenapp.dialog.AddLidDialog.LidDialogListener
import com.example.bruno.kljvissenakenapp.ui.LidViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var currentFragment:Int = 0

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
        nav_view.setCheckedItem(R.id.nav_home)

        if(savedInstanceState != null){
            currentFragment = savedInstanceState.getInt("selectedFragment")
        }

        //Standaard naar HomeFragment bij opstarten anders naar currentFragment
        if (currentFragment==0){
            displaySelectedScreen(-1)
        }else{
            displaySelectedScreen(currentFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        val sharedPrefs = getSharedPreferences("weerPref", Context.MODE_PRIVATE)
        val edit = sharedPrefs?.edit()
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


    /**
     * Methode om navigation drawer clicks te behandelen
     * @param item Geselecteerde menuItem wordt meegegeven
     *
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        displaySelectedScreen(item.itemId)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * Methode om geselecteerde fragment weer te geven
     * @param id Het id van het geselecteerde fragment
     */
    fun displaySelectedScreen(id: Int){
        nav_view.setCheckedItem(id)
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

        currentFragment = id

        //Hier zal de correcte fragment gepusht worden naar de fragmentLayout in content_main.xml
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, fragment)
            .commit()

    }

    //De currentFragment geven we mee zodat bij orientation change de activity weet welke fragment geselecteerd is
    /**
     * Methode om currentFragment op te slaan. Zo kan bij configuration change de juiste fragment weergegeven worden
     * @param outState De bundle waarin de waarden opgeslagen worden
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("selectedFragment", currentFragment)

    }

    //In de onCreate() methode wordt deze bundle ook doorgegeven, verschil is dat deze methode enkel opgeroepen wordt
    //wanneer de bundle niet null is. We moeten hier dus niet checken op null values en in de onStart zouden we dit
    //wel moeten doen
    /*
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState!!.getInt("selectedFragment")
    }
    */

}
