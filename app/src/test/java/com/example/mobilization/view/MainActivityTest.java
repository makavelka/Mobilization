package com.example.mobilization.view;

import android.os.Bundle;

import com.example.mobilization.BaseTest;
import com.example.mobilization.model.data.Artist;
import com.example.mobilization.presenter.ArtistListPresenter;
import com.example.mobilization.view.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import java.util.List;

import javax.inject.Inject;

import static junit.framework.Assert.assertNotNull;

public class MainActivityTest extends BaseTest {

    @Inject
    protected ArtistListPresenter mArtistListPresenter;

    @Inject
    List<Artist> mArtistList;

    private MainActivity mActivity;
    private ActivityController controller;
    private Bundle bundle;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
//        controller = Robolectric.buildActivity(MainActivity.class).create();
        bundle = new Bundle();
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        mActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        mArtistListPresenter.onCreate(bundle, mActivity);
        assertNotNull(mActivity);
    }
}
