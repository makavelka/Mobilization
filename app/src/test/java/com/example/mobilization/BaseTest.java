package com.example.mobilization;

import com.example.mobilization.di.App;
import com.example.mobilization.di.TestComponent;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(application = TestApp.class,
        constants = BuildConfig.class,
        sdk = 21)
@Ignore
public class BaseTest {

    public TestComponent component;
    public TestUtils testUtils;

    @Before
    public void setUp() throws Exception {
        component = (TestComponent) App.getComponent();
        testUtils = new TestUtils();
    }

}