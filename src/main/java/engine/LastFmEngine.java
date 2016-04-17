package engine;

import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Tag;
import de.umass.lastfm.Track;
import start.models.Step;

import java.util.*;

/**
 * Created by Aleksandr_Kugushev on 4/16/2016.
 */
public class LastFmEngine {
    private String apiKey = "a0d2513c0cd76704e43f5bbd3dcabb28";

    public Recommendation GetRecommendation(Step step) {
        switch (step.getInputType()) {
            case "artist":
                return getRecommendationByArtist(step);
            case "tag":
                return getRecommendationByTag(step);
        }
        return null;
    }

    private Recommendation getRecommendationByTag(Step step) {
        Recommendation r = new Recommendation(step);

        String tagName = step.getName();

        Tag tag = Tag.getInfo(tagName, apiKey);
        if (tag == null)
            return r;

        Collection<Artist> simularArtists = Tag.getTopArtists(tagName, apiKey);
        for (Artist a : simularArtists) {
            r.getArtists().add(a);
        }

        Collection<Album> albums = Tag.getTopAlbums(tagName, apiKey);
        for (Album a : albums) {
            r.getAlbums().add(a);
        }

        Collection<Tag> tags = Tag.getSimilar(tagName, apiKey);
        for (Tag t : tags) {
            r.getTags().add(t);
        }

        Collection<Track> tracks = Tag.getTopTracks(tagName, apiKey);
        for (Track t : tracks) {
            //
            r.getTracks().add(t);
        }

        return r;
    }

    private Recommendation getRecommendationByArtist(Step step) {

        Recommendation r = new Recommendation(step);

        String artistName = step.getName();

        Artist artist = FindArtist(artistName);
        if (artist == null)
            return r;

        Collection<Artist> simularArtists = Artist.getSimilar(artistName, 30, apiKey);
        for (Artist a : simularArtists) {
            r.getArtists().add(a);
        }

        Collection<Album> albums = Artist.getTopAlbums(artistName, apiKey);
        for (Album a : albums) {
            r.getAlbums().add(a);
        }

        Collection<Tag> tags = Artist.getTopTags(artistName, apiKey);
        for (Tag t : tags) {
            r.getTags().add(t);
        }

        Collection<Track> tracks = Artist.getTopTracks(artistName, apiKey);
        for (Track t : tracks) {
            r.getTracks().add(t);
        }

        return r;
    }

    private Artist FindArtist(String name) {
        Collection<Artist> artists = Artist.search(name, apiKey);
        if (artists.isEmpty())
            return null;

        return artists.iterator().next();
    }


    public Recommendation Merge(ArrayList<Recommendation> steps) {
        Recommendation res = new Recommendation(null);

        RangedSetArtists(steps, res);
        RangedSetAlbums(steps, res);
        RangedSetTags(steps, res);
        RangedSetTracks(steps, res);

        return res;
    }

    private void RangedSetTracks(ArrayList<Recommendation> steps, Recommendation res) {
        LinkedHashMap<String, Double> rangs = new LinkedHashMap<>();
        HashMap<String, Track> tracks = new HashMap<>();
        for (int i = steps.size() - 1; i >= 0; i--) {
            Recommendation r = steps.get(i);
            double mul = i >= steps.size() - 5 ? 1: -1;
            for (int j = 0; j < r.getTracks().size(); j++) {
                Track a = r.getTracks().get(j);
                String name = a.getName();

                int tagRang = 0;
                for (String t :a.getTags()) {
                    tagRang += FindTagIndex(r.getTags(), t);
                }
                double localRang = r.getTracks().size() - j;
                double newRang = mul * (i*4 + localRang + tagRang);
                if (tracks.containsKey(name)) {
                    Double rang = rangs.get(name);
                    rang += newRang;
                    rangs.put(name, rang);
                }
                else{
                    tracks.put(name, a);
                    rangs.put(name, newRang);
                }
            }
        }
        ArrayList<AbstractMap.Entry> byRang = new ArrayList<>(rangs.entrySet());
        byRang.sort(new Comparator<AbstractMap.Entry>() {
            @Override
            public int compare(AbstractMap.Entry o1, AbstractMap.Entry o2) {
                Double o1d = (Double)o1.getValue();
                Double o2d = (Double)o2.getValue();
                return o2d.compareTo(o1d);
            }
        });
        for (AbstractMap.Entry e:byRang){
            String key = (String)e.getKey();
            Track a = tracks.get(key);
            res.getTracks().add(a);
        }
    }

    private void RangedSetTags(ArrayList<Recommendation> steps, Recommendation res) {
        LinkedHashMap<String, Double> rangs = new LinkedHashMap<>();
        HashMap<String, Tag> tags = new HashMap<>();
        for (int i = steps.size() - 1; i >= 0; i--) {
            Recommendation r = steps.get(i);
            for (int j = 0; j < r.getTags().size(); j++) {
                Tag a = r.getTags().get(j);
                String name = a.getName();

                double newRang = i;
                if (tags.containsKey(name)) {
                    Double rang = rangs.get(name);
                    rang -= newRang;
                    rangs.put(name, rang);
                }
                else{
                    tags.put(name, a);
                    rangs.put(name, newRang);
                }
            }
        }
        ArrayList<AbstractMap.Entry> byRang = new ArrayList<>(rangs.entrySet());
        byRang.sort(new Comparator<AbstractMap.Entry>() {
            @Override
            public int compare(AbstractMap.Entry o1, AbstractMap.Entry o2) {
                Double o1d = (Double)o1.getValue();
                Double o2d = (Double)o2.getValue();
                return o2d.compareTo(o1d);
            }
        });
        for (AbstractMap.Entry e:byRang){
            String key = (String)e.getKey();
            Tag a = tags.get(key);
            res.getTags().add(a);
        }
    }

    private void RangedSetAlbums(ArrayList<Recommendation> steps, Recommendation res) {
        LinkedHashMap<String, Double> rangs = new LinkedHashMap<>();
        HashMap<String, Album> albums = new HashMap<>();
        for (int i = steps.size() - 1; i >= 0; i--) {
            Recommendation r = steps.get(i);
            double mul = i >= steps.size() - 5 ? 1: -1;
            for (int j = 0; j < r.getAlbums().size(); j++) {
                Album a = r.getAlbums().get(j);
                String name = a.getName();

                double localRang = r.getAlbums().size() - j;
                double newRang = mul * (i*4 + localRang);
                if (albums.containsKey(name)) {
                    Double rang = rangs.get(name);
                    rang += newRang;
                    rangs.put(name, rang);
                }
                else{
                    albums.put(name, a);
                    rangs.put(name, newRang);
                }
            }
        }
        ArrayList<AbstractMap.Entry> byRang = new ArrayList<>(rangs.entrySet());
        byRang.sort(new Comparator<AbstractMap.Entry>() {
            @Override
            public int compare(AbstractMap.Entry o1, AbstractMap.Entry o2) {
                Double o1d = (Double)o1.getValue();
                Double o2d = (Double)o2.getValue();
                return o2d.compareTo(o1d);
            }
        });
        for (AbstractMap.Entry e:byRang){
            String key = (String)e.getKey();
            Album a = albums.get(key);
            res.getAlbums().add(a);
        }
    }

    private void RangedSetArtists(ArrayList<Recommendation> steps, Recommendation res) {
        LinkedHashMap<String, Double> artistsRangs = new LinkedHashMap<>();
        HashMap<String, Artist> artists = new HashMap<>();
        for (int i = steps.size() - 1; i >= 0; i--) {
            Recommendation r = steps.get(i);
            double mul = i >= steps.size() - 5 ? 1: -1;
            for (int j = 0; j < r.getArtists().size(); j++) {
                Artist a = r.getArtists().get(j);
                String name = a.getName();

                double localRang = r.getArtists().size() - j;
                double newRang = mul * (i*4 + localRang);
                if (artists.containsKey(name)) {
                    Double rang = artistsRangs.get(name);
                    rang += newRang;
                    artistsRangs.put(name, rang);
                }
                else{
                    artists.put(name, a);
                    artistsRangs.put(name, newRang);
                }
            }
        }
        ArrayList<AbstractMap.Entry> artistsByRang = new ArrayList<>(artistsRangs.entrySet());
        artistsByRang.sort(new Comparator<AbstractMap.Entry>() {
            @Override
            public int compare(AbstractMap.Entry o1, AbstractMap.Entry o2) {
                Double o1d = (Double)o1.getValue();
                Double o2d = (Double)o2.getValue();
                return o2d.compareTo(o1d);
            }
        });
        for (AbstractMap.Entry e:artistsByRang){
            String key = (String)e.getKey();
            Artist a = artists.get(key);
            res.getArtists().add(a);
        }
    }

    private Integer FindTagIndex(ArrayList<Tag> tags, String name){
        for (int i = 0; i < tags.size(); i++) {
            if(tags.get(i).getName().equals(name))
                return tags.size() - i;
        }
        return 0;
    }
}
