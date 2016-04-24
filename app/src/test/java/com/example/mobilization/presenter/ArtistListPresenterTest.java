package com.example.mobilization.presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.mobilization.BaseTest;
import com.example.mobilization.R;
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

/**
 * Тест для презентера главного экрана
 */
public class ArtistListPresenterTest extends BaseTest {

    @Inject
    protected Model model;

    @Inject
    Context mContext;

    @Inject
    protected List<Artist> artistList;

    private IMainView mockView;
    private ArtistListPresenter mArtistListPresenter;

    /**
     * Первоначальная настройка теста и инъекция данных
     * @throws Exception
     */
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

    /**
     * Тест на правильность загрузки и отображения списка с данными
     */
    @Test
    public void testLoadData() {
        mArtistListPresenter.onCreate(null, mockView);
        mArtistListPresenter.getData();
        mArtistListPresenter.onStop();
        verify(mockView).showList(artistList);
    }

    /**
     * Тест на правильность отображения пустого списка с данными
     */
    @Test
    public void testLoadNullData() {
        doAnswer(invocation -> Observable.just(null))
                .when(model)
                .getArtistList();
        mArtistListPresenter.getData();
        verify(mockView).showEmptyList();
    }

    /**
     * Тест на правильность обработки ошибки
     */
    @Test
    public void testOnError() {
        doAnswer(invocation -> Observable.error(new Throwable()))
                .when(model)
                .getArtistList();
        mArtistListPresenter.getData();
        verify(mockView).showError(mContext.getString(R.string.error));
    }

    /**
     * Тест на правильность восстановления данных с бандла
     */
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
