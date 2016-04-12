package com.example.mobilization.view.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilization.R;
import com.example.mobilization.model.data.Artist;
import com.example.mobilization.view.IDetailView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity  implements IDetailView {

    @Bind(R.id.biografy_textView_detailActivity)
    TextView mBio;
    @Bind(R.id.count_textView_detailActivity)
    TextView mCount;
    @Bind(R.id.genres_textView_detailActivity)
    TextView mGenres;
    @Bind(R.id.cover_imageView_detailActivity)
    ImageView mCover;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        showData(loadDataFromIntent());
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
        Picasso.with(this)
                .load(artist.getCover().getBig())
                .into(mCover);
    }

    @Override
    public Bitmap loadSmallImage() {
        return getIntent().getExtras().getParcelable("bitmap");
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
