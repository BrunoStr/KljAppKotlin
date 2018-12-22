package com.example.bruno.kljvissenakenapp.network

import android.app.Activity
import android.content.Context
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity

// source: https://stackoverflow.com/questions/51141970/check-internet-connectivity-android-in-kotlin

class Connection {

    companion object {

        fun verifyAvailableNetwork(activity: Activity):Boolean{
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkInfo = connectivityManager.activeNetworkInfo

            return  networkInfo!=null && networkInfo.isConnected
        }

    }

}