package com.example.bruno.kljvissenakenapp.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.net.sip.SipAudioCall
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import com.example.bruno.kljvissenakenapp.R
import com.example.bruno.kljvissenakenapp.activities.MainActivity
import kotlinx.android.synthetic.main.dialog_add_lid.*

class AddLidDialog: DialogFragment() {

    lateinit var dialogListener: SipAudioCall.Listener

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


                   println("+++++++++++")
                   println("${naam},${bedrag},${omschrijving}")
                   println("+++++++++++")



               }
           })

        return builder.create()
    }

    /*
    override fun onAttach(context: Context) {
        super.onAttach(context)

        dialogListener = context as

    }
    */

}