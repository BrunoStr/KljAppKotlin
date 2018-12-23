package com.example.bruno.kljvissenakenapp.fragments.Dranken

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bruno.kljvissenakenapp.R
import com.example.bruno.kljvissenakenapp.adapters.LidAdapter
import com.example.bruno.kljvissenakenapp.dialog.AddLidDialog
import com.example.bruno.kljvissenakenapp.ui.LidViewModel
import kotlinx.android.synthetic.main.dialog_add_lid.*
import kotlinx.android.synthetic.main.fragment_drankenlijst.*

class DrankenlijstFragment: androidx.fragment.app.Fragment(){

    lateinit var lidViewModel: LidViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.drank_title)
        return inflater?.inflate(R.layout.fragment_drankenlijst, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view!!.findViewById(R.id.ledenRecycler) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        recycler.setHasFixedSize(true)

        val adapter = LidAdapter()
        recycler.adapter = adapter

        lidViewModel = ViewModelProviders.of(activity!!).get(LidViewModel::class.java)
        lidViewModel.getAll().observe(this, Observer {leden ->

            adapter.setLeden(leden)

        })

        addLidBtn.setOnClickListener {
            println("Floating button clicked...")
            openDialog()
        }

    }

    fun openDialog(){

        val dialog = AddLidDialog()
        dialog.show(activity!!.supportFragmentManager,"lid dialog")

    }
}