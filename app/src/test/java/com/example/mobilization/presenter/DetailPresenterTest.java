package com.example.mobilization.presenter;

import android.os.Bundle;

import com.example.mobilization.BaseTest;
import com.example.mobilization.model.Model;
import com.example.mobilization.model.data.Artist;
import com.example.mobilization.view.IDetailView;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DetailPresenterTest extends BaseTest {

    @Inject
    protected Model model;

    @Inject
    protected Artist artist;

    private IDetailView mockView;
    private DetailPresenter mDetailPresenter;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        mockView = mock(IDetailView.class);
        mDetailPresenter = new DetailPresenter(mockView, artist);
    }

    @Test
    public void testLoadData() {
        mDetailPresenter.onCreate(null, mockView);
        mDetailPresenter.showData(artist);
        mDetailPresenter.onStop();
        verify(mockView).showData(artist);
    }

//    @Test
//    public void testLoadNullData() {
//        doAnswer(invocation -> Observable.just(null))
//                .when(model)
//                .getArtistList();
//        mDetailPresenter.showData(null);
//        verify(mockView).showToast("error");
//    }

//    @Test
//    public void testOnError() {
//        doAnswer(invocation -> Observable.error(new Throwable()))
//                .when(model)
//                .getArtistList();
//        mDetailPresenter.getData();
//        verify(mockView).showError("error");
//    }

    @Test
    public void testSaveState() {
        mDetailPresenter.onCreate(null, mockView);
        Bundle bundle = Bundle.EMPTY;
        mDetailPresenter.onSaveInstanceState(bundle);
        mDetailPresenter.onStop();
        mDetailPresenter.onCreate(bundle, mockView);
        verify(mockView).showData(artist);
    }
}
