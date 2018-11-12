package com.example.bruno.kljvissenakenapp

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*
import java.time.ZoneId.systemDefault



class CalendarFragment:Fragment(){

    lateinit var calendar: CompactCalendarView
    val dateFormatMonth = SimpleDateFormat("MMMM- yyyy", Locale.getDefault())

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
        calendar.setEventIndicatorStyle(2)

        //Set up an event in calendar
        //TimeinMillis: datum die omgevormd is in miliSeconden
        var event1 = Event(Color.BLUE,1542485810000,"Vandaag is de eerste KLJ activiteit!")
        calendar.addEvent(event1)

        var event2 = Event(Color.BLUE,1542572210000, "De tweede activiteit gaat van start vandaag!")
        calendar.addEvent(event2)

        compactCalendarView.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                val events = compactCalendarView.getEvents(dateClicked)

                if(events.isEmpty()){
                    activiteitTxt.text = "Op deze dag vindt er geen activiteit plaats"
                }
                else{
                    for (item in events){
                        activiteitTxt.text = item.data.toString()
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
}