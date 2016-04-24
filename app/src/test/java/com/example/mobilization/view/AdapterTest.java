package com.example.mobilization.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilization.BaseTest;
import com.example.mobilization.R;
import com.example.mobilization.model.data.Artist;
import com.example.mobilization.view.adapter.ArtistAdapter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тест для проверки адаптера
 */
public class AdapterTest extends BaseTest {
    private ArtistAdapter adapter;
    private LayoutInflater layoutInflater;

    @Inject
    List<Artist> artistList;

    @Inject
    Context mContext;

    /**
     * Первоначальная настройка теста и инъекция данных
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        layoutInflater = mock(LayoutInflater.class);
        component.inject(this);
        adapter = new ArtistAdapter(mContext);
    }

    /**
     * Проверка на пустоту адаптера, адаптер не пустой
     */
    @Test
    public void adapterNotEmpty() {
        adapter.setArtistList(artistList);
        assertEquals(4, adapter.getItemCount());
    }

    /**
     * Проверка на пустоту адаптера, адаптер пустой
     */
    @Test
    public void getCount_shouldReturn0FromEmptyList() {
        adapter.setArtistList(emptyList());
        assertEquals(0, adapter.getItemCount());
    }

    /**
     * Проверка на правильность отображения количества данных
     */
    @Test
    public void getCount_shouldReturnCountFromList() {
        List<Artist> artists = new ArrayList<>();
        artists.add(mock(Artist.class));
        artists.add(mock(Artist.class));
        artists.add(mock(Artist.class));

        adapter.setArtistList(artists);
        assertEquals(3, adapter.getItemCount());
    }


    /**
     * Проверка на правильность получения данных
     */
    @Test
    public void getItem_shouldReturnItemsFromList() {
        List<Artist> artists = new ArrayList<>();
        artists.add(mock(Artist.class));
        artists.add(mock(Artist.class));
        artists.add(mock(Artist.class));

        adapter.setArtistList(artists);

        assertEquals(adapter.getItem(0), artists.get(0));
        assertEquals(adapter.getItem(1), artists.get(1));
        assertEquals(adapter.getItem(2), artists.get(2));
    }

    @SuppressLint("SetTextI18n")
    @Test
    public void getView_shouldBindDataAndReturnViewWithoutConvertView() {
        List<Artist> artists = asList(
                mock(Artist.class),
                mock(Artist.class),
                mock(Artist.class)
        );

        adapter.setArtistList(artists);
        ViewGroup container = mock(ViewGroup.class);

        for (int position = 0; position < artists.size(); position++) {
            when(artists.get(position).getName()).thenReturn("Title " + position);

            View view = mock(View.class);
            when(layoutInflater.inflate(R.layout.item_artist, container, false)).thenReturn(view);

            TextView nameTextView = mock(TextView.class);
            when(view.findViewById(R.id.name_textView_itemArtist)).thenReturn(nameTextView);
            verify(nameTextView).setText("Title " + position);
        }
    }
}
