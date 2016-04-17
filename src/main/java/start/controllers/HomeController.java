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
    private static ArrayList<Recommendation> steps = new ArrayList<>();


    @CrossOrigin()
    @RequestMapping(value = "/magic", method = RequestMethod.GET)
    public Info Test(String name, String type, String reset, String albumArtist){

        try {
            if(reset!=null)
                steps.clear();
            Step step = new Step();
            step.setName(name);
            step.setInputType(type);
            step.setAlbumArtist(albumArtist);
            return Do(step);
        } catch (Exception e) {
            return new Info();
        }
    }

    private Info Do(Step step){
        LastFmEngine engine = new LastFmEngine();

        Recommendation r = engine.GetRecommendation(step);
        steps.add(r);

        Recommendation result = engine.Merge(steps);
        return result.getInfo();
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
