package com.example.mobilization.view.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilization.view.activity.DetailActivity;
import com.example.mobilization.R;
import com.example.mobilization.model.data.Artist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    private List<Artist> artistList = new ArrayList<>();
    private Context mContext;

    public ArtistAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setArtistList(List<Artist> repoList) {
        this.artistList = repoList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Artist artist = artistList.get(position);
        holder.name.setText(artist.getName());
        holder.count.setText(artist.getMusicCount());
        holder.genres.setText(artist.getGenresList());
        holder.view.setOnClickListener(e -> {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("data", artist);
            intent.putExtra("image", ((BitmapDrawable)holder.cover.getDrawable()).getBitmap());
            if (Build.VERSION.SDK_INT < 21) {
                mContext.startActivity(intent);
            } else {
                Pair<View, String> p1 = Pair.create(holder.count, "count");
                Pair<View, String> p2 = Pair.create(holder.genres, "genre");
                Pair<View, String> p3 = Pair.create(holder.cover, "cover");
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity) mContext, p1, p2, p3);
                mContext.startActivity(intent, options.toBundle());
            }
        });
        Picasso.with(mContext).load(artist.getCover().getSmall()).placeholder(android.R.drawable.ic_dialog_info).into(holder.cover);
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cover_imageView_itemArtist)
        ImageView cover;
        @Bind(R.id.name_textView_itemArtist)
        TextView name;
        @Bind(R.id.genre_textView_itemArtist)
        TextView genres;
        @Bind(R.id.count_textView_itemArtist)
        TextView count;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }
    }
}
