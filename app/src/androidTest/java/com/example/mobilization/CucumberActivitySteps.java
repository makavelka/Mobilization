package com.example.mobilization;

import android.app.Activity;
import android.app.Instrumentation;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.test.espresso.action.ViewActions;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.example.mobilization.view.activity.MainActivity;

import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.mobilization.OrientationChangeAction.orientationLandscape;
import static com.example.mobilization.OrientationChangeAction.orientationPortrait;
import static org.hamcrest.core.IsAnything.anything;

@CucumberOptions(glue = "com.example.mobilization", format = {"pretty", "html:/sdcard/com.example.mobilization/html",
        "json:/sdcard/com.example.mobilization/jreport"},
        features = "features/test_ui.feature")
public class CucumberActivitySteps extends ActivityInstrumentationTestCase2<MainActivity> {

    @Before
    public void before() {
        Instrumentation instrumentation = getInstrumentation();
        assertNotNull(instrumentation);
        assertNotNull(getActivity());
        String testPackageName = instrumentation.getContext().getPackageName();
        String targetPackageName = instrumentation.getContext().getPackageName();
        assertEquals(testPackageName, targetPackageName);
    }

    public CucumberActivitySteps() {
        super(MainActivity.class);
    }

    @Given("^Проверяем загрузился ли экран")
    public void givenLoginTryCounter() {
        assertNotNull(getActivity());
    }

    @When("^Нажимаем кнопку назад")
    public void clickOnBackButton() {
        ViewActions.pressBack();

    }

    @When("^Клик на элемент списка")
    public void pressRecyclerViewItem() {
        onData(anything()).inAdapterView(withId(R.id.artistList_recyclerView_mainActivity))
                .atPosition(9)
                .onChildView(withId(R.id.cover_imageView_itemArtist))
                .perform(click());
    }

    @When("^Смена ориентации")
    public void changeRotation() {
        onView(isRoot()).perform(orientationLandscape());
        onView(isRoot()).perform(orientationPortrait());
    }

    private static void drawDecorViewToBitmap(Activity activity, Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        activity.getWindow().getDecorView().draw(canvas);
    }

    @After
    public void someAction(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            Bitmap bitmap;
            final Activity activity = getActivity();
            View view = getActivity().getWindow().getDecorView();
            view.setDrawingCacheEnabled(true);
            bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            scenario.embed(stream.toByteArray(), "image/png");
        }
    }


}