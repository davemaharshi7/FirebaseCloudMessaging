package com.md.firebase_cloud_messaging;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ActivityInputOutput {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            Notification.class);

    @Test
    public void useAppContext() {

        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.md.firebase_cloud_messaging", appContext.getPackageName());
    }
    @Test
    public void activityLaunch(){
        onView(withId(R.id.buttonN)).perform(click());
        onView(withId(R.id.buttonUpdate)).check(matches(isDisplayed()));

    }


}
