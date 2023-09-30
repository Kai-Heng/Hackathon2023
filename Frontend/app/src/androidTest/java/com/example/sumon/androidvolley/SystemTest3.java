package com.example.sumon.androidvolley;

import static org.hamcrest.CoreMatchers.startsWith;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest   // large execution time
public class SystemTest3 {
    private static final int SIMULATED_DELAY_MS = 500;

    @Rule   // needed to launch the activity
    public ActivityTestRule<EventActivity> activityRule = new ActivityTestRule<>(EventActivity.class);
   // public ActivityTestRule<MainActivity> activityRule1 = new ActivityTestRule<>(MainActivity.class);

    /**
     * Start the server and run this test
     */
    @Test
    public void checkWebSocketEventList(){
        String testEventName = "SystemTest3";
        String testEventTime = "5/3";
        String testEventLocation = "ISU";
        String result = "Event:  SystemTest3\nTime:  5/3\nLocation:  ISU\n\n";

        //Espresso.onView(ViewMatchers.withId(R.id.createEventBtn)).perform(ViewActions.click());

        // Put thread to sleep to allow volley to handle the request


        Espresso.onView(ViewMatchers.withId(R.id.eventNameTextField))
                .perform(ViewActions.typeText(testEventName), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.eventTimeTextField)).perform(ViewActions.typeText(testEventTime), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.eventLocationTextField)).perform(ViewActions.typeText(testEventLocation), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.createEventBtn)).perform(ViewActions.click());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        // Verify that volley returned the correct value
        Espresso.onView(ViewMatchers.withId(R.id.eventList)).check(ViewAssertions.matches(ViewMatchers.withText(startsWith(result))));

    }
}
