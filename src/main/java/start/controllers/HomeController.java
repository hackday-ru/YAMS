package start.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController extends BaseController{

    @RequestMapping("/home")
    public String getHomePage() {
        return "home";
    }


}
