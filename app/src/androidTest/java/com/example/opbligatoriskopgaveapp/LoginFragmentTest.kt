package com.example.opbligatoriskopgaveapp

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest : TestCase(){


    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun useAppContext() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )


        val titleScenario = launchFragmentInContainer<LoginFragment>()

        titleScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withText("Please Log in")).check(matches(isDisplayed()))
        onView(withId(R.id.emailInputField))
            .perform(clearText())
            .perform(typeText("email@123.dk"))
        onView(withId(R.id.passwordInputField))
            .perform(clearText())
            .perform(typeText("123456"))
            .perform(closeSoftKeyboard())
        onView(withId(R.id.signIn)).perform(click())
        pause(2000)

        val titleScenario1 = launchFragmentInContainer<FirstFragment>()

        titleScenario1.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        pause(2000)

        onView(withId(R.id.UserName)).check(matches(withText(("Hello email@123.dk"))))

        val titleScenario2 = launchFragmentInContainer<LoginFragment>()

        titleScenario2.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.SignOutButton)).perform(click())

        pause(2000)



        // to wait for Firebase response to arrive

        /*onView(withId(R.id.textview_second)).check(matches(withText(("Hello Email@123.dk"))))

        onView(withId(R.id.SignOutButton)).perform(click())
        onView(withText("Log in")).check(matches(isDisplayed()))*/
    }

    private fun pause(millis: Long) {
        try {
            Thread.sleep(millis)
            // https://www.repeato.app/android-espresso-why-to-avoid-thread-sleep/
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }


}