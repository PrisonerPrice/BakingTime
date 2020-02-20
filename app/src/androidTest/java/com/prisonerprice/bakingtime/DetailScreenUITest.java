package com.prisonerprice.bakingtime;

import android.content.Intent;
import android.util.Log;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.prisonerprice.bakingtime.DetailScreen.DetailActivity;

import org.json.JSONException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DetailScreenUITest {

    private int stepPosition;

    @Rule
    public ActivityTestRule<DetailActivity> detailActivityActivityTestRule
            = new ActivityTestRule<>(DetailActivity.class);

    @Before
    public void setup() {
        stepPosition = 2;
    }

    @Test
    public void onStepClickTest() {
        onView(withId(R.id.step_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(stepPosition,
                        click()));
        onView(withId(R.id.instruction_fl)).check(matches(isDisplayed()));
        onView(withId(R.id.play_next_fl)).check(matches(isDisplayed()));
        onView(withId(R.id.media_player_fl)).check(matches(isDisplayed()));
    }

    @Test
    public void onPrevBtnClickTest() {
        onView(withId(R.id.step_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(stepPosition,
                        click()));
        onView(withId(R.id.play_prev_btn)).perform(click());
        onView(withId(R.id.instruction_tv)).check(matches(isDisplayed()));
    }

    @Test
    public void onNextBtnClickTest() {
        onView(withId(R.id.step_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(stepPosition,
                        click()));
        onView(withId(R.id.play_next_btn)).perform(click());
        onView(withId(R.id.instruction_tv)).check(matches(isDisplayed()));
    }
}
