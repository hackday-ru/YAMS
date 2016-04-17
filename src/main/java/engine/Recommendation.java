package engine;

import de.umass.lastfm.*;
import de.umass.lastfm.Album;
import start.models.*;

import java.util.ArrayList;

/**
 * Created by Aleksandr_Kugushev on 4/16/2016.
 */
public class Recommendation {
    public Recommendation(Step step){
        this.step = step;
    }

    private Step step;
    public Step getStep() {
        return step;
    }

    private ArrayList<Artist> artists = new ArrayList<>();
    public ArrayList<Artist> getArtists() {
        return artists;
    }

    private ArrayList<Album> albums = new ArrayList<>();
    public ArrayList<Album> getAlbums() {
        return albums;
    }

    private ArrayList<Tag> tags = new ArrayList<>();
    public ArrayList<Tag> getTags() {
        return tags;
    }

    private ArrayList<Track> tracks = new ArrayList<>();
    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public Info getInfo(){
        Info info = new Info();
        for (int i = 0; i < 10; i++) {
            if(getArtists().size() > i)
                info.getArtists().add(getArtists().get(i).getName());
            if(getAlbums().size() > i) {
                Album a = getAlbums().get(i);
                start.models.Album aa = new start.models.Album(a.getName(), a.getArtist(), a.getImageURL(ImageSize.SMALL));
                info.getAlbums().add(aa);
            }
            if(getTags().size() > i)
                info.getTags().add(getTags().get(i).getName());
            if(getTracks().size() > i) {
                Track t = getTracks().get(i);
                Song s = new Song(t.getName(), t.getAlbum(), t.getArtist(), t.getImageURL(ImageSize.SMALL));
                info.getPlaylist().add(s);
            }
        }
        return info;
    }
}
