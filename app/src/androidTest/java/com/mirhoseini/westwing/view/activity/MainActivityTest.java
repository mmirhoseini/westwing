package com.mirhoseini.westwing.view.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.TextView;

import com.mirhoseini.westwing.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Mohsen on 29/06/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction recyclerViewInteraction = onView(allOf(
                withId(R.id.recyclerView), isDisplayed()));

        RecyclerView recyclerView = (RecyclerView) activityRule.getActivity().findViewById(R.id.recyclerView);

        TextView name = (TextView) recyclerView.getChildAt(0).findViewById(R.id.name);

        String firstRowName = name.getText().toString();

        recyclerViewInteraction.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction nameInteraction = onView(
                allOf(withId(R.id.name), isDisplayed()));
        nameInteraction.check(matches(withText(firstRowName)));

    }

}
