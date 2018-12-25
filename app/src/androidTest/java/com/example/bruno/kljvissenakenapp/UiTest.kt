package com.example.bruno.kljvissenakenapp

import android.content.pm.ActivityInfo
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.bruno.kljvissenakenapp.activities.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class UiTest{

    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun clearDatabase() {
        InstrumentationRegistry.getTargetContext().deleteDatabase("Lid_database")
    }

    @Test
    fun addNewSchuldPortretMode() {
        mainActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onView(withId(R.id.main_menu_scrollview)).perform(ViewActions.swipeUp())
        onView(withId(R.id.main_drankenBtn)).perform(click())
        onView(withId(R.id.addLidBtn)).perform(click())
        onView(withId(R.id.editNameTxt)).perform(typeText("Test Portret"))
        onView(withId(R.id.editBedragTxt)).perform(typeText("20.50"))
        onView(withId(R.id.editOmschrijvingTxt)).perform(typeText("Deze omschrijving dient om te testen"))
        onView(withText("Opslaan")).perform(click())
        onView(withId(R.id.ledenRecycler)).check(matches(hasDescendant(withText("Test Portret"))))


    }

    @Test
    fun addNewSchuldLandscapeMode() {
        mainActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.main_menu_scroller)).perform(ViewActions.swipeUp())
        onView(withId(R.id.main_drankenBtn)).perform(click())
        onView(withId(R.id.addLidBtn)).perform(click())
        onView(withId(R.id.editNameTxt)).perform(typeText("Test Landscape"))
        closeSoftKeyboard()
        onView(withId(R.id.editBedragTxt)).perform(typeText("20.50"))
        closeSoftKeyboard()
        onView(withId(R.id.editOmschrijvingTxt)).perform(typeText("Deze omschrijving dient om te testen"))
        closeSoftKeyboard()
        onView(withText("Opslaan")).perform(click())
        onView(withId(R.id.ledenRecycler)).check(matches(hasDescendant(withText("Test Landscape"))))


    }

}