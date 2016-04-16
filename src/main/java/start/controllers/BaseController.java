package start.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sergei_Shatilov on 4/16/2016.
 */
public class BaseController {

    @RequestMapping(value="/readcookie", method = RequestMethod.GET)
    public static String getCookie(String cookieName, HttpServletRequest request) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            int i = 0;
            boolean cookieExists = false;
            while (!cookieExists && i < cookies.length) {
                if (cookies[i].getName().equals(cookieName)) {
                    cookieExists = true;
                    value = cookies[i].getValue();
                } else {
                    i++;
                }
            }
        }
        return value;
    }
}
