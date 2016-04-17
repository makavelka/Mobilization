package com.example.mobilization.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import com.example.mobilization.view.RecyclerItemClickListener;
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
        mPresenter.onCreate(savedInstanceState, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new ArtistAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        }));
        mPresenter.getData();

    }

    @Override
    public void showList(List<Artist> artistList) {
        mAdapter.setArtistList(artistList);
    }

    private void makeToast(String text) {
        Snackbar.make(mToolbar, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void showEmptyList() {
        mRecyclerView.setVisibility(View.GONE);
        mNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
