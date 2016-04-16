package engine;

import de.umass.lastfm.*;
import start.models.Info;
import start.models.Song;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Aleksandr_Kugushev on 4/16/2016.
 */
public class LastFmEngine {
    private String apiKey = "a0d2513c0cd76704e43f5bbd3dcabb28";

    public Recommendation GetRecommendation(String artistName){
        Info info = new Info();
        Recommendation recommendation = new Recommendation(info);

        Artist artist = FindArtist(artistName);
        if (artist == null)
            return recommendation;

        Collection<Artist> simularArtists = Artist.getSimilar(artistName, 10, apiKey);
        info.setArtists(new ArrayList<>());
        for(Artist a: simularArtists)
            info.getArtists().add(a.getName());

        Collection<Album> albums = Artist.getTopAlbums(artistName, apiKey);
        info.setAlbums(new ArrayList<>());
        for(Album a: albums)
            info.getAlbums().add(a.getName());

        Collection<Tag> tags = Artist.getTopTags(artistName, apiKey);
        info.setTags(new ArrayList<>());
        for(Tag t: tags)
            info.getTags().add(t.getName());

        Collection<Track> tracks = Artist.getTopTracks(artistName, apiKey);
        info.setPlaylist(new ArrayList<>());
        for(Track t: tracks){
            Song s = new Song(t.getName(), t.getAlbum(), t.getArtist(), t.getImageURL(ImageSize.SMALL));
            info.getPlaylist().add(s);
        }

        return recommendation;
    }

    private Artist FindArtist(String name){
        Collection<Artist> artists = Artist.search(name, apiKey);
        if(artists.isEmpty())
            return null;

        return artists.iterator().next();
    }





}
