package com.example.mobilization.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilization.R;
import com.example.mobilization.di.App;
import com.example.mobilization.model.data.Artist;
import com.example.mobilization.presenter.DetailPresenter;
import com.example.mobilization.view.IDetailView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements IDetailView, NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.biografy_textView_detailActivity)
    TextView mBio;
    @Bind(R.id.count_textView_detailActivity)
    TextView mCount;
    @Bind(R.id.genres_textView_detailActivity)
    TextView mGenres;
    @Bind(R.id.cover_imageView_detailActivity)
    ImageView mCover;
    @Bind(R.id.toolbar_detailActivity)
    Toolbar mToolbar;
    @Bind(R.id.title_textView_toolbar)
    TextView mTitle;
    @Bind(R.id.url_textView_detailActivity)
    TextView mUrl;

    @Inject
    Picasso mPicasso;

    @Inject
    DetailPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Вызываем метод внедрения компонент из класса App
        App.getComponent().inject(this);
        //Биндим компоненты интерфейса
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        //Настойка тулбара
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        mPresenter.onCreate(savedInstanceState, this);
        mPresenter.showData(loadDataFromIntent());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    /**
     * Загрузка данных для вывода из интента
     */
    @Override
    public Artist loadDataFromIntent() {
        return getIntent().getExtras().getParcelable("data");
    }

    /**
     * Показ данных во вьюхах на экране, загрузка большого размера обложки
     *
     * @param artist - объект, с данными для вывода
     */
    @Override
    public void showData(Artist artist) {
        mBio.setText(artist.getDescription());
        mCount.setText(artist.getMusicCount());
        mGenres.setText(artist.getGenresList());
        mTitle.setText(artist.getName());
        mUrl.setText(artist.getLink());
        mUrl.setOnClickListener(e -> openUrl(artist.getLink()));
        mPicasso.with(this)
                .load(artist.getCover().getSmall())
                .placeholder(R.drawable.placeholder_big)
                .fit()
                .into(mCover);
    }

    /**
     * Открывает ссылку во встроенном браузере
     * @param url - ссылка
     */
    @Override
    public void openUrl(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    /**
     * Вывод сообщения во всплывающем контейнере
     *
     * @param message - сообщение
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}
