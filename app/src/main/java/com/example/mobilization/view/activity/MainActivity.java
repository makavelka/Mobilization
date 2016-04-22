package com.example.mobilization.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.mobilization.R;
import com.example.mobilization.di.App;
import com.example.mobilization.model.data.Artist;
import com.example.mobilization.presenter.ArtistListPresenter;
import com.example.mobilization.view.IMainView;
import com.example.mobilization.view.SimpleDividerItemDecoration;
import com.example.mobilization.view.adapter.ArtistAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainView {

    @Bind(R.id.artistList_recyclerView_mainActivity)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.noData_layout_mainActivity)
    View mNoData;
    @Bind(R.id.swipeRefreshLayout_mainActivity)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    ArtistListPresenter mPresenter;

    private ArtistAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Биндим компоненты интерфейса
        ButterKnife.bind(this);
        //Вызываем метод внедрения компонент из класса App
        App.getComponent().inject(this);
        //Устанавливаем кастомный тулбар
        setSupportActionBar(mToolbar);
        //Задание менеджера - вертикального списка для вывода исполнителей
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ArtistAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        //Устанавливаем разделители в списке
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        //Обновление данных при свайпе в начале списка
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.getData());
        //Сохранение состояния экрана в презентер
        mPresenter.onCreate(savedInstanceState, this);
        //Загрузка данных из интернета
        mPresenter.getData();
        //Вывод прогрессбара на экран
        mSwipeRefreshLayout.setRefreshing(true);
    }

    /**
     * Вывод списка исполнителей на экран, скрытие прогрессбара
     *
     * @param artistList - список исполнителей
     */

    @Override
    public void showList(List<Artist> artistList) {
        mAdapter.setArtistList(artistList);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * Выводится ошбика во всплывающем собщении
     *
     * @param error - сообщение об ошибке
     */

    @Override
    public void showError(String error) {
        mSwipeRefreshLayout.setRefreshing(false);
        showToast(error);
    }

    /**
     * Отображение сообщнения о пустом списке
     */

    @Override
    public void showEmptyList() {
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.setVisibility(View.GONE);
        mNoData.setVisibility(View.VISIBLE);
    }

    /**
     * Вызов сохраненного состояния экрана из бандла и восстановление его из презентера
     *
     * @param outState - баднл с сохранённым состоянием
     */

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    /**
     * Вывод сообщения во всплывающем контейнере
     *
     * @param message - сообщение
     */

    @Override
    public void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
