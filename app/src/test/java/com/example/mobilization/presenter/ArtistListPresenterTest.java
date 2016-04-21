package com.example.mobilization.presenter;

import android.os.Bundle;

import com.example.mobilization.BaseTest;
import com.example.mobilization.model.Model;
import com.example.mobilization.model.data.Artist;
import com.example.mobilization.view.activity.MainActivity;

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

    private MainActivity mockView;
    private ArtistListPresenter artistListPresenter;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        mockView = mock(MainActivity.class);
        artistListPresenter = new ArtistListPresenter();
        doAnswer(invocation -> Observable.just(artistList))
                .when(model)
                .getArtistList();
    }

    @Test
    public void testLoadData() {
        artistListPresenter.onCreate(null, mockView);
        artistListPresenter.getData();
        artistListPresenter.onStop();
        verify(mockView).showList(artistList);
    }

    @Test
    public void testLoadNullData() {
        doAnswer(invocation -> Observable.just(null))
                .when(model)
                .getArtistList();
        artistListPresenter.getData();
        verify(mockView).showEmptyList();
    }

    @Test
    public void testOnError() {
        doAnswer(invocation -> Observable.error(new Throwable()))
                .when(model)
                .getArtistList();
        artistListPresenter.getData();
        verify(mockView).showError("ERROR");
    }

    @Test
    public void testSaveState() {
        artistListPresenter.onCreate(null, mockView);
        artistListPresenter.getData();

        Bundle bundle = Bundle.EMPTY;
        artistListPresenter.onSaveInstanceState(bundle);
        artistListPresenter.onStop();

        artistListPresenter.onCreate(bundle, mockView);

        verify(mockView, times(2)).showList(artistList);
        verify(model).getArtistList();
    }
}
