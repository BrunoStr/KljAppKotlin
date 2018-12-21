package com.example.bruno.kljvissenakenapp.Home

class Weer(var time:Long, var summary:String, var icon:String, var precipIntensity:Double, var precipProbability:Double,
           var precipType:String, var temperature:Double, var apparentTemperature:Double, var dewPoint:Double, var humidity:Double,
           var pressure:Double, var windSpeed:Double, var windGust:Double, var windBearing:Int, var cloudCover:Double, var uvIndex:Int,
           var visibility: Double, var ozone: Double) {

}

class TussenObject(var latitude :Double, var longitude:Double, var timezone:String, var currently:Weer, var offset:Int){

}