package com.example.bruno.kljvissenakenapp.fragments.Contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.bruno.kljvissenakenapp.R
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment: androidx.fragment.app.Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.contact_title)
        return inflater?.inflate(R.layout.fragment_contact, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fbLogo.setImageResource(R.drawable.fb_logo)
        instaLogo.setImageResource(R.drawable.insta_logo)
    }
}