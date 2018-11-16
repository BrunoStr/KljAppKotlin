package com.example.bruno.kljvissenakenapp.Kalender

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bruno.kljvissenakenapp.R
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*


class CalendarFragment:Fragment(){

    lateinit var calendar: CompactCalendarView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.calendar_title)
        return inflater?.inflate(R.layout.fragment_calendar, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TextView die naam van de maand weergeeft
        var monthTxt:TextView = view.findViewById(R.id.monthTxt)
        //TextView die gegevens van de activiteit weergeeft
        var activiteitTxt:TextView = view.findViewById(R.id.activiteitTxt)

        monthTxt.text = getHuidigeMaand()
        activiteitTxt.text = ""

        //instellen calendar
        calendar = view.findViewById(R.id.compactCalendarView) as CompactCalendarView
        calendar.setEventIndicatorStyle(3)

        //Set up an event in calendar
        //TimeinMillis: datum die omgevormd is in miliSeconden   EPOCH CONVERTER.COM
        var event1 = Event(Color.parseColor("#9cc24c"),convertDateToMillis("17/11/2018"),
            Activiteit("Dropping","Maak je klaar voor een super uitdagende dropping vandaag! Lukt het jouw groep om als eerste de eindstreep de halen?",
                "+16","17/11/2018","18:00", "21:00"))
        calendar.addEvent(event1)

        var event2 = Event(Color.parseColor("#9cc24c"),convertDateToMillis("18/11/2018"),
            Activiteit("Pleinspelen", "Kom vandaag samen met je vriendjes naar de KLJ en ontdek de allerleukste spelletjes! Plezier gegarandeerd!",
                "-12","18/11/2018", "14:00", "18:00"))
        calendar.addEvent(event2)

        var event3 = Event(Color.parseColor("#9cc24c"),convertDateToMillis("18/11/2018"),
            Activiteit("HoneyMoon", "Haal je beste flirttechnieken boven en schop het tot beste koppel van de avond!",
                "+12","18/11/2018","17:00", "20:00"))
        calendar.addEvent(event3)


        compactCalendarView.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                val events = compactCalendarView.getEvents(dateClicked)

                if(events.isEmpty()){
                    activiteitTxt.text = "Op deze dag vindt er geen activiteit plaats"
                }
                else{
                    for (item in events){
                        println("DE NAAM IS "+(item.data as Activiteit).naam)
                        activiteitTxt.text = (item.data as Activiteit).naam
                    }
                }
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {

                val cal = Calendar.getInstance()
                cal.time = firstDayOfNewMonth
                var month = (cal.get(Calendar.MONTH))
                var monthName = (DateFormatSymbols().getMonths()[month])
                monthName = monthName.substring(0,1).toUpperCase()+monthName.substring(1).toLowerCase()
                val year = cal.get(Calendar.YEAR)
                monthTxt.text = "${monthName} ${year}"
            }
        })
    }

    //Met deze functie haal ik de naam van de maand op
    private fun getHuidigeMaand():String{
       val cal =  Calendar.getInstance()
       val month = cal.get(Calendar.MONTH)
       var monthName = (DateFormatSymbols().getMonths()[month])
       monthName = monthName.substring(0,1).toUpperCase()+monthName.substring(1).toLowerCase()
       val year = cal.get(Calendar.YEAR)
       return "${monthName} ${year}"
    }

    private fun convertDateToMillis(datum:String):Long{
        var sdf = SimpleDateFormat("dd/MM/yyyy")
        var date = sdf.parse(datum)
        return date.time
    }

}