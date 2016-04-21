package com.example.mobilization.model;

import com.example.mobilization.BaseTest;
import com.example.mobilization.model.api.ApiInterface;
import com.example.mobilization.model.api.ApiService;
import com.example.mobilization.model.data.Artist;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class ApiInterfaceTest extends BaseTest {

    private MockWebServer server;
    private ApiInterface apiInterface;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        server = new MockWebServer();
        server.start();
        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

                if (request.getPath().equals("/download.cdn.yandex.net/mobilization-2016/artists.json")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("raw/test.json"));
                }
                return new MockResponse().setResponseCode(404);
            }
        };

        server.setDispatcher(dispatcher);
        HttpUrl baseUrl = server.url("/");
        apiInterface = new ApiService().getApiService();
    }


    @Test
    public void testGetArtists() throws Exception {

        TestSubscriber<List<Artist>> testSubscriber = new TestSubscriber<>();
        apiInterface.getArtists().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<Artist> actual = testSubscriber.getOnNextEvents().get(0);

//        Assert.assertEquals(7, actual.size());
        Assert.assertEquals("Tove Lo", actual.get(0).getName());
        Assert.assertEquals("http://www.tove-lo.com/", actual.get(0).getLink());
        Assert.assertEquals(1080505L, (long) actual.get(0).getId());
    }

    @Test
    public void testGetArtistsIncorrect() throws Exception {
        try {
            apiInterface.getArtists().subscribe();
            fail();
        } catch (Exception expected) {
            assertEquals("HTTP 404 OK", expected.getMessage());
        }
    }


    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}