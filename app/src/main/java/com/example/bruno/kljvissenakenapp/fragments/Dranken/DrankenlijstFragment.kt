package com.example.bruno.kljvissenakenapp.fragments.Dranken

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bruno.kljvissenakenapp.R
import com.example.bruno.kljvissenakenapp.adapters.LidAdapter
import com.example.bruno.kljvissenakenapp.dialog.AddLidDialog
import com.example.bruno.kljvissenakenapp.models.Lid
import com.example.bruno.kljvissenakenapp.ui.LidViewModel
import kotlinx.android.synthetic.main.fragment_drankenlijst.*

class DrankenlijstFragment: androidx.fragment.app.Fragment(), AddLidDialog.LidDialogListener {

    lateinit var lidViewModel: LidViewModel
    var teller = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.drank_title)
        setHasOptionsMenu(true)
        return inflater?.inflate(R.layout.fragment_drankenlijst, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view!!.findViewById(R.id.ledenRecycler) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        //setFixedSize zorgt voor hogere efficiëntie
        recycler.setHasFixedSize(true)

        val adapter = LidAdapter()
        recycler.adapter = adapter

        lidViewModel = ViewModelProviders.of(activity!!).get(LidViewModel::class.java)
        //observe() is een liveData method
        //Zet lifecycleOwner (this) --> fragment wordt enkel geupdate wanneer het in de foreground is
        lidViewModel.getAll().observe(this, Observer {leden ->

            //Dit wordt opgeroepen wanneer er changes plaatsvinden en de fragment is in de foreground
            adapter.setLeden(leden)

        })

        // dragDirs:0 zegt dat we geen drag & drop gebruiken
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //GetLidAt() toegevoegd aan de adapter class
                //viewholder heeft de position van de gewenste cell
                lidViewModel.delete(adapter.getLidAt(viewHolder.adapterPosition))
                Toast.makeText(activity!!.applicationContext, "Schuld werd verwijderd", Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(recycler)


        addLidBtn.setOnClickListener {
            println("Floating button clicked...")
            openDialog()
        }


    }

    fun openDialog(){

        val dialog = AddLidDialog()
        dialog.show(activity!!.supportFragmentManager,"lid dialog")
        dialog.setTargetFragment(this ,1)

    }

    //In deze methode krijgt de fragment de strings vanuit de dialog en kan ermee werken
    override fun applyTexts(naam: String, bedrag: String, omschrijving: String) {
        println("IN DE FRAGMENT")
        val bedragDouble = bedrag.toDouble()
        val lid = Lid(0,naam,bedragDouble,omschrijving)
        lidViewModel.insert(lid)
        Toast.makeText(activity!!.applicationContext, "Schuld werd opgeslagen", Toast.LENGTH_SHORT).show()
        teller++
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuInflater = activity!!.menuInflater
        menuInflater.inflate(R.menu.delete_leden_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteAllBtn -> {
                lidViewModel.deleteAll()
                Toast.makeText(activity!!.applicationContext,"Alle schulden verwijderd", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}