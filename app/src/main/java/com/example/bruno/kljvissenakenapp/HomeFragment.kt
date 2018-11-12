package com.example.bruno.kljvissenakenapp

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Button
import android.widget.Toolbar
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment:Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.home_title)
        setHasOptionsMenu(true)
        return inflater?.inflate(R.layout.fragment_home, null)
    }

    //Deze methode is het equivalent van de OnCreate() methode in een activity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logoImg.setImageResource(R.drawable.logo2)

        kalenderBtn.setOnClickListener(){
            (activity as MainActivity).displaySelectedScreen(R.id.nav_calendar)
        }

        gallerijBtn.setOnClickListener(){
            (activity as MainActivity).displaySelectedScreen(R.id.nav_gallery)
        }

        survivalBtn.setOnClickListener(){
            (activity as MainActivity).displaySelectedScreen(R.id.nav_survival)
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