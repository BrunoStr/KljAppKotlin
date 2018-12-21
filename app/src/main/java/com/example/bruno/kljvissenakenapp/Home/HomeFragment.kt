package com.example.bruno.kljvissenakenapp.Home

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.example.bruno.kljvissenakenapp.MainActivity
import com.example.bruno.kljvissenakenapp.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.map
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class HomeFragment:Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.home_title)
        setHasOptionsMenu(true)
        return inflater?.inflate(R.layout.fragment_home, null)
    }

    //Deze methode is het equivalent van de OnCreate() methode in een activity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Gegevens ophalen van de API

        doAsync {
            FuelManager.instance.basePath = "https://api.darksky.net/forecast/15c85cb1f7684eaf136aeda46d68f9bf/50.835470,4.910570";
            Fuel.get("?lang=nl&units=si&exclude=minutely,%20hourly,%20alerts,%20flags,%20daily")
                .responseJson { request, response, result ->

                println("+++++++++++++++DATA WERD OPGEHAALD++++++++++++++++++")

                val tussenObject = Klaxon().parse<TussenObject>(result.get().content)
                val weer = tussenObject!!.currently

                weerDescription.text = weer.summary
                weerTemp.text = "Temperatuur: ${weer.temperature.toInt()} °C"
                weerLuchtvocht.text = "Luchtvochtigheid: ${((weer.humidity)*100).toInt()} %"

                val img = weer.icon.replace("-","_",true)
                val iconImg = resources.getIdentifier(img, "drawable", activity?.packageName)
                weerIcon.setImageResource(iconImg)



                weerLinearLayout.visibility = View.VISIBLE
                weerSpinner.visibility = View.GONE
            }

        }



        logoImg.setImageResource(R.drawable.logo2)

        kalenderBtn.setOnClickListener(){
            (activity as MainActivity).displaySelectedScreen(R.id.nav_calendar)
        }

        drankenBtn.setOnClickListener(){
            (activity as MainActivity).displaySelectedScreen(R.id.nav_drankenLijst)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.fragment_home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)

    }
}