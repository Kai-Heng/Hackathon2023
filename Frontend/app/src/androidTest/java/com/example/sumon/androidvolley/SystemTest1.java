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
public class SystemTest1 {

    private static final int SIMULATED_DELAY_MS = 500;

    @Rule   // needed to launch the activity
    public ActivityTestRule<LoginPageActivity> activityRule = new ActivityTestRule<>(LoginPageActivity.class);

    /**
     * Start the server and run this test
     */
    @Test
    public void checkEventInfo(){
        String loginUsername = "postman";
        String loginPassword = "asdf1234";
        String checkLogin = "Login Successful";
        // Type in testString and send request
        Espresso.onView(ViewMatchers.withId(R.id.usernameInputText))
                .perform(ViewActions.typeText(loginUsername), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.passwordInputText)).perform(ViewActions.typeText(loginPassword), ViewActions.closeSoftKeyboard());
        //Espresso.onView(ViewMatchers.withId(R.id.eventLocationTextField)).perform(ViewActions.typeText(testEventLocation), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        // Verify that volley returned the correct value
        Espresso.onView(ViewMatchers.withId(R.id.loginDetailsWrongHint)).check(ViewAssertions.matches(ViewMatchers.withText(startsWith(checkLogin))));

    }

    /**
     * Start the server and run this test
     */
//    @Test
//    public void capitalizeString(){
//        String testString = "hello";
//        String resultString = "HELLO";
//        // Type in testString and send request
//        onView(withId(R.id.stringEntry))
//                .perform(typeText(testString), closeSoftKeyboard());
//        onView(withId(R.id.submit2)).perform(click());
//
//        // Put thread to sleep to allow volley to handle the request
//        try {
//            Thread.sleep(SIMULATED_DELAY_MS);
//        } catch (InterruptedException e) {
//        }
//        //onView(withId(R.id.text_simple)).check(matches(withText("Hello Espresso!")));
//
//        // Verify that volley returned the correct value
//        onView(withId(R.id.myTextView)).check(matches(withText(endsWith(resultString))));
//    }
}
