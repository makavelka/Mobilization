package com.example.mobilization;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.mobilization.view.activity.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mobilization.OrientationChangeAction.orientationLandscape;
import static junit.framework.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class UiTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    private MainActivity mActivity = null;

    @Before
    public void setActivity() {
        mActivity = mActivityRule.getActivity();
    }

    @Test
    public void testList() throws InterruptedException {
        //Проверка активити на null
        assertNotNull(mActivity);
        //Поворот экрана
        //Скролл к элекменту
        onView(withId(R.id.artistList_recyclerView_mainActivity)).perform(
                RecyclerViewActions.scrollToPosition(54));
        onItemClick();
        //Проверка данных, выведеных на второй экран
        onView(withText("Tove Lo")).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(isRoot()).perform(orientationLandscape());
        onView(withId(R.id.artistList_recyclerView_mainActivity)).perform(
                RecyclerViewActions.scrollToPosition(128));
        onItemClick();
        onView(withText("Tove Lo")).check(matches(isDisplayed()));
        onView(withId(R.id.refresh_fab_mainActivity)).check(matches(isDisplayed())).perform(click());
    }

    /**
     * Нажатие на первый элемент в списке
     */
    private void onItemClick() {
        onView(withId(R.id.artistList_recyclerView_mainActivity)).perform(
                RecyclerViewActions.scrollToPosition(0));
        onView(withId(R.id.artistList_recyclerView_mainActivity)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }
}
