package com.example.tiptime

import android.view.KeyEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    private var serviceCost = 50

    @Test
    fun calculate_20_percent_tip() {
        calculate_tip("${serviceCost*0.2}")
    }

    @Test
    fun calculate_18_percent_tip() {
        calculate_tip(
            "${serviceCost*0.18}",
            R.id.option_eighteen_percent)
    }

    @Test
    fun calculate_15_percent_tip() {
        calculate_tip(
            "${serviceCost*0.15}",
            R.id.option_fifteen_percent)
    }

    private fun calculate_tip(tipResult: String, percentId: Int = R.id.option_twenty_percent) {
        onView(withId(percentId))
            .perform(click())

        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("$serviceCost"))
            .perform(pressKey(KeyEvent.KEYCODE_ENTER))

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$$tipResult"))))
    }

}