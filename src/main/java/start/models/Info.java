package start.models;

import java.util.ArrayList;

/**
 * Created by Aleksandr_Kugushev on 4/16/2016.
 */
public class Info {
    private ArrayList<String> artists = new ArrayList<>();
    public ArrayList<String> getArtists() {
        return artists;
    }
    public void setArtists(ArrayList<String> artists) {
        this.artists = artists;
    }

    private ArrayList<Album> albums = new ArrayList<>();
    public ArrayList<Album> getAlbums() {
        return albums;
    }
    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    private ArrayList<String> tags = new ArrayList<>();
    public ArrayList<String> getTags() {
        return tags;
    }
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    private ArrayList<Song> playlist = new ArrayList<>();
    public ArrayList<Song> getPlaylist() {
        return playlist;
    }
    public void setPlaylist(ArrayList<Song> playlist) {
        this.playlist = playlist;
    }

    private Double playlistWeight;
    public Double getPlaylistWeight() {
        return playlistWeight;
    }
    public void setPlaylistWeight(Double playlistWeight) {
        this.playlistWeight = playlistWeight;
    }
}
