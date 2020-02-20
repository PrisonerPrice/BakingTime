package com.prisonerprice.bakingtime;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.prisonerprice.bakingtime.MainScreen.MainActivity;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainScreenUITest {

    private int recipePosition;  // 0, 1, 2, 3

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        recipePosition = 1;
    }

    @Test
    public void clickOnRecipePictureTest() throws JSONException {
        onView(withId(R.id.main_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(recipePosition,
                        click()));
        onView(withId(R.id.step_list_fl)).check(matches(isDisplayed()));
    }

}
