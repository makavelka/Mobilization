package com.example.mobilization.presenter;

import android.os.Bundle;

import com.example.mobilization.BaseTest;
import com.example.mobilization.model.Model;
import com.example.mobilization.model.data.Artist;
import com.example.mobilization.view.IMainView;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ArtistListPresenterTest extends BaseTest {

    @Inject
    protected Model model;

    @Inject
    protected List<Artist> artistList;

    private IMainView mockView;
    private ArtistListPresenter mArtistListPresenter;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        mockView = mock(IMainView.class);
        mArtistListPresenter = new ArtistListPresenter(mockView);
        doAnswer(invocation -> Observable.just(artistList))
                .when(model)
                .getArtistList();
    }

    @Test
    public void testLoadData() {
        mArtistListPresenter.onCreate(null, mockView);
        mArtistListPresenter.getData();
        mArtistListPresenter.onStop();
        verify(mockView).showList(artistList);
    }

    @Test
    public void testLoadNullData() {
        doAnswer(invocation -> Observable.just(null))
                .when(model)
                .getArtistList();
        mArtistListPresenter.getData();
        verify(mockView).showEmptyList();
    }

    @Test
    public void testOnError() {
        doAnswer(invocation -> Observable.error(new Throwable()))
                .when(model)
                .getArtistList();
        mArtistListPresenter.getData();
        verify(mockView).showError("error");
    }

    @Test
    public void testSaveState() {
        mArtistListPresenter.onCreate(null, mockView);
        mArtistListPresenter.getData();
        Bundle bundle = Bundle.EMPTY;
        mArtistListPresenter.onSaveInstanceState(bundle);
        mArtistListPresenter.onStop();
        mArtistListPresenter.onCreate(bundle, mockView);
        verify(mockView, times(2)).showList(artistList);
        verify(model).getArtistList();
    }
}
