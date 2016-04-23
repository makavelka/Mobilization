
package com.example.mobilization.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO класс, хранящий данные об исполнителе, в который будут конвертироваться приходящие
 * данные JSON.
 * Используемые аннотации предоставляются библиотекой GSON.
 * Реализован интерфейс Parcelable, для передачи данных между экранами.
 */
public class Artist implements Parcelable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("genres")
    @Expose
    private List<String> genres = new ArrayList<String>();
    @SerializedName("tracks")
    @Expose
    private Integer tracks;
    @SerializedName("albums")
    @Expose
    private Integer albums;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cover")
    @Expose
    private Cover cover;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The genres
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * 
     * @param genres
     *     The genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * 
     * @return
     *     The tracks
     */
    public Integer getTracks() {
        return tracks;
    }

    /**
     * 
     * @param tracks
     *     The tracks
     */
    public void setTracks(Integer tracks) {
        this.tracks = tracks;
    }

    /**
     * 
     * @return
     *     The albums
     */
    public Integer getAlbums() {
        return albums;
    }

    /**
     * 
     * @param albums
     *     The albums
     */
    public void setAlbums(Integer albums) {
        this.albums = albums;
    }

    /**
     * 
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The cover
     */
    public Cover getCover() {
        return cover;
    }

    /**
     * 
     * @param cover
     *     The cover
     */
    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public String getMusicCount() {
        StringBuilder sb = new StringBuilder();
        String albumName;
        switch (albums% 10) {
            case 1:
                albumName = " альбом";
                break;
            case 2:
            case 3:
            case 4:
                albumName = " альбома";
                break;
            default:
                albumName = " альбомов";
                break;

        }
        String trackName ;
        switch (tracks % 10) {
            case 1:
                trackName = " песня";
                break;
            case 2:
            case 3:
            case 4:
                trackName = " песни";
                break;
            default:
                trackName = " песен";
                break;

        }
        sb.append(albums)
                .append(albumName)
                .append(" • ")
                .append(tracks)
                .append(trackName);
        return sb.toString();
    }

    public String getGenresList() {
        String list = "";
        for (String s: genres) {
            list += s + " / ";
        }
        list = list.substring(0, list.length()-2);
        return list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeStringList(this.genres);
        dest.writeValue(this.tracks);
        dest.writeValue(this.albums);
        dest.writeString(this.link);
        dest.writeString(this.description);
        dest.writeParcelable(this.cover, flags);
    }

    public Artist() {
    }

    protected Artist(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.genres = in.createStringArrayList();
        this.tracks = (Integer) in.readValue(Integer.class.getClassLoader());
        this.albums = (Integer) in.readValue(Integer.class.getClassLoader());
        this.link = in.readString();
        this.description = in.readString();
        this.cover = in.readParcelable(Cover.class.getClassLoader());
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel source) {
            return new Artist(source);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };
}
