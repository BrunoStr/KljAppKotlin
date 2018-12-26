package com.example.bruno.kljvissenakenapp.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bruno.kljvissenakenapp.R
import gr.net.maroulis.library.EasySplashScreen
import kotlinx.android.synthetic.main.app_bar_main.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        val config = EasySplashScreen(this)
            .withFullScreen()
            .withTargetActivity(MainActivity::class.java)
            .withSplashTimeOut(2000)
            .withBackgroundColor(Color.parseColor("#cc3c27"))
            .withLogo(R.drawable.logo2)
            .withFooterText("Created by Bruno Stroobants ®")

        config.footerTextView.setTextColor(Color.WHITE)
        config.footerTextView.setPadding(0,0,0,100)


        val view = config.create()
        setContentView(view)
    }
}
