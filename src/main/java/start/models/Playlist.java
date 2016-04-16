package start.models;

import java.util.Collection;

/**
 * Created by Aleksandr_Kugushev on 4/16/2016.
 */
public class Playlist {
    private Collection<Integer> tracks;

    public void setTracks(Collection<Integer> tracks){
        this.tracks = tracks;
    }

    public Collection<Integer> getTracks(){
        return this.tracks;
    }
}
