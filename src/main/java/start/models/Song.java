package start.models;

/**
 * Created by Aleksandr_Kugushev on 4/16/2016.
 */
public class Song {
    public Song(String title, String album, String artist, String cover){
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.cover = cover;
    }

    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    private String album;
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }

    private String artist;
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }

    private String cover;
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
}
