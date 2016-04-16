package engine;

/**
 * Created by Aleksandr_Kugushev on 4/16/2016.
 */
public class DeezerRequest {

    public DeezerRequest(String artist, String album, String track) {
        this.artist = artist;
        this.album = album;
        this.track = track;
    }

    private String artist;
    public String getArtist() {
        return this.artist;
    }

    private String album;
    public String getAlbum() {
        return album;
    }

    private String track;
    public String getTrack() {
        return track;
    }
}
