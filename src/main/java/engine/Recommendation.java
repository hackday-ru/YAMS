package engine;

import start.models.Info;

/**
 * Created by Aleksandr_Kugushev on 4/16/2016.
 */
public class Recommendation {
    public Recommendation(Info info){
        this.info = info;
    }

    private Info info;
    public Info getInfo() {
        return info;
    }

}
