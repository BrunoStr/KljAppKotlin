package com.example.bruno.kljvissenakenapp.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.bruno.kljvissenakenapp.R
import com.example.bruno.kljvissenakenapp.models.Lid
import java.lang.ClassCastException


class AddLidDialog: DialogFragment() {

    lateinit var dialogListener: LidDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       val builder =  AlertDialog.Builder(activity!!.themedContext)

       val inflater = activity!!.layoutInflater
       val view = inflater.inflate(R.layout.dialog_add_lid, null)


       builder.setView(view)
           .setTitle("Voeg nieuwe Schuld toe")
           .setNegativeButton("Cancel", object : DialogInterface.OnClickListener{
               override fun onClick(dialog: DialogInterface?, which: Int) {

               }

           })
           .setPositiveButton("Opslaan", object : DialogInterface.OnClickListener {
               override fun onClick(dialog: DialogInterface?, which: Int) {

                    val naam = view.findViewById<EditText>(R.id.editNameTxt).text.toString()
                    val bedrag = view.findViewById<EditText>(R.id.editBedragTxt).text.toString()
                    val omschrijving = view.findViewById<EditText>(R.id.editOmschrijvingTxt).text.toString()

                   if(naam.isNotEmpty() && bedrag.isNotEmpty() && omschrijving.isNotEmpty()){
                       dialogListener.applyTexts(naam, bedrag, omschrijving)
                   }else{
                       Toast.makeText(activity!!.applicationContext, "Ongeldige schuld werd niet opgeslagen",Toast.LENGTH_SHORT).show()
                   }

                   println("+++++++++++")
                   println("${naam},${bedrag},${omschrijving}")
                   println("+++++++++++")

               }
           })

        return builder.create()
    }

    /**
     * Deze methode zal de waarden van de dialog meegeven aan de fragment
     */
    interface LidDialogListener{
        fun applyTexts(naam:String, bedrag:String, omschrijving:String)
    }


    override fun onAttach(context: Context) {

        try {
            dialogListener = targetFragment as LidDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + "must implement LidDialogListenerZZZ")
        }

        super.onAttach(context)



    }


}