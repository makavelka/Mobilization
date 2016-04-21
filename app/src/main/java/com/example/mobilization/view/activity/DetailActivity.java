package com.example.mobilization.view.activity;

import android.graphics.Bitmap;
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

    @Inject
    Picasso mPicasso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        App.getComponent().inject(this);
        ButterKnife.bind(this);
        showData(loadDataFromIntent());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public Artist loadDataFromIntent() {
        return getIntent().getExtras().getParcelable("data");
    }

    @Override
    public void showData(Artist artist) {
        mBio.setText(artist.getDescription());
        mCount.setText(artist.getMusicCount());
        mGenres.setText(artist.getGenresList());
        mTitle.setText(artist.getName());
        mPicasso.with(this)
                .load(artist.getCover().getSmall())
                .placeholder(R.drawable.placeholder_big)
                .fit()
                .into(mCover);
    }

    @Override
    public Bitmap loadSmallImage() {
        return getIntent().getExtras().getParcelable("bitmap");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}
