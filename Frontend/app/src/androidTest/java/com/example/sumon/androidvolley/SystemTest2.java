package com.example.sumon.androidvolley;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.sumon.androidvolley.utils.Const;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest   // large execution time
public class SystemTest2 {

    private static final int SIMULATED_DELAY_MS = 500;

    @Rule   // needed to launch the activity
    public ActivityTestRule<EditProfileWindows> activityRule = new ActivityTestRule<>(EditProfileWindows.class);

    /**
     * Start the server and run this test
     */
    @Test
    public void checkUserInfo(){
        Const.USER_ID = 703;
        String resultUsername = "systemTestAcc";
        String resultEmail = "systemTestAcc@gmail.com";
        String resultPhoneNumber = "5163732343";
        String resultName = "System Tester";
        String resultInterest = "Travel,Hiking,Rural,Nomadic,Digital,";
        String resultGender = "M";

        Espresso.onView(ViewMatchers.withId(R.id.getDataButton)).perform(ViewActions.click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        // Verify that volley returned the correct value
        Espresso.onView(ViewMatchers.withId(R.id.usernameTextView))
                .check(ViewAssertions.matches(ViewMatchers.withText(endsWith(resultUsername))));
        Espresso.onView(ViewMatchers.withId(R.id.editprofilewindows_email))
                .check(ViewAssertions.matches(ViewMatchers.withText(endsWith(resultEmail))));
        Espresso.onView(ViewMatchers.withId(R.id.editprofilewindows_phoneNumber))
                .check(ViewAssertions.matches(ViewMatchers.withText(endsWith(resultPhoneNumber))));
        Espresso.onView(ViewMatchers.withId(R.id.editprofilewindows_name))
                .check(ViewAssertions.matches(ViewMatchers.withText(endsWith(resultName))));
        Espresso.onView(ViewMatchers.withId(R.id.editprofilewindows_interest))
                .check(ViewAssertions.matches(ViewMatchers.withText(startsWith(resultInterest))));
        Espresso.onView(ViewMatchers.withId(R.id.editprofilewindows_gender))
                .check(ViewAssertions.matches(ViewMatchers.withText(endsWith(resultGender))));

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
