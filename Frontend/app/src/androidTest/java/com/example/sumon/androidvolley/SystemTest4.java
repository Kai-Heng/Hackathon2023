package com.example.sumon.androidvolley;

import static org.hamcrest.CoreMatchers.endsWith;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.KeyEvent;

import com.example.sumon.androidvolley.utils.Const;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest   // large execution time
public class SystemTest4 {
    private static final int SIMULATED_DELAY_MS = 500;

    @Rule   // needed to launch the activity
    public ActivityTestRule<MessageActivity> activityRule = new ActivityTestRule<>(MessageActivity.class);
    // public ActivityTestRule<MainActivity> activityRule1 = new ActivityTestRule<>(MainActivity.class);

    /**
     * Start the server and run this test
     */
    @Test
    public void checkWebSocketMessage(){
        Const.USER_NAME = "SystemTester";
        String message = "I am here to test the system.";
        String result = "SystemTester: I am here to test the system.\n";

        //Espresso.onView(ViewMatchers.withId(R.id.createEventBtn)).perform(ViewActions.click());

        // Put thread to sleep to allow volley to handle the request


        Espresso.onView(ViewMatchers.withId(R.id.messageBox))
                .perform(ViewActions.typeText(message), ViewActions.pressKey(KeyEvent.KEYCODE_ENTER));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        // Verify that volley returned the correct value
        Espresso.onView(ViewMatchers.withId(R.id.messageHistory)).check(ViewAssertions.matches(ViewMatchers.withText(endsWith(result))));

    }
}
