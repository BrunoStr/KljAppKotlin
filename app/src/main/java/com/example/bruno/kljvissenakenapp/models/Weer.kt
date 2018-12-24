package com.example.bruno.kljvissenakenapp.models

class Weer(var time:Long, var summary:String, var icon:String, var precipIntensity:Double, var precipProbability:Double,
           var temperature:Double, var apparentTemperature:Double, var dewPoint:Double, var humidity:Double,
           var pressure:Double, var windSpeed:Double, var windGust:Double, var windBearing:Int, var cloudCover:Double, var uvIndex:Int,
           var visibility: Double, var ozone: Double) {

    lateinit var precipType:String

    constructor(time:Long, summary:String, icon:String, precipIntensity:Double, precipProbability:Double,
                precipType:String, temperature:Double, apparentTemperature:Double, dewPoint:Double, humidity:Double,
                pressure:Double, windSpeed:Double, windGust:Double, windBearing:Int, cloudCover:Double, uvIndex:Int,
                visibility: Double, ozone: Double):this(time,summary,icon,precipIntensity, precipProbability, temperature, apparentTemperature, dewPoint, humidity, pressure, windSpeed, windGust, windBearing, cloudCover, uvIndex, visibility, ozone){
        this.precipType = precipType
    }



}

class TussenObject(var latitude :Double, var longitude:Double, var timezone:String, var currently: Weer, var offset:Int){

}