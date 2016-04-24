package com.example.mobilization.model;

import com.example.mobilization.BaseTest;
import com.example.mobilization.Const;
import com.example.mobilization.model.api.ApiInterface;
import com.example.mobilization.model.data.Artist;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ModelImplTest extends BaseTest {

    @Inject
    protected ApiInterface apiInterface;

    Model model;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    /**
     * Первоначальная настройка теста и инъекция зависимостей
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        model = new ModelImpl();
    }


    /**
     * Проверка данных, полученных с локального хранилища на правильность
     * @throws Exception
     */
    @Test
    public void testGetArtistList() {
        Artist[] artists = testUtils.getGson().fromJson(testUtils.readString("json/test.json"), Artist[].class);

        when(apiInterface.getArtists()).thenReturn(Observable.just(Arrays.asList(artists)));

        TestSubscriber<List<Artist>> testSubscriber = new TestSubscriber<>();
        model.getArtistList().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<Artist> actual = testSubscriber.getOnNextEvents().get(0);

        assertEquals(4, actual.size());
        assertEquals("Tove Lo", actual.get(0).getName());
        assertEquals("http://www.tove-lo.com/", actual.get(0).getLink());
        assertEquals(1080505L, (long) actual.get(0).getId());
    }
}
