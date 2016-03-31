package com.example.mobilization.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Artist artist = artistList.get(position);
        holder.name.setText(artist.getName());
        holder.count.setText(String.valueOf(artist.getTracks()));
//        holder.genres.setText(artist.getGenres());
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


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
