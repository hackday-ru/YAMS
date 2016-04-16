package start.controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import engine.LastFmEngine;
import engine.Recommendation;
import org.springframework.web.bind.annotation.*;
import start.models.Info;
import start.models.Song;
import start.models.Step;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class HomeController extends BaseController {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Info Test(@RequestBody String steps){

        try {
            Collection<Step> values = new ObjectMapper().readValue(steps, new TypeReference<Collection<Step>>() {
            });

            return Do(values);
        } catch (Exception e) {
            return new Info();
        }
    }

    private Info Do(Collection<Step> values){
        LastFmEngine engine = new LastFmEngine();
        if (values.isEmpty())
            return new Info();

        Recommendation r = engine.GetRecommendation(values.iterator().next().getName());
        return new Info();
        //return r.getInfo();
    }




    @CrossOrigin()
    @RequestMapping(value = "/getstep", method = RequestMethod.POST)
    public Info getStep(@RequestBody String steps) {
        List<Integer> tracks = new ArrayList<Integer>();
        try {
            Collection<Step> readValues = new ObjectMapper().readValue(steps, new TypeReference<Collection<Step>>() {
            });




        } catch (Exception e) {

        }

        Info info = new Info();

        ArrayList<String> artists = new ArrayList<>();
        artists.add("Black Sabbath");
        artists.add("Rem");
        artists.add("Abba");
        info.setArtists(artists);

        ArrayList<String> albums = new ArrayList<>();
        albums.add("Eat");
        albums.add("Shit");
        albums.add("Die");
        info.setAlbums(albums);

        ArrayList<String> tags = new ArrayList<>();
        tags.add("oldschool");
        tags.add("gothic");
        tags.add("black");
        info.setTags(tags);

        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("Song 1", "Nemo", "Nightwish", "http://img2-ak.lst.fm/i/u/770x0/3e2e44b538ef48a5b9a3f00ae957399b.jpg"));
        songs.add(new Song("Song 2", "Nemo", "Nightwish", "http://img2-ak.lst.fm/i/u/770x0/3e2e44b538ef48a5b9a3f00ae957399b.jpg"));
        songs.add(new Song("Song 3", "Nemo", "Nightwish", "http://img2-ak.lst.fm/i/u/770x0/3e2e44b538ef48a5b9a3f00ae957399b.jpg"));
        songs.add(new Song("Song 4", "Nemo", "Nightwish", "http://img2-ak.lst.fm/i/u/770x0/3e2e44b538ef48a5b9a3f00ae957399b.jpg"));
        songs.add(new Song("Song 5", "Nemo", "Nightwish", "http://img2-ak.lst.fm/i/u/770x0/3e2e44b538ef48a5b9a3f00ae957399b.jpg"));
        songs.add(new Song("Song 6", "Nemo", "Nightwish", "http://img2-ak.lst.fm/i/u/770x0/3e2e44b538ef48a5b9a3f00ae957399b.jpg"));
        songs.add(new Song("Song 7", "Nemo", "Nightwish", "http://img2-ak.lst.fm/i/u/770x0/3e2e44b538ef48a5b9a3f00ae957399b.jpg"));
        songs.add(new Song("Song 8", "Nemo", "Nightwish", "http://img2-ak.lst.fm/i/u/770x0/3e2e44b538ef48a5b9a3f00ae957399b.jpg"));
        songs.add(new Song("Song 9", "Nemo", "Nightwish", "http://img2-ak.lst.fm/i/u/770x0/3e2e44b538ef48a5b9a3f00ae957399b.jpg"));
        songs.add(new Song("Song 10", "Nemo", "Nightwish", "http://img2-ak.lst.fm/i/u/770x0/3e2e44b538ef48a5b9a3f00ae957399b.jpg"));
        songs.add(new Song("Song 11", "Nemo", "Nightwish", "http://img2-ak.lst.fm/i/u/770x0/3e2e44b538ef48a5b9a3f00ae957399b.jpg"));
        info.setPlaylist(songs);
        info.setPlaylistWeight(0.5);

        return info;
    }


}
