package com.example.bruno.kljvissenakenapp.fragments.Dranken

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.example.bruno.kljvissenakenapp.R
import com.example.bruno.kljvissenakenapp.R.id.saveLid
import kotlinx.android.synthetic.main.fragment_add_lid.*


class AddLid : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        activity!!.actionBar.setHomeAsUpIndicator(R.drawable.close_white_24dp) as AppCompatActivity
        activity!!.actionBar.setTitle("Voeg schulden toe")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_lid, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_lid_menu, menu)

    }

    fun saveLid(){
        val naam = naamEditTxt.text
        val bedrag = bedragEditTxt.text
        val omschrijving = omschrijvingEditTxt.text

        if(naam.trim().isNotEmpty() && bedrag.trim().isNotEmpty() && omschrijving.trim().isNotEmpty()){

        }
        else{
            Toast.makeText(activity!!.applicationContext,"Er werden geen geldige waarden opgegeven", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.saveLid -> {
                saveLid()
                return true
            }
            else -> return super.onOptionsItemSelected(item)

        }

    }


}
