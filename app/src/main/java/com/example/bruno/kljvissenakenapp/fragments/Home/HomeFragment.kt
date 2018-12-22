package com.example.bruno.kljvissenakenapp.fragments.Home

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.beust.klaxon.Klaxon
import com.example.bruno.kljvissenakenapp.activities.MainActivity
import com.example.bruno.kljvissenakenapp.R
import com.example.bruno.kljvissenakenapp.network.Connection
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.doAsync

class HomeFragment:Fragment(){

    var sharedPrefs:SharedPreferences?=null

    override fun onResume() {
        super.onResume()
        println("----RESUME-----")

        var weerOmschrijving = sharedPrefs?.getString("weerOmschrijving","")

        //Checken of er Weer data in shared preferences zit, anders API aanspreken
        if(weerOmschrijving!!.isNotEmpty()){
            println("+++++Haalt uit shared Preferences++++++")
            val weerTemperatuur = sharedPrefs?.getString("weerTemperatuur","")
            val weerLuchtvochtigheid = sharedPrefs?.getString("weerLuchtvochtigheid","")
            val weerImage = sharedPrefs?.getString("weerIcon","")

            weerDescription.text = weerOmschrijving
            weerTemp.text = "Temperatuur: ${weerTemperatuur}"
            weerLuchtvocht.text = "Luchtvochtigheid: ${weerLuchtvochtigheid}"
            val iconImg = resources.getIdentifier(weerImage, "drawable", activity?.packageName)
            weerIcon.setImageResource(iconImg)

            weerLinearLayout.visibility = View.VISIBLE
            weerSpinner.visibility = View.GONE

        }
        else{

            //Checken of er internetverbinding is
            if (Connection.verifyAvailableNetwork(this.activity!!)){

                //Gegevens ophalen van de API
                doAsync {
                    println("+++++Haalt data van API++++++")
                    FuelManager.instance.basePath = "https://api.darksky.net/forecast/15c85cb1f7684eaf136aeda46d68f9bf/50.835470,4.910570";
                    Fuel.get("?lang=nl&units=si&exclude=minutely,%20hourly,%20alerts,%20flags,%20daily")
                        .responseJson { request, response, result ->

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

                            //Data opslaan in sharedPreferences
                            var edit = sharedPrefs?.edit()
                            edit?.putString("weerOmschrijving",weer.summary)
                            edit?.putString("weerTemperatuur",weer.temperature.toString())
                            edit?.putString("weerLuchtvochtigheid",weer.humidity.toString())
                            edit?.putString("weerIcon",img)
                            edit?.apply()
                        }

                }

            }else{
                println("NIET VERBONDEN MET INTERNET")
                weerSpinner.visibility = View.GONE
                noConnectionLabel.visibility = View.VISIBLE
            }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.home_title)
        setHasOptionsMenu(true)
        return inflater?.inflate(R.layout.fragment_home, null)
    }

    //Deze methode is het equivalent van de OnCreate() methode in een activity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       sharedPrefs = activity?.getSharedPreferences("weerPref", MODE_PRIVATE)

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