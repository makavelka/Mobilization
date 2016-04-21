package com.example.mobilization.view;

import android.os.Bundle;

import com.example.mobilization.BaseTest;
import com.example.mobilization.presenter.ArtistListPresenter;
import com.example.mobilization.view.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import javax.inject.Inject;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MainActivityTest extends BaseTest {

    @Inject
    ArtistListPresenter artistListPresenter;

    private MainActivity activity;
    private ActivityController controller;
    private Bundle bundle;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        controller = Robolectric.buildActivity(MainActivity.class).create();
        activity = (MainActivity) controller.get();
        bundle = new Bundle();
    }


    @Test
    public void testOnCreate() {
        controller.create();
        verify(artistListPresenter, times(2)).onCreate(null, activity); //2 times setUp() + testOnCreate()
    }

    @Test
    public void testOnStop() {
        controller.stop();
        verify(artistListPresenter).onStop();
    }

    @Test
    public void testOnSaveInstanceState() {
        controller.saveInstanceState(bundle);
        verify(artistListPresenter).onSaveInstanceState(bundle);
    }
}
