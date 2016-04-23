package com.example.mobilization.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mobilization.BaseTest;
import com.example.mobilization.model.data.Artist;
import com.example.mobilization.presenter.ArtistListPresenter;
import com.example.mobilization.view.activity.MainActivity;
import com.example.mobilization.view.adapter.ArtistAdapter;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainActivityTest extends BaseTest {

    @Inject
    protected ArtistListPresenter mArtistListPresenter;

    @Inject
    List<Artist> mArtistList;

    private ArtistAdapter adapter;

    private MainActivity mActivity;
    private Bundle bundle;
    private TextView mNoData;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        bundle = new Bundle();
        mActivity = new MainActivity();
        adapter = mock(ArtistAdapter.class);
        mNoData = mock(TextView.class);
        mArtistListPresenter.onCreate(bundle, mActivity);
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(mActivity);
    }

    @Test
    public void testHaveNoData() throws Exception {
        assertNotNull(mNoData);
    }

    @Test
    public void testShowNoData() throws Exception {
        mActivity.showEmptyList();
        verify(mNoData).setVisibility(View.VISIBLE);
    }

    @Test
    public void testHideNoData() throws Exception {
        mActivity.showList(mArtistList);
        verify(mNoData).setVisibility(View.GONE);
    }

    @Test
    public void testLoadList() {
        mActivity.showList(mArtistList);
    }
}
