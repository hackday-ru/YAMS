package start.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class HomeController extends BaseController {

    @RequestMapping(value = "/getstep", method = RequestMethod.POST)
    public Collection<Integer> getStep() {
        List<Integer> tracks = new ArrayList<Integer>();
        tracks.add(107033664);
        tracks.add(111737490);
        tracks.add(111737484);
        tracks.add(111737472);
        tracks.add(52991541);
        tracks.add(52973281);
        tracks.add(52973291);
        tracks.add(52973301);
        tracks.add(52973331);
        tracks.add(52991611);
        return tracks;
    }


}
