package com.example.bruno.kljvissenakenapp.fragments.Contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.bruno.kljvissenakenapp.R
import kotlinx.android.synthetic.main.fragment_contact.*
import android.content.Intent
import android.content.ActivityNotFoundException
import android.content.pm.ApplicationInfo
import android.net.Uri

//sources: https://stackoverflow.com/questions/10788247/opening-facebook-app-on-specified-profile-page

class ContactFragment: androidx.fragment.app.Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.contact_title)
        return inflater?.inflate(R.layout.fragment_contact, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fbLogo.setImageResource(R.drawable.fb_logo)
        instaLogo.setImageResource(R.drawable.insta_logo)

        fbBtn.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/161627963929094"))
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {

                //Als app niet op apparaat staat, open in browser
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/KLJvissenaken/")
                    )
                )
            }

        }

        instaBtn.setOnClickListener {
            try {
                val uri = Uri.parse("http://instagram.com/_u/klj_vissenaken")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.instagram.android")
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {

                //Als app niet op apparaat staat, open in browser
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://www.instagram.com/klj_vissenaken")
                    )
                )
            }

        }
    }
}