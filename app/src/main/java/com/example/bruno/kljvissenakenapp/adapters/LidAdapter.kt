package com.example.bruno.kljvissenakenapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bruno.kljvissenakenapp.R
import com.example.bruno.kljvissenakenapp.models.Lid
import kotlinx.android.synthetic.main.lid_item.view.*

class LidAdapter:RecyclerView.Adapter<LidViewHolder>() {
    private var leden: List<Lid> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LidViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.lid_item,parent,false)
        return LidViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return leden.size
    }

    override fun onBindViewHolder(holder: LidViewHolder, position: Int) {
        val lid:Lid = leden.get(position)
        holder.view.naamTxt.text = lid.naam
        holder.view.bedragTxt.text = String.format("%.2f €", lid.teBetalen)
        holder.view.omschrijvingTxt.text = lid.omschrijving
    }

    fun setLeden(leden:List<Lid>){
        this.leden = leden
        notifyDataSetChanged()
    }

    //Dient voor slide to delete, hiervoor hebben we de position nodig
    fun getLidAt(pos:Int):Lid{
        return leden.get(pos)
    }


}

class LidViewHolder(val view:View): RecyclerView.ViewHolder(view){

}