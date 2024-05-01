package com.hfad.whattowatch
import org.junit.Test

import org.junit.Assert.*


import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.runner.RunWith


class UnitTest {
    @Before
    fun setUp() {
        ActivityScenarioRule(MainActivity::class.java).launchActivity()
    }
    @Test
    fun testNavigationToSearchFragment() {
        // Open drawer (implementation depends on your drawer library)
        onView(withId(R.id.drawer_layout)).perform(openDrawer())

        // Click on Search item
        onView(withId(R.id.search_field)).perform(click())

        // Assert Search fragment is displayed
        onView(withId(R.id.searchMoviesView)).check(matches(isDisplayed()))
    }

    @Test
    fun testSearchFunctionality() {
        // Launch Search fragment
        onView(withId(R.id.search_field)).perform(typeText("Friends"))
        // Simulate search
        onView(withId(R.id.searchButton)).perform(pressImeActionButton())

        // Assert results displayed in recycler view
        onView(withId(R.id.searchMoviesView)).check(matches(isDisplayed()))

        // Repeat search for "Ava"
        onView(withId(R.id.search_field)).perform(clearText())
        onView(withId(R.id.search_field)).perform(typeText("Ava"))
        onView(withId(R.id.search_field)).perform(pressImeActionButton())
        // Assert results updated for "Ava"
        onView(withId(R.id.searchMoviesView)).check(matches(isDisplayed()))
    }

    @Test
    fun testSearchItemClickToDetailsFragment() {
        // Launch Search fragment and perform search

        // Simulate click on a search result item
        onView(withId(R.id.searchMoviesView)).perform(actionOnLastChildView(click()))

        // Assert Detail fragment is displayed
        onView(withId(R.id.detailFragment)).check(matches(isDisplayed()))

        // Assert details like Title, type, year are displayed (use data matchers)
        onView(withId(R.id.tvMediaTitle)).check(matches(withText("Friends")))
        // ... similar assertions for other details
    }
    @Test
    fun testFavoriteButtonClick() {
        // Launch Detail fragment

        // Find Favorite button
        onView(withId(R.id.favoriteButton)).check(matches(isDisplayed()))

        // Simulate click on Favorite button
        onView(withId(R.id.favoriteButton)).perform(click())
    }
    @Test
    fun testFavoritesFragmentLaunch() {
        // Launch Favorites fragment

        // Assert Favorites fragment is displayed
        onView(withId(R.id.favoritesFragment)).check(matches(isDisplayed()))
    }
    @Test
    fun testSeeWhereToWatchButtonClick() {
        // Launch Detail fragment

        // Find "See Where to Watch" button
        onView(withId(R.id.whereToWatchButton)).check(matches(isDisplayed()))

        // Simulate click on the button
        onView(withId(R.id.whereToWatchButton)).perform(click())
    }


}